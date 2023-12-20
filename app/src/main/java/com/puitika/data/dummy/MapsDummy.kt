package com.puitika.data.dummy

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Maps(
    val error: Boolean,
    val message: String,
    val data: List<RegionCoordinates>
) : Parcelable

@Parcelize
data class RegionCoordinates(
    val id: Int,
    val name: String,
    val latitude: Double,
    val longitude: Double
) : Parcelable

val mapsList = Maps(
    error = false,
    message = "Success",
    data = listOf(
        RegionCoordinates(
            id = 1,
            name = "Sumatera Utara",
            latitude = 3.5952,
            longitude = 98.6722,
        ),
        RegionCoordinates(
            id = 2,
            name = "Riau",
            latitude = 0.5116,
            longitude = 101.446),
        RegionCoordinates(
            id = 3,
            name = "Palembang",
            latitude = -2.9761,
            longitude = 104.7754),
        RegionCoordinates(
            id = 4,
            name = "Lombok",
            latitude = -8.6500,
            longitude = 116.3242),
        RegionCoordinates(
            id = 5,
            name = "Sumatera Barat",
            latitude = -0.7893,
            longitude = 100.6528),
        RegionCoordinates(
            id = 6,
            name = "Bali",
            latitude = -8.3405,
            longitude = 115.0919),
        RegionCoordinates(
            id = 7,
            name = "Kalimantan Selatan",
            latitude = -3.3194,
            longitude = 114.5907),
        RegionCoordinates(
            id = 8,
            name = "Jawa Barat",
            latitude = -6.2088,
            longitude = 106.8456)
    )
)



