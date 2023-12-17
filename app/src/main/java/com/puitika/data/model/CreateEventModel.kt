package com.puitika.data.model

data class CreateEventModel(
    val nama: String,
    val waktu: String,
    val description: String,
    val jenis: String,
    val harga: String,
    val contact: String,
    val penyelenggara: String,
    val lokasi: String,
    val mulai: String,
    val selesai: String,
    val gambar: String
)