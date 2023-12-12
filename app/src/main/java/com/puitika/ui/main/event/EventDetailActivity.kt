package com.puitika.ui.main.event

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.puitika.data.dummy.DetailEvent
import com.puitika.data.dummy.Event
import com.puitika.databinding.ActivityEventDetailBinding
import com.puitika.ui.detail.region_detail.ViewMoreAdapter

class EventDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEventDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the event ID from the intent
        val event = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra("EXTRA_EVENT", DetailEvent::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("EXTRA_EVENT")
        }

        if (event != null) {
            setupView(event)
            setupRecycleView(event)
        }
    }

    private fun setupView(event: DetailEvent) {
        Glide.with(this).load(event.BannerURL).into(binding.ivImageView2)
        binding.apply {
            tvEventname.text = event.Name
            tvEventtime.text = event.Time
            tvEventdate.text = event.Date
            tvLocation.text = event.Location
            tvDetailEvent.text = event.Description
        }
    }

    private fun setupRecycleView(detailEvents: List<DetailEvent>) {
        val eventAdapter = ViewMoreAdapter(this, detailEvents)
        binding.tvDetailEvent.apply {
            layoutManager = LinearLayoutManager(
                this@EventDetailActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = eventAdapter
        }
    }
}
