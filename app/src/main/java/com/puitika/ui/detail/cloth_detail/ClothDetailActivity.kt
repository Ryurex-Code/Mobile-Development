package com.puitika.ui.detail.cloth_detail

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.puitika.data.remote.response.ClothDetail
import com.puitika.databinding.ActivityClothDetailBinding

class ClothDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityClothDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClothDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cloth = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra("EXTRA_CLOTH", ClothDetail::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("EXTRA_CLOTH")
        }

        if(cloth!=null){
            setupView(cloth)
        }
    }

    private fun setupView(cloth: ClothDetail) {
        Glide.with(this).load(cloth.imageUrl).into(binding.ivCloth)
        binding.apply {
            tvCloth.text = cloth.name
            tvDescription.text = cloth.description
            tvCategory.text = cloth.category
            tvSubCategory.text = cloth.subcategory
        }
    }

}