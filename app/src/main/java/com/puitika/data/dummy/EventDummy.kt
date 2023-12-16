package com.puitika.data.dummy

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class Event(
    val error: Boolean,
    val message: String,
    val data: List<DetailEvent>
)

@Parcelize
data class DetailEvent(
    val id: Int,
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
    val gambar: String,
): Parcelable

val eventList = Event(
    error = false,
    message = "Success",
    data = listOf(
        DetailEvent(
            id = 1,
            nama = " Festival Tenun Tradisional Bugis ",
            waktu = "Sabtu, 10 Desember 2023",
            description = "Ikuti festival tenun Bugis tradisional kami di mana Anda dapat merasakan warisan budaya kaya dari Tenun. Jelajahi berbagai tekstil tenunan tangan dan pelajari seni tenun yang rumit. Masuk gratis!",
            jenis = "Terbuka",
            harga = "Rp. 0",
            contact = "08123456789",
            penyelenggara = "Penyelenggara Event 1",
            lokasi = "Rumah adat Saoraja Latenri Bali",
            mulai = "10.00am",
            selesai = "11.00pm",
            gambar = "https://katasatu.co.id/wp-content/uploads/2021/11/Spanduk-FBK-copy-scaled.jpg"
        ),
        DetailEvent(
            id = 2,
            nama = "Festival Batik Bordir Tenun & Scarf 2020",
            waktu = "Minggu, 15 Januari 2024",
            description = "Ikuti festival Batik Bordir Tenun & Scarf kami yang akan menampilkan ragam batik, bordir, dan scarf terbaik. Acara ini tertutup dan tiket dapat dibeli seharga Rp. 150.000. Hubungi kami di 08234567890 untuk informasi lebih lanjut.",
            jenis = "Tertutup",
            harga = "Rp. 150.000",
            contact = "08234567890",
            penyelenggara = "Penyelenggara Event 2",
            lokasi = "Lokasi Event 2",
            mulai = "11.00am",
            selesai = "10.00pm",
            gambar = "https://www.loveindonesia.com/images/thingstodo/3849/web.jpg"
        ),
        DetailEvent(
            id = 3,
            nama = "Lomba Desain Motif Tenun Ikat Kota Kediri",
            waktu = "Selasa, 20 Februari 2024",
            description = "Ikuti lomba desain motif tenun ikat di Kota Kediri, yang dihadiri oleh Wali Kota Kediri. Lomba ini terbuka untuk umum, dan tidak ada biaya pendaftaran. Hubungi kami di 08765432100 untuk informasi lebih lanjut.",
            jenis = "Terbuka",
            harga = "Rp. 0",
            contact = "08765432100",
            penyelenggara = "Penyelenggara Event 3",
            lokasi = "Lokasi Event 3",
            mulai = "2.00pm",
            selesai = "7.00pm",
            gambar = "https://beritajatim.com/wp-content/uploads/2022/07/IMG-20220707-WA0037.jpg"
        ),
        DetailEvent(
            id = 4,
            nama = "Festival Batik Bordir Tenun Nusantara 2018",
            waktu = "Kamis, 5 Maret 2024",
            description = "Ikuti Festival Batik Bordir Tenun Nusantara 2018 yang menghadirkan karya-karya terbaik dari seluruh Nusantara. Acara ini tertutup, dan tiket bisa dibeli seharga Rp. 200.000. Hubungi kami di 08987654321 untuk pembelian tiket.",
            jenis = "Tertutup",
            harga = "Rp. 200.000",
            contact = "08987654321",
            penyelenggara = "Penyelenggara Event 4",
            lokasi = "Lokasi Event 4",
            mulai = "9.00am",
            selesai = "5.00pm",
            gambar = "https://media.goopps.com/upload/module/b_event/event-1554446698-1889.jpg"
        ),
        DetailEvent(
            id = 5,
            nama = "Festival Batik Bordir Tenun Jumputan 2018",
            waktu = "Sabtu, 15 April 2024",
            description = "Rasakan keindahan Batik Jumputan di festival terbuka kami. Bergabunglah untuk satu hari penuh warna-warni, desain kreatif, dan pertunjukan budaya. Tiket tersedia seharga Rp. 50.000. Jangan lewatkan perayaan ekspresi artistik ini!",
            jenis = "Terbuka",
            harga = "Rp. 50.000",
            contact = "08112233445",
            penyelenggara = "Penyelenggara Event 5",
            lokasi = "Lokasi Event 5",
            mulai = "3.00pm",
            selesai = "10.00pm",
            gambar = "https://1.bp.blogspot.com/-DbbbpYHE1lg/WuBUmu5LRhI/AAAAAAAAraI/3uWC2m98t5c7rncrUAMD8j0HE_Qfiyg1gCLcBGAs/s1600/Festival%2BBatik%252C%2BBordir%2Bdan%2BTenun%2BNusantara%2B2018.jpg"
        )
    )
)

