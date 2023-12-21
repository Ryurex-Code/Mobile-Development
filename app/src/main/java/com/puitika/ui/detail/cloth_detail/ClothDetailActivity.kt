package com.puitika.ui.detail.cloth_detail

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.puitika.R
import com.puitika.data.remote.response.ClothDetail
import com.puitika.databinding.ActivityClothDetailBinding
import com.puitika.utils.showToast

class ClothDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityClothDetailBinding
    private var isFavorite = false

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
        val favoriteButton: ImageView = findViewById(R.id.ib_favorite)
        setFavoriteIcon()
        favoriteButton.setOnClickListener {
            showToast(this, "Coming Soon!")
            isFavorite = !isFavorite
            setFavoriteIcon()
        }
    }

    private fun setupView(cloth: ClothDetail) {
        Glide.with(this).load(cloth.imageUrl).into(binding.ivCloth)
        binding.apply {
            tvCloth.text = cloth.name
            tvDescription.text = cloth.description
            tvCategory.text = cloth.category
            tvSubCategory.text = cloth.subCategory
        }
    }

    private fun setFavoriteIcon() {
        val favoriteButton: ImageView = findViewById(R.id.ib_favorite)
        val newIcon = if (isFavorite) R.drawable.favorite_fill_ic else R.drawable.favorite_ic
        favoriteButton.setImageResource(newIcon)
    }
}