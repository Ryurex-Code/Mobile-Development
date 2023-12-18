package com.puitika.ui.main.event

import androidx.lifecycle.ViewModel
import com.puitika.repository.PuitikaRepository

class EventViewModel(private val repository: PuitikaRepository) : ViewModel() {
    fun getEvents() = repository.getEvents()
}