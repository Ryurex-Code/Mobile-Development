package com.puitika.ui.main.scan

import androidx.lifecycle.ViewModel
import com.puitika.repository.PuitikaRepository
import okhttp3.MultipartBody

class ScanViewModel(private val repository:PuitikaRepository): ViewModel() {
    fun scanCloth(file: MultipartBody.Part) = repository.scanCloth(file)
}