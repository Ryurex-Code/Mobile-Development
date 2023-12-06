package com.puitika.ui.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.puitika.R
import com.puitika.ui.login.LoginActivity

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Find the TextView by its ID
        val tvLogOut = findViewById<TextView>(R.id.tv_log_out)
        val rectangle5 = findViewById<View>(R.id.rectangle_5)

        // Set an OnClickListener on the TextView
        tvLogOut.setOnClickListener {
            // Create an Intent to start the LoginActivity
            val intent = Intent(this, LoginActivity::class.java)


            // You can add any additional logic or data passing here if needed

            // Start the LoginActivity
            startActivity(intent)
        }
    }
}