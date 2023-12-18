package com.puitika.ui.main.event

import android.Manifest
import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.text.InputType
import android.util.Base64
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.viewModels
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.puitika.R
import com.puitika.data.model.CreateEventModel
import com.puitika.databinding.ActivityAddEventFormBinding
import com.puitika.databinding.ActivityClothDetailBinding
import com.puitika.databinding.FragmentHomeBinding
import com.puitika.factory.ViewModelFactory
import com.puitika.ui.main.home.HomeViewModel
import com.puitika.ui.main.main.MainActivity
import com.puitika.utils.Result
import com.puitika.utils.showToast
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AddEventFormActivity : AppCompatActivity() {

    private lateinit var ivBannerImage: ImageView
    private lateinit var etDateDay: EditText
    private lateinit var etEventTimeStart: EditText
    private lateinit var etEventTimeEnd: EditText
    private lateinit var binding: ActivityAddEventFormBinding
    private lateinit var factory: ViewModelFactory
    private val viewModel: AddEventFormViewModel by viewModels { factory }


    companion object {
        private const val REQUEST_CODE_PERMISSION = 123
        private const val REQUEST_CODE_PICK_IMAGE = 124
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_event_form)
        binding = ActivityAddEventFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setViewModelFactory()

        val backButton = findViewById<MaterialButton>(R.id.btn_back)
        backButton.setOnClickListener {
            finish()
        }

        val confirmButton = findViewById<Button>(R.id.btn_confirm)
        confirmButton.setOnClickListener {
            if (isEventDataComplete()) {
                showConfirmationDialog()
            } else {
                Toast.makeText(this, "Anda belum melengkapi data event!", Toast.LENGTH_SHORT).show()
            }
        }

        val ivCalender = findViewById<ImageView>(R.id.iv_calender)
        ivCalender.setOnClickListener {
            showDatePickerDialog()
        }
        ivBannerImage = findViewById(R.id.iv_bannerimage)
        etDateDay = findViewById(R.id.et_dateday)
        etDateDay.setOnClickListener {
            showDatePickerDialog()
        }

        etDateDay.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                showDatePickerDialog()
            }
        }

        etEventTimeStart = findViewById(R.id.et_eventtime_start)
        etEventTimeStart.inputType = InputType.TYPE_NULL
        etEventTimeStart.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                showTimePickerDialog(etEventTimeStart)
            }
        }

        etEventTimeEnd = findViewById(R.id.et_eventtime_end)
        etEventTimeEnd.inputType = InputType.TYPE_NULL
        etEventTimeEnd.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                showTimePickerDialog(etEventTimeEnd)
            }
        }

        val cardViewBanner = findViewById<MaterialCardView>(R.id.cardview_10)
        cardViewBanner.setOnClickListener {
            pickImage()
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSION && grantResults.isNotEmpty() &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            pickImage()
        } else {
            Toast.makeText(this, "Izin akses galeri ditolak", Toast.LENGTH_SHORT).show()
        }
    }

    private fun pickImage() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                REQUEST_CODE_PERMISSION
            )
        } else {
            val pickIntent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            startActivityForResult(pickIntent, REQUEST_CODE_PICK_IMAGE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_PICK_IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            val imageUri = data.data
            ivBannerImage.setImageURI(imageUri)
        }
    }

    private fun sendDataToApi() {
        // Get data from EditText fields
        val eventName = findViewById<EditText>(R.id.et_eventname).text.toString()
        val eventDateDay = findViewById<EditText>(R.id.et_dateday).text.toString()
        val eventDescription = findViewById<EditText>(R.id.et_descevent).text.toString()
        val ticketPrice = findViewById<EditText>(R.id.et_ticketprice).text.toString()
        val contactPerson = findViewById<EditText>(R.id.et_contactperson).text.toString()
        val organizer = findViewById<EditText>(R.id.et_organizer).text.toString()
        val eventLocation = findViewById<EditText>(R.id.et_eventlocation).text.toString()
        val eventTimeStart = etEventTimeStart.text.toString()
        val eventTimeEnd = etEventTimeEnd.text.toString()
        val image = imageViewToBase64(findViewById(R.id.iv_bannerimage))
        Log.d("IMAGE", image.take(10))

        // Get selected radio button ID
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val checkedRadioButtonId = radioGroup.checkedRadioButtonId
        Log.d("HAHAAHH", checkedRadioButtonId.toString())

        if (checkedRadioButtonId != -1) {
            viewModel.createEvent(
                CreateEventModel(
                    nama = eventName,
                    waktu = eventDateDay,
                    description = eventDescription,
                    jenis = checkedRadioButtonId==2131362368,
                    harga = ticketPrice,
                    contact = contactPerson,
                    penyelenggara = organizer,
                    lokasi = eventLocation,
                    mulai = eventTimeStart,
                    selesai =eventTimeEnd,
                    gambar = image
                )
            ).observe(this){ result ->
                when (result) {
                    is Result.Loading -> {

                    }
                    is Result.Error -> {
                        showCustomDialog(result.data, false)
                    }
                    is Result.Success -> {
                        showCustomDialog(result.data.message, true)
                        Handler(Looper.getMainLooper()).postDelayed({
                            val intent = Intent(this, MainActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            intent.putExtra("fromAddEvent", true)
                            startActivity(intent)
                        }, 2000)
                    }
                }
            }
            // Now you can use the eventType variable
        } else {
            // Handle the case where no radio button is selected
            showToast(this@AddEventFormActivity, "Please Choose Event Type")
        }
    }


    fun imageViewToBase64(imageView: ImageView): String {
        val bitmap: Bitmap = imageView.drawable.toBitmap() // Convert ImageView to Bitmap
        val byteArrayOutputStream = ByteArrayOutputStream()

        // Compress the Bitmap to PNG format
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)

        // Convert ByteArray to Base64 String
        val byteArray = byteArrayOutputStream.toByteArray()
        return "data:image/png;base64,${Base64.encodeToString(byteArray, Base64.NO_WRAP)}"
    }

    private fun showDatePickerDialog() {
        etDateDay.isEnabled = false
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedCalendar = Calendar.getInstance().apply {
                    set(selectedYear, selectedMonth, selectedDay)
                }

                val formattedDate = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault())
                    .format(selectedCalendar.time)

                etDateDay.setText(formattedDate)
            },
            year,
            month,
            day
        )

        datePickerDialog.setOnCancelListener {
            etDateDay.isEnabled = true
        }

        datePickerDialog.show()
        etDateDay.inputType = InputType.TYPE_NULL
    }

    private fun showTimePickerDialog(editText: EditText) {
        editText.isEnabled = false
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            this,
            { _, selectedHour, selectedMinute ->
                val formattedTime =
                    String.format(Locale.getDefault(), "%02d:%02d", selectedHour, selectedMinute)
                editText.setText(formattedTime)
            },
            hour,
            minute,
            false
        )

        timePickerDialog.setOnCancelListener {
            editText.isEnabled = true
        }

        timePickerDialog.show()
    }

    private fun isEventDataComplete(): Boolean {
        val eventName = findViewById<EditText>(R.id.et_eventname).text.toString()
        val eventDateDay = findViewById<EditText>(R.id.et_dateday).text.toString()
        val eventDescription = findViewById<EditText>(R.id.et_descevent).text.toString()
        val ticketPrice = findViewById<EditText>(R.id.et_ticketprice).text.toString()
        val contactPerson = findViewById<EditText>(R.id.et_contactperson).text.toString()
        val organizer = findViewById<EditText>(R.id.et_organizer).text.toString()
        val eventLocation = findViewById<EditText>(R.id.et_eventlocation).text.toString()
        val eventTimeStart = etEventTimeStart.text.toString()
        val eventTimeEnd = etEventTimeEnd.text.toString()

        return eventName.isNotEmpty() &&
                eventDateDay.isNotEmpty() &&
                eventDescription.isNotEmpty() &&
                ticketPrice.isNotEmpty() &&
                contactPerson.isNotEmpty() &&
                organizer.isNotEmpty() &&
                eventLocation.isNotEmpty() &&
                eventTimeStart.isNotEmpty() &&
                eventTimeEnd.isNotEmpty()
    }
    private fun showConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.fragment_popup_confirmevent, null)
        builder.setView(dialogView)

        val btnNoConfirm = dialogView.findViewById<Button>(R.id.button_noconfirm)
        val btnYesConfirm = dialogView.findViewById<Button>(R.id.button_yesconfirm)
        val tvAskConfirm = dialogView.findViewById<TextView>(R.id.tv_askconfirm)
        val tvAskDesc = dialogView.findViewById<TextView>(R.id.tv_askdesc)

        tvAskConfirm.text = "Are you sure?"
        tvAskDesc.text = "We will inform your event if it fulfills requirements at the notification"

        val alertDialog = builder.create()

        btnNoConfirm.setOnClickListener {
            alertDialog.dismiss()
        }

        btnYesConfirm.setOnClickListener {
            alertDialog.dismiss()
            sendDataToApi()
        }

        alertDialog.show()
    }

    // private fun sendDataToApi(dialog: AlertDialog) {
    //val apiResult = sendDataToApi()

    //    if (apiResult) {
    //         showSuccessDialog()
    //    } else {
    //         showFailureDialog()
    //    }
    // }
    private fun showSuccessDialog() {
        val successDialog = AlertDialog.Builder(this)
            .setTitle("Selamat!")
            .setMessage("Event Anda berhasil didaftarkan.")
            .setPositiveButton("OK") { _, _ ->
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                intent.putExtra("fromAddEvent", true)
                startActivity(intent)
            }
            .create()

        successDialog.show()
    }

    private fun showFailureDialog() {
        val failureDialog = AlertDialog.Builder(this)
            .setTitle("Gagal")
            .setMessage("Sepertinya ada gangguan koneksi. Silakan coba lagi nanti.")
            .setPositiveButton("OK") { _, _ ->

            }
            .create()

        failureDialog.show()
    }
    private fun setViewModelFactory() {
        factory = ViewModelFactory.getInstance(binding.root.context)
    }

    private fun showCustomDialog(message: String, success: Boolean) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(com.puitika.R.layout.fragment_popup_loggedin)

        // Assuming there's a TextView in your layout to display the message
        val messageTextView: TextView = dialog.findViewById(R.id.tv_loggedin)
        messageTextView.text = message
        val checkListImageView: ImageFilterView = dialog.findViewById(R.id.iv_checklist)
        val cancelImageView: ImageFilterView = dialog.findViewById(R.id.iv_cancel)

        if (success) {
            checkListImageView.visibility = View.VISIBLE
            cancelImageView.visibility = View.GONE
        } else {
            checkListImageView.visibility = View.GONE
            cancelImageView.visibility = View.VISIBLE
        }

        dialog.show()

        Handler(Looper.getMainLooper()).postDelayed({
            dialog.dismiss()
        }, 2000)
    }
}

