package com.puitika.data.dummy

data class Event(
    val error: Boolean,
    val message: String,
    val data: List<DetailEvent>
)

data class DetailEvent(
    val id: Int,
    val eventName: String,
    val eventDate: String,
    val eventDescription: String,
    val eventType: String,
    val ticketPrice: String,
    val contactPerson: String,
    val organizer: String,
    val eventLocation: String,
    val eventTime: String,
    val eventBannerURL: String,
)

val eventList = Event(
    error = false,
    message = "Success",
    data = listOf(
        DetailEvent(
            id = 1,
            eventName = "Nama Event 1",
            eventDate = "Sabtu, 10 Desember 2023",
            eventDescription = "Deskripsi Event 1",
            eventType = "Terbuka",
            ticketPrice = "Rp. 0",
            contactPerson = "08123456789",
            organizer = "Penyelenggara Event 1",
            eventLocation = "Lokasi Event 1",
            eventTime = "10.00am - 11.00pm",
            eventBannerURL = "https://katasatu.co.id/wp-content/uploads/2021/11/Spanduk-FBK-copy-scaled.jpg"
        ),
        DetailEvent(
            id = 2,
            eventName = "Nama Event 2",
            eventDate = "Minggu, 15 Januari 2024",
            eventDescription = "Deskripsi Event 2",
            eventType = "Tertutup",
            ticketPrice = "Rp. 150.000",
            contactPerson = "08234567890",
            organizer = "Penyelenggara Event 2",
            eventLocation = "Lokasi Event 2",
            eventTime = "11.00am - 10.00pm",
            eventBannerURL = "https://www.loveindonesia.com/images/thingstodo/3849/web.jpg"
        ),
        DetailEvent(
            id = 3,
            eventName = "Nama Event 3",
            eventDate = "Selasa, 20 Februari 2024",
            eventDescription = "Deskripsi Event 3",
            eventType = "Terbuka",
            ticketPrice = "Rp. 0",
            contactPerson = "08765432100",
            organizer = "Penyelenggara Event 3",
            eventLocation = "Lokasi Event 3",
            eventTime = "2.00pm - 7.00pm",
            eventBannerURL = "https://beritajatim.com/wp-content/uploads/2022/07/IMG-20220707-WA0037.jpg"
        ),
        DetailEvent(
            id = 4,
            eventName = "Nama Event 4",
            eventDate = "Kamis, 5 Maret 2024",
            eventDescription = "Deskripsi Event 4",
            eventType = "Tertutup",
            ticketPrice = "Rp. 200.000",
            contactPerson = "08987654321",
            organizer = "Penyelenggara Event 4",
            eventLocation = "Lokasi Event 4",
            eventTime = "9.00am - 5.00pm",
            eventBannerURL = "https://media.goopps.com/upload/module/b_event/event-1554446698-1889.jpg"
        ),
        DetailEvent(
            id = 5,
            eventName = "Nama Event 5",
            eventDate = "Sabtu, 15 April 2024",
            eventDescription = "Deskripsi Event 5",
            eventType = "Terbuka",
            ticketPrice = "Rp. 50.000",
            contactPerson = "08112233445",
            organizer = "Penyelenggara Event 5",
            eventLocation = "Lokasi Event 5",
            eventTime = "3.00pm - 10.00pm",
            eventBannerURL = "https://1.bp.blogspot.com/-DbbbpYHE1lg/WuBUmu5LRhI/AAAAAAAAraI/3uWC2m98t5c7rncrUAMD8j0HE_Qfiyg1gCLcBGAs/s1600/Festival%2BBatik%252C%2BBordir%2Bdan%2BTenun%2BNusantara%2B2018.jpg"
        )
    )
)
