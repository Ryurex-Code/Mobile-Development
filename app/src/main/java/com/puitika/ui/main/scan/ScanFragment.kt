package com.puitika.ui.main.scan

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.TranslateAnimation
import android.widget.PopupWindow
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.puitika.R
import com.puitika.data.model.ScanModel
import com.puitika.databinding.FragmentPopupBinding
import com.puitika.databinding.FragmentScanBinding
import com.puitika.ml.Model
import com.puitika.ui.profile.ProfileActivity
import com.puitika.utils.getImageUri
import com.puitika.utils.showToast
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeOp
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer

/**
 * A simple [Fragment] subclass.
 * Use the [ScanFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ScanFragment : Fragment() {
    private lateinit var binding: FragmentScanBinding
    private lateinit var bindingPopup: FragmentPopupBinding
    private var currentImageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showScanOptionsPopup()

        setAction()
        binding.btnScan.setOnClickListener {
            processImage()
        }
    }

    private fun processImage() {
        currentImageUri?.let { uri ->
            val bitmap = MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, uri)
            val tensorImage = preprocessImage(bitmap)
            val model = Model.newInstance(requireActivity())
            val outputs = model.process(createInputBuffer(tensorImage))
            val confidences: FloatArray = outputs.outputFeature0AsTensorBuffer.floatArray
            val classes = arrayOf("Bali", "Sumatera Barat", "Lombok", "Palembang", "Riau", "Sumatera Utara")

            val scanModelList = createScanModelList(confidences, classes)

            model.close()
            showResult(scanModelList)
        }
    }

    private fun preprocessImage(bitmap: Bitmap): TensorImage {
        val imageProcessor = ImageProcessor.Builder().add(ResizeOp(224, 224, ResizeOp.ResizeMethod.BILINEAR)).build()
        var tensorImage = TensorImage(DataType.FLOAT32)
        tensorImage.load(bitmap)
        return imageProcessor.process(tensorImage)
    }

    private fun createInputBuffer(tensorImage: TensorImage): TensorBuffer {
        val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.FLOAT32)
        inputFeature0.loadBuffer(tensorImage.buffer)
        return inputFeature0
    }

    private fun createScanModelList(confidences: FloatArray, classes: Array<String>): List<ScanModel> {
        val scanModelList = mutableListOf<ScanModel>()
        for ((index, confidence) in confidences.withIndex()) {
            val regionName = if (index < classes.size) classes[index] else "Unknown Region"
            val formattedPercent = String.format("%.2f%%", confidence * 100)
            scanModelList.add(ScanModel(region = regionName, percent = formattedPercent))
        }
        return scanModelList
    }

    private fun showResult(scanModelList: List<ScanModel>) {
        val adapter = ScanModelAdapter(requireContext(), scanModelList)

        // Use GridLayoutManager with 2 columns
        val layoutManager = GridLayoutManager(requireContext(), 2)

        binding.rvScan.layoutManager = layoutManager
        binding.rvScan.adapter = adapter
        binding.rvScan.visibility = View.VISIBLE
    }


    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { isSuccess ->
        if (isSuccess) {
            showImage()
        }else{
            showToast(requireActivity(), "No Photo Captured")
        }
    }

    private fun showImage() {
        currentImageUri?.let {
            binding.ivPhoto.setPadding(0, 0, 0, 0)
            binding.ivPhoto.setImageURI(it)
            binding.btnScan.visibility = View.VISIBLE
            binding.layoutIvPhoto.visibility = View.VISIBLE
        }
    }

    private fun showScanOptionsPopup() {
        bindingPopup = FragmentPopupBinding.inflate(layoutInflater)
        val popupWindow = createPopupWindow()

        with(bindingPopup) {
            btnCamera.setOnClickListener {
                showToast(requireActivity(), "Camera")
                popupWindow.dismiss()
                currentImageUri = getImageUri(requireActivity())
                launcherIntentCamera.launch(currentImageUri)
            }

            btnGalery.setOnClickListener {
                showToast(requireActivity(), "Gallery")
                popupWindow.dismiss()
                launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }

            layoutBtnClose.setOnClickListener {
                popupWindow.dismiss()
            }

            ivClose.setOnClickListener {
                popupWindow.dismiss()
            }

            imageViewCloseup.setOnClickListener {
                popupWindow.dismiss()
            }

            setPopupAnimation(root, popupWindow)
        }

        popupWindow.showAtLocation(bindingPopup.root, Gravity.CENTER, 0, 0)
    }

    private fun createPopupWindow(): PopupWindow {
        return PopupWindow(
            bindingPopup.root,
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        ).apply {
            setBackgroundDrawable(resources.getDrawable(R.drawable.popup_background))
        }
    }

    private fun setPopupAnimation(view: View, popupWindow: PopupWindow) {
        val translateAnimation = TranslateAnimation(0f, 0f, popupWindow.height.toFloat(), 0f)
        translateAnimation.duration = 500
        view.startAnimation(translateAnimation)
    }

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            currentImageUri = uri
            showImage()
        } else {
            showToast(requireActivity(), "No Media Selected")
        }
    }

    private fun setAction() {
        binding.topNavigation.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_account -> {
                    startActivity(Intent(requireContext(), ProfileActivity::class.java))
                    true
                }

                else -> false
            }
        }
    }
}
