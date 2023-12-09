package com.puitika.ui.detail

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.puitika.data.dummy.DetailRegion
import com.puitika.data.dummy.DetailTraditionalCloth
import com.puitika.databinding.ActivityDetailDaerahBinding
import com.puitika.utils.ViewMoreAdapter
import java.io.Serializable

class DetailDaerahActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailDaerahBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailDaerahBinding.inflate(layoutInflater)
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

    private fun setupRecycleView(listKain: List<DetailTraditionalCloth>) {
        val regionAdapter = ViewMoreAdapter(this, listKain)
        binding.rvCloth.apply {
            layoutManager = LinearLayoutManager(
                this@DetailDaerahActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = regionAdapter
        }
    }
}