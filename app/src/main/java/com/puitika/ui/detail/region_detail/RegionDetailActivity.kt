package com.puitika.ui.detail.region_detail

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.puitika.data.remote.response.RegionCloth
import com.puitika.data.remote.response.RegionDetail
import com.puitika.databinding.ActivityRegionDetailBinding

class RegionDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegionDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegionDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val region = if(Build.VERSION.SDK_INT >= 33){
            intent.getParcelableExtra<DetailRegion>("EXTRA_REGION", DetailRegion::class.java)

        }else{
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<DetailRegion>("EXTRA_REGION")
        }

        if(region!=null){
            setupView(region)
            setupRecycleView(region.listKain)
        }

    }

    private fun setupView(region: DetailRegion) {
        Glide.with(this).load(region.imageURL).into(binding.ivRegion)
        binding.apply {
            tvRegion.text = region.name
            tvDescription.text = region.description
        }
    }

    private fun setupRecycleView(listKain: List<DetailCloth>) {
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
}