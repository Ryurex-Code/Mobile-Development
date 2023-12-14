package com.puitika.ui.main.event

import android.Manifest
import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.text.InputType
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.puitika.R
import com.puitika.ui.main.home.HomeFragment
import com.puitika.ui.main.main.MainActivity
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
    private lateinit var ivCalender: ImageView

    companion object {
        private const val REQUEST_CODE_PERMISSION = 123
        private const val REQUEST_CODE_PICK_IMAGE = 124
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_event_form)

        val backButton = findViewById<MaterialButton>(R.id.btn_back)
        backButton.setOnClickListener {
            finish()
        }

        val confirmButton = findViewById<Button>(R.id.btn_confirm)
        confirmButton.setOnClickListener {
            sendDataToApi()
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

        // Ganti iv_calender menjadi et_dateday agar bisa menampilkan kalender saat diklik
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
        val imagePath = getImagePathFromImageView(findViewById(R.id.iv_bannerimage))

        // Get selected radio button ID
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val selectedRadioButton = findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
        val eventType = selectedRadioButton.text.toString()

        // TODO: Implement the logic to send data to your API
        // Example: retrofit.create(ApiService::class.java).sendEventData(eventName, eventDateDay, eventDescription, eventType, ticketPrice, contactPerson, organizer, eventLocation, eventTimeStart, eventTimeEnd)
    }

    private fun getImagePathFromImageView(imageView: ImageView): String {
        val drawable = imageView.drawable
        val bitmap = (drawable as BitmapDrawable).bitmap

        val tempDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        val tempFile = File.createTempFile("tempImage", ".jpg", tempDir)
        val outputStream = FileOutputStream(tempFile)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        outputStream.close()

        return tempFile.absolutePath
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
}

