package com.puitika.ui.detail.region_detail

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.puitika.R
import com.puitika.data.remote.response.RegionCloth
import com.puitika.data.remote.response.RegionDetail
import com.puitika.data.dummy.RegionCoordinates
import com.puitika.data.dummy.mapsList
import com.puitika.databinding.ActivityRegionDetailBinding
import com.puitika.utils.showToast

class RegionDetailActivity : AppCompatActivity() {

    private var isFavorite = false // Menyim
    private lateinit var binding: ActivityRegionDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegionDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mapView = binding.mapView
        mapView.onCreate(savedInstanceState)

        mapView.getMapAsync { googleMap ->
            val region = getRegionFromIntent()
            if (region != null) {
                val location = getRegionCoordinates(region.name)
                if (location != null) {
                    val dummyLocation = LatLng(location.latitude, location.longitude)
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(dummyLocation, 8f))
                    googleMap.addMarker(MarkerOptions().position(dummyLocation).title("Lokasi ${region.name}"))
                }
            }
        }

        mapView.setOnClickListener {
            val region = getRegionFromIntent()
            if (region != null) {
                val location = getRegionCoordinates(region.name)
                if (location != null) {
                    openGoogleMaps(location.latitude, location.longitude, region.name)
                }
            }
        }

        val region = getRegionFromIntent()
        if (region != null) {
            setupView(region)
            setupRecycleView(region.listKain)
        }
        val favoriteButton: ImageView = findViewById(R.id.ib_favorite)
        setFavoriteIcon()
        favoriteButton.setOnClickListener {
            showToast(this, "Coming Soon!")
            isFavorite = !isFavorite
            setFavoriteIcon()
        }
    }

    private fun getRegionFromIntent(): RegionDetail? {
        return if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra("EXTRA_REGION", RegionDetail::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("EXTRA_REGION")
        }
    }

    private fun getRegionCoordinates(regionName: String): RegionCoordinates? {
        return mapsList.data.find { it.name == regionName }
    }

    private fun openGoogleMaps(latitude: Double, longitude: Double, label: String) {
        val gmmIntentUri: Uri = Uri.parse("geo:$latitude,$longitude?q=$latitude,$longitude($label)")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")

        if (mapIntent.resolveActivity(packageManager) != null) {
            startActivity(mapIntent)
        } else {
            Toast.makeText(this, "Google Maps app not installed", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapView.onDestroy()
    }

    private fun setupView(region: RegionDetail) {
        Glide.with(this).load(region.imageURL).into(binding.ivRegion)
        binding.apply {
            tvRegion.text = region.name
            tvDescription.text = region.description
        }
    }

    private fun setupRecycleView(listKain: List<RegionCloth>) {
        val regionAdapter = ViewMoreAdapter(this, listKain)
        binding.rvCloth.apply {
            layoutManager = LinearLayoutManager(
                this@RegionDetailActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = regionAdapter
        }
    }
    private fun setFavoriteIcon() {
        val favoriteButton: ImageView = findViewById(R.id.ib_favorite)
        val newIcon = if (isFavorite) R.drawable.favorite_fill_ic else R.drawable.favorite_ic
        favoriteButton.setImageResource(newIcon)
    }
}

