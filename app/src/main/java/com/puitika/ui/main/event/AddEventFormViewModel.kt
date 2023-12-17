package com.puitika.ui.main.event

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.puitika.data.model.CreateEventModel
import com.puitika.data.remote.response.CreateEventResponse
import com.puitika.data.request.CreateEventRequest
import com.puitika.repository.PuitikaRepository
import com.puitika.utils.Result

class AddEventFormViewModel(private val repository: PuitikaRepository) : ViewModel() {
    fun createEvent(createEventModel: CreateEventModel): LiveData<Result<CreateEventResponse>> {
        val resultLiveData = MutableLiveData<Result<CreateEventResponse>>()

        val createEventRequest = CreateEventRequest(
            nama = createEventModel.nama,
            waktu = createEventModel.waktu,
            description = createEventModel.description,
            jenis = createEventModel.jenis,
            harga = createEventModel.harga,
            contact = createEventModel.contact,
            penyelenggara = createEventModel.penyelenggara,
            lokasi = createEventModel.lokasi,
            mulai = createEventModel.mulai,
            selesai = createEventModel.selesai,
            gambar = createEventModel.gambar
        )

        repository.createEvent(createEventRequest).observeForever { result ->
            resultLiveData.value = result
        }

        return resultLiveData
    }
}