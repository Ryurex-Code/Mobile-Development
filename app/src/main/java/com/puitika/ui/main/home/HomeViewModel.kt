package com.puitika.ui.main.home

import androidx.lifecycle.ViewModel
import com.puitika.repository.PuitikaRepository

class HomeViewModel(private val repository: PuitikaRepository) : ViewModel() {
    fun getRegions() = repository.getRegions()
    fun getClothes() = repository.getClothes()
}