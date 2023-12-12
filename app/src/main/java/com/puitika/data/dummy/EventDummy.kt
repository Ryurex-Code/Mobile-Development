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
    val Name: String,
    val Date: String,
    val Description: String,
    val Type: String,
    val ticketPrice: String,
    val contactPerson: String,
    val organizer: String,
    val Location: String,
    val Time: String,
    val BannerURL: String,
): Parcelable

val eventList = Event(
    error = false,
    message = "Success",
    data = listOf(

        DetailEvent(
            id = 1,
            Name = "Nama Event 1",
            Date = "Sabtu, 10 Desember 2023",
            Description = "Lorem ipsum maktanuanv a ashbianfiuauc nusiw fun sausn uqw9fn nsuiu is fhqn ufvmamoa fi iqnfu  efueu q0ao sj weewew 09na meki ansuifna pukmak miaf kuufqf 7f f\n" +
                    "affa faf jijnifa ujoamf unf jiqinqinqif iqnifninfq \n" +
                    "asfjania asfnahfjasf fonajf oinfoamfokaf aoino fooa foia f oaj fjajlka vogwoimg oignofoka f ooif qo fo fonfo  ",
            Type = "Terbuka",
            ticketPrice = "Rp. 0",
            contactPerson = "08123456789",
            organizer = "Penyelenggara Event 1",
            Location = "Lokasi Event 1",
            Time = "10.00am - 11.00pm",
            BannerURL = "https://katasatu.co.id/wp-content/uploads/2021/11/Spanduk-FBK-copy-scaled.jpg"
        ),
        DetailEvent(
            id = 2,
            Name = "Nama Event 2",
            Date = "Minggu, 15 Januari 2024",
            Description = "Lorem ipsum maktanuanv a ashbianfiuauc nusiw fun sausn uqw9fn nsuiu is fhqn ufvmamoa fi iqnfu  efueu q0ao sj weewew 09na meki ansuifna pukmak miaf kuufqf 7f f\n" +
                    "affa faf jijnifa ujoamf unf jiqinqinqif iqnifninfq \n" +
                    "asfjania asfnahfjasf fonajf oinfoamfokaf aoino fooa foia f oaj fjajlka vogwoimg oignofoka f ooif qo fo fonfo ",
            Type = "Tertutup",
            ticketPrice = "Rp. 150.000",
            contactPerson = "08234567890",
            organizer = "Penyelenggara Event 2",
            Location = "Lokasi Event 2",
            Time = "11.00am - 10.00pm",
            BannerURL = "https://www.loveindonesia.com/images/thingstodo/3849/web.jpg"
        ),
        DetailEvent(
            id = 3,
            Name = "Nama Event 3",
            Date = "Selasa, 20 Februari 2024",
            Description = "Lorem ipsum maktanuanv a ashbianfiuauc nusiw fun sausn uqw9fn nsuiu is fhqn ufvmamoa fi iqnfu  efueu q0ao sj weewew 09na meki ansuifna pukmak miaf kuufqf 7f f\n" +
                    "affa faf jijnifa ujoamf unf jiqinqinqif iqnifninfq \n" +
                    "asfjania asfnahfjasf fonajf oinfoamfokaf aoino fooa foia f oaj fjajlka vogwoimg oignofoka f ooif qo fo fonfo ",
            Type = "Terbuka",
            ticketPrice = "Rp. 0",
            contactPerson = "08765432100",
            organizer = "Penyelenggara Event 3",
            Location = "Lokasi Event 3",
            Time = "2.00pm - 7.00pm",
            BannerURL = "https://beritajatim.com/wp-content/uploads/2022/07/IMG-20220707-WA0037.jpg"
        ),
        DetailEvent(
            id = 4,
            Name = "Nama Event 4",
            Date = "Kamis, 5 Maret 2024",
            Description = "Lorem ipsum maktanuanv a ashbianfiuauc nusiw fun sausn uqw9fn nsuiu is fhqn ufvmamoa fi iqnfu  efueu q0ao sj weewew 09na meki ansuifna pukmak miaf kuufqf 7f f\n" +
                    "affa faf jijnifa ujoamf unf jiqinqinqif iqnifninfq \n" +
                    "asfjania asfnahfjasf fonajf oinfoamfokaf aoino fooa foia f oaj fjajlka vogwoimg oignofoka f ooif qo fo fonfo ",
            Type = "Tertutup",
            ticketPrice = "Rp. 200.000",
            contactPerson = "08987654321",
            organizer = "Penyelenggara Event 4",
            Location = "Lokasi Event 4",
            Time = "9.00am - 5.00pm",
            BannerURL = "https://media.goopps.com/upload/module/b_event/event-1554446698-1889.jpg"
        ),
        DetailEvent(
            id = 5,
            Name = "Nama Event 5",
            Date = "Sabtu, 15 April 2024",
            Description = "Lorem ipsum maktanuanv a ashbianfiuauc nusiw fun sausn uqw9fn nsuiu is fhqn ufvmamoa fi iqnfu  efueu q0ao sj weewew 09na meki ansuifna pukmak miaf kuufqf 7f f\n" +
                    "affa faf jijnifa ujoamf unf jiqinqinqif iqnifninfq \n" +
                    "asfjania asfnahfjasf fonajf oinfoamfokaf aoino fooa foia f oaj fjajlka vogwoimg oignofoka f ooif qo fo fonfo ",
            Type = "Terbuka",
            ticketPrice = "Rp. 50.000",
            contactPerson = "08112233445",
            organizer = "Penyelenggara Event 5",
            Location = "Lokasi Event 5",
            Time = "3.00pm - 10.00pm",
            BannerURL = "https://1.bp.blogspot.com/-DbbbpYHE1lg/WuBUmu5LRhI/AAAAAAAAraI/3uWC2m98t5c7rncrUAMD8j0HE_Qfiyg1gCLcBGAs/s1600/Festival%2BBatik%252C%2BBordir%2Bdan%2BTenun%2BNusantara%2B2018.jpg"
        )
    )
)
