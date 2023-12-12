package com.puitika.ui.main.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.PopupWindow
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.puitika.R
import com.puitika.databinding.ActivityMainBinding
import com.puitika.ui.login.LoginActivity
import com.puitika.ui.main.event.EventFragment
import com.puitika.ui.main.home.HomeFragment
import com.puitika.ui.main.scan.ScanFragment
import java.io.ByteArrayOutputStream

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val REQUEST_IMAGE_CAPTURE = 1
    private val REQUEST_IMAGE_GALLERY = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isLoggedIn = intent.getBooleanExtra(EXTRA_USER, false)

        if (!isLoggedIn) {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        with(binding) {
            bottomNav.add(MeowBottomNavigation.Model(1, R.drawable.home_ic))
            bottomNav.add(MeowBottomNavigation.Model(2, R.drawable.scan_ic))
            bottomNav.add(MeowBottomNavigation.Model(3, R.drawable.event_ic))
            bottomNav.show(1)
            navigation(HomeFragment(),true)

            bottomNav.setOnClickMenuListener {
                when (it.id) {
                    1 -> navigation(HomeFragment(),false)
                    2 -> showScanOptionsPopup(it.id)
                    3 -> navigation(EventFragment(),false)
                }
            }
        }

        supportFragmentManager.addOnBackStackChangedListener(onBackStackChangedListener)
    }

    private fun showScanOptionsPopup(menuId: Int) {
        val popupView = LayoutInflater.from(this).inflate(R.layout.fragment_popup, null)

        val layoutParams = WindowManager.LayoutParams()
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT

        val popupWindow = PopupWindow(
            popupView,
            layoutParams.width,
            layoutParams.height
        )

        popupWindow.setBackgroundDrawable(resources.getDrawable(R.drawable.popup_background))

        val buttonCamera = popupView.findViewById<Button>(R.id.button_camera)
        val buttonGallery = popupView.findViewById<Button>(R.id.button_gallery)
        val imageViewClose = popupView.findViewById<ImageView>(R.id.imageViewClose)
        val imageViewCloseup = popupView.findViewById<ImageView>(R.id.imageViewCloseup)

        buttonCamera.setOnClickListener {
            Toast.makeText(this, "Camera selected", Toast.LENGTH_SHORT).show()
            popupWindow.dismiss()
            openCameraAndCaptureImage()
        }

        buttonGallery.setOnClickListener {
            Toast.makeText(this, "Gallery selected", Toast.LENGTH_SHORT).show()
            popupWindow.dismiss()
            openGalleryAndSelectImage()
        }

        imageViewClose.setOnClickListener {
            popupWindow.dismiss()
        }

        imageViewCloseup.setOnClickListener {
            popupWindow.dismiss()
        }

        val translateAnimation = TranslateAnimation(0f, 0f, layoutParams.height.toFloat(), 0f)
        translateAnimation.duration = 500
        popupView.startAnimation(translateAnimation)

        popupWindow.showAtLocation(binding.root, Gravity.CENTER, 0, 0)
    }

    override fun onDestroy() {
        super.onDestroy()
        supportFragmentManager.removeOnBackStackChangedListener(onBackStackChangedListener)
    }

    private val onBackStackChangedListener =
        FragmentManager.OnBackStackChangedListener {
            val currentFragment = supportFragmentManager.findFragmentById(R.id.container)
            currentFragment?.let { updateBottomNavigation(it) }
        }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    private fun navigation(fragment: Fragment, first:Boolean) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment)
        if (!first){
        fragmentTransaction.addToBackStack(null)}
        fragmentTransaction.commit()
        updateBottomNavigation(fragment)
    }

    private fun updateBottomNavigation(fragment: Fragment) {
        val selectedItem = when (fragment) {
            is HomeFragment -> 1
            is ScanFragment -> 2
            is EventFragment -> 3
            else -> return
        }
        binding.bottomNav.show(selectedItem, true)
    }

    companion object {
        const val EXTRA_USER = "fromLogin"

    }

    private fun openCameraAndCaptureImage() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
    }
    private fun openGalleryAndSelectImage() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        galleryIntent.type = "image/*"
        startActivityForResult(galleryIntent, REQUEST_IMAGE_GALLERY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQUEST_IMAGE_CAPTURE -> {
                if (resultCode == Activity.RESULT_OK) {
                    handleImageResult(data?.extras?.get("data") as Bitmap)
                }
            }
            REQUEST_IMAGE_GALLERY -> {
                if (resultCode == Activity.RESULT_OK) {
                    // Gambar dari galeri dipilih
                    val selectedImageUri = data?.data
                    val selectedImagePath = getRealPathFromURI(selectedImageUri)
                    handleImageResult(BitmapFactory.decodeFile(selectedImagePath))
                }
            }
        }
    }

    private fun handleImageResult(imageBitmap: Bitmap) {
        val intent = Intent(this, ScanFragment::class.java)
        val byteArrayOutputStream = ByteArrayOutputStream()
        imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        intent.putExtra("imageBitmap", byteArray)
        startActivity(intent)
    }

    private fun getRealPathFromURI(uri: Uri?): String {
        val cursor = contentResolver.query(uri!!, null, null, null, null)
        val index = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        val result = cursor.getString(index)
        cursor.close()
        return result
    }



}


