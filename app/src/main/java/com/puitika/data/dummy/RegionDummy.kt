package com.puitika.data.dummy

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

data class Region(
    val error: Boolean,
    val message: String,
    val data: List<DetailRegion>
)

@Parcelize
data class DetailRegion(
    val id: Int,
    val name: String,
    val description: String,
    val imageURL: String,
    val listKain: List<DetailTraditionalCloth>
):Parcelable

val regionList =
    Region(
        error = false,
        message = "Success",
        data = listOf(
            DetailRegion(
                id = 1,
                name = "Sumatera Utara",
                description = "Sumatera Utara adalah provinsi yang terletak di bagian utara Sumatra, Indonesia. Daerah ini dikenal dengan keberagaman budayanya, dengan pengaruh yang signifikan dari suku Batak. Orang Batak terkenal dengan adat istiadat, tarian, dan musik tradisionalnya yang khas. Dalam seni tekstil, daerah ini menghasilkan batik dan ulos (tekstil tradisional Batak) yang unik dengan pola geometris dan warna yang cerah. Motif pada kain mereka sering mencerminkan warisan budaya yang kaya dan kepercayaan spiritual orang Batak.",
                imageURL = "https://images.unsplash.com/photo-1440558929809-1412944a6225?q=80&w=3129&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                listKain = listOf(
                    DetailTraditionalCloth(
                        name = "Ulos Sadum",
                        category = "das",
                        description = "das",
                        id = 1,
                        origin = "fsd",
                        subcategory = "fdsa",
                        imageUrl = "https://1.bp.blogspot.com/-VzH3dvv3Tuw/T7-2zVp57mI/AAAAAAAADQI/16_jFF7RyJQ/s400/tenun_ikat.JPG"
                    ),
                    DetailTraditionalCloth(
                        name = "Ulos Ragi Idup",
                        imageUrl = "https://assets-a1.kompasiana.com/items/album/2019/06/28/img-20190628-214712-5d161c850d82305ab43fe883.jpg"
                    ),
                    DetailTraditionalCloth(
                        name = "Ulos Sibolang",
                        imageUrl = "https://images.tokopedia.net/img/cache/700/VqbcmM/2020/7/30/b84cc018-a89f-48b0-b20d-c1f67f73a100.jpg"
                    ),
                    DetailTraditionalCloth(
                        name = "Ulos Mangiring",
                        imageUrl = "https://static.promediateknologi.id/crop/0x0:0x0/750x500/webp/photo/2022/05/11/2239340502.jpeg"
                    ),
                    DetailTraditionalCloth(
                        name = "Ulos Bintang Maratur",
                        imageUrl = "https://s1.bukalapak.com/img/67672046111/s-400-400/data.png.webp"
                    )
                )
            ),
            DetailRegion(
                id = 2,
                name = "Riau",
                description = "Provinsi Riau, yang terletak di pantai tengah timur Sumatra, ditandai oleh keberagaman budayanya, dengan pengaruh dari komunitas Melayu, Minangkabau, dan Tionghoa. Daerah ini terkenal dengan produksi batiknya, di mana pola dan motif yang rumit sering diilhami oleh alam dan cerita rakyat tradisional. Batik Riau dikenal dengan desain elegannya dan penggunaan warna-warna alam. Pembuatan batik di Riau seringkali merupakan kegiatan komunal, melibatkan para pengrajin yang mahir mewariskan teknik mereka dari generasi ke generasi.",
                imageURL = "https://images.unsplash.com/photo-1698285608712-5ebb1c0b21fe?q=80&w=2624&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                listKain = listOf(
                    DetailTraditionalCloth(
                        name = "Batik Bunga Riau",
                        imageUrl = "api-puitika.com/files/kainName/ambatukam-1.jpg"
                    ),
                    DetailTraditionalCloth(
                        name = "Songket Siku Awan",
                        imageUrl = "api-puitika.com/files/kainName/ambatukam-2.jpg"
                    ),
                    DetailTraditionalCloth(
                        name = "Songket Siku Keluang",
                        imageUrl = "api-puitika.com/files/kainName/ambatukam-3.jpg"
                    ),
                    DetailTraditionalCloth(
                        name = "Songket Pucuk Rebung",
                        imageUrl = "api-puitika.com/files/kainName/ambatukam-4.jpg"
                    ),
                    DetailTraditionalCloth(
                        name = "Songket Wajik Sempurna",
                        imageUrl = "api-puitika.com/files/kainName/ambatukam-5.jpg"
                    ),
                    DetailTraditionalCloth(
                        name = "Songket Awan Larat",
                        imageUrl = "api-puitika.com/files/kainName/ambatukam-6.jpg"
                    )
                )
            ),
            DetailRegion(
                id = 3,
                name = "Palembang",
                description = "Palembang, yang terletak di Sumatera Selatan, memiliki latar belakang sejarah yang kaya sebagai pusat perdagangan. Daerah ini dikenal dengan songketnya yang unik, yang ditandai dengan pola rumit dan penggunaan benang emas dan perak. Songket Palembang sering menampilkan motif yang diilhami oleh alam, mitologi, dan peristiwa sejarah. Kerajinan tenun songket sangat melekat dalam identitas budaya daerah ini dan diwariskan dari generasi ke generasi, dengan para pengrajin yang mahir menggunakan teknik tradisional untuk membuat tekstil yang indah ini.",
                imageURL = "https://images.unsplash.com/photo-1604361726591-d50d6483a0aa?q=80&w=3017&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                listKain = listOf(
                    DetailTraditionalCloth(
                        name = "Songket Limar",
                        imageUrl = "api-puitika.com/files/kainName/ambatukam-1.jpg"
                    ),
                    DetailTraditionalCloth(
                        name = "Songket Bungo Pacik",
                        imageUrl = "api-puitika.com/files/kainName/ambatukam-2.jpg"
                    ),
                    DetailTraditionalCloth(
                        name = "Songket Lepus",
                        imageUrl = "api-puitika.com/files/kainName/ambatukam-3.jpg"
                    ),
                    DetailTraditionalCloth(
                        name = "Songket Tawur",
                        imageUrl = "api-puitika.com/files/kainName/ambatukam-4.jpg"
                    ),
                    DetailTraditionalCloth(
                        name = "Songket Tretes",
                        imageUrl = "api-puitika.com/files/kainName/ambatukam-5.jpg"
                    )
                )
            ),
            DetailRegion(
                id = 4,
                name = "Lombok",
                description = "Lombok, sebuah pulau di timur Bali, terkenal dengan budaya Sasak dan pemandangannya yang indah. Pulau ini menghasilkan tekstil tradisional yang indah, termasuk songket dan ikat. Songket Lombok menampilkan pola rumit dengan benang emas dan perak, sering digunakan dalam pakaian upacara. Ikat, teknik pewarnaan ikat, juga sangat menonjol dengan warna-warna cerah dan desain geometris. Tekstil Lombok mencerminkan kekayaan budaya pulau ini dan keahlian tinggi para pembuatnya.",
                imageURL = "https://images.unsplash.com/photo-1605752660759-2db7b7de8fa9?q=80&w=2970&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                listKain = listOf(
                    DetailTraditionalCloth(
                        name = "Songket Merak Lombok",
                        imageUrl = "api-puitika.com/files/kainName/ambatukam-1.jpg"
                    ),
                    DetailTraditionalCloth(
                        name = "Songket Wayang Lombok",
                        imageUrl = "api-puitika.com/files/kainName/ambatukam-2.jpg"
                    ),
                    DetailTraditionalCloth(
                        name = "Tenun Subhanale",
                        imageUrl = "api-puitika.com/files/kainName/ambatukam-3.jpg"
                    ),
                    DetailTraditionalCloth(
                        name = "Songket Bintang Empat",
                        imageUrl = "api-puitika.com/files/kainName/ambatukam-4.jpg"
                    ),
                    DetailTraditionalCloth(
                        name = "Songket Keker",
                        imageUrl = "api-puitika.com/files/kainName/ambatukam-5.jpg"
                    )
                )
            ),
            DetailRegion(
                id = 5,
                name = "Sumatera Barat",
                description = "Sumatra Barat adalah rumah bagi suku Minangkabau, yang terkenal dengan struktur sosial matrilineal dan arsitektur uniknya. Daerah ini terkenal dengan songketnya, sejenis kain tenun yang dihiasi dengan benang logam. Songket Minangkabau ditandai dengan motif rumit yang sering menggambarkan pemandangan alam atau peristiwa sejarah. Warna-warna cerah dan pola yang rinci membuat songket Minangkabau menjadi kain yang dicari, digunakan dalam upacara tradisional dan sebagai simbol identitas budaya.",
                imageURL = "https://images.unsplash.com/photo-1654593777128-d187ed9820e5?q=80&w=3174&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                listKain = listOf(
                    DetailTraditionalCloth(
                        name = "Songket Tanah Liek",
                        imageUrl = "api-puitika.com/files/kainName/ambatukam-1.jpg"
                    ),
                    DetailTraditionalCloth(
                        name = "Songket Pandai Sikek",
                        imageUrl = "api-puitika.com/files/kainName/ambatukam-2.jpg"
                    ),
                    DetailTraditionalCloth(
                        name = "Songket Silungkang",
                        imageUrl = "api-puitika.com/files/kainName/ambatukam-3.jpg"
                    ),
                    DetailTraditionalCloth(
                        name = "Batik Rangkiang",
                        imageUrl = "api-puitika.com/files/kainName/ambatukam-4.jpg"
                    ),
                    DetailTraditionalCloth(
                        name = "Batik Lengkuak Daun Pakis",
                        imageUrl = "api-puitika.com/files/kainName/ambatukam-5.jpg"
                    )
                )
            ),
            DetailRegion(
                id = 6,
                name = "Bali",
                description = "Bali, destinasi wisata populer, terkenal dengan seni dan budayanya yang berwarna-warni. Batik Bali adalah bentuk seni tekstil yang mencolok, dengan pola rumit yang diilhami oleh mitologi Hindu, alam, dan kehidupan sehari-hari. Pulau ini juga menghasilkan songket, yang digunakan dalam upacara tradisional dan pernikahan Bali. Warna dan motif dalam tekstil Bali sering membawa makna simbolis, dan kerajinan yang terlibat dalam pembuatannya sangat dihargai.",
                imageURL = "https://plus.unsplash.com/premium_photo-1668883188861-39974ed9ad99?q=80&w=2400&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                listKain = listOf(
                    DetailTraditionalCloth(
                        name = "Songket Sedimen",
                        imageUrl = "api-puitika.com/files/kainName/ambatukam-5.jpg"
                    ),
                    DetailTraditionalCloth(
                        name = "Batik Burung Bali",
                        imageUrl = "api-puitika.com/files/kainName/ambatukam-1.jpg"
                    ),
                    DetailTraditionalCloth(
                        name = "Batik Merak Abyorhokokai",
                        imageUrl = "api-puitika.com/files/kainName/ambatukam-2.jpg"
                    ),
                    DetailTraditionalCloth(
                        name = "Batik Sating",
                        imageUrl = "api-puitika.com/files/kainName/ambatukam-3.jpg"
                    ),
                    DetailTraditionalCloth(
                        name = "Batik Singo Barong",
                        imageUrl = "api-puitika.com/files/kainName/ambatukam-4.jpg"
                    ),
                    DetailTraditionalCloth(
                        name = "Batik Ulamsari Mas",
                        imageUrl = "api-puitika.com/files/kainName/ambatukam-5.jpg"
                    ),
                    DetailTraditionalCloth(
                        name = "Songket Beratan",
                        imageUrl = "api-puitika.com/files/kainName/ambatukam-6.jpg"
                    ),
                    DetailTraditionalCloth(
                        name = "Batik Buketan Hitam",
                        imageUrl = "api-puitika.com/files/kainName/ambatukam-7.jpg"
                    )
                )
            )
        )

    )


