package com.puitika.ui.main.event

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.puitika.data.dummy.DetailEvent
import com.puitika.databinding.ActivityEventDetailBinding

class EventDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEventDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val event = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra("EXTRA_EVENT", DetailEvent::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("EXTRA_EVENT")
        }

        if (event != null) {
            setupView(event)
        }
    }

    private fun setupView(event: DetailEvent) {
        Glide.with(this).load(event.gambar).into(binding.ivImageview2)
        binding.apply {
            tvEventname.text = event.nama
            tvEventtimestart.text = event.mulai
            tvEventtimeend.text = event.selesai
            tvEventdate.text = event.waktu
            tvLocation.text = event.lokasi
            tvDetailEvent.text = event.description
            tvHargaTiketDetail.text = event.harga
            tvContactPersonDetail.text = event.contact

        }
    }
}