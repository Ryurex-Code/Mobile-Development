package com.puitika.ui.main.event

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.puitika.BuildConfig.BASE_URL
import com.puitika.data.remote.response.EventDetail
import com.puitika.databinding.ActivityEventDetailBinding


class EventDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEventDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val event = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra("EXTRA_EVENT", EventDetail::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("EXTRA_EVENT")
        }

        if (event != null) {
            setupView(event)
        }
    }

    private fun setupView(event: EventDetail) {
        Glide.with(this).load("${BASE_URL}${event.gambar}").into(binding.zoomableImageView)
        binding.apply {
            tvEventname.text = event.nama
            tvEventtimestart.text = event.mulai
            tvEventtimeend.text = event.selesai
            tvEventdate.text = event.waktu
            tvLocation.text = event.lokasi
            tvDetailEvent.text = event.description
            tvHargaTiketDetail.text = if (event.harga == "Rp 0") "Gratis" else event.harga
            tvContactPersonDetail.text = event.contact
        }
    }
}