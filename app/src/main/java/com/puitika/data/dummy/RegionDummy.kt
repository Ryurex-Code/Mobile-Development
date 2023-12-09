package com.puitika.data.dummy

data class Region(
    val error: Boolean,
    val message: String,
    val data: List<DetailRegion>
)

data class DetailRegion(
    val id: Int,
    val name: String,
    val description: String,
    val imageURL: String,
    val listKain: List<DetailCloth>
)

data class DetailCloth(
    val nama: String,
    val url: String
)

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
                    DetailCloth("Ulos Sadum", "api-puitika.com/files/kainName/sadum.jpg"),
                    DetailCloth("Ulos Ragi Idup", "api-puitika.com/files/kainName/ragiidup.jpg"),
                    DetailCloth("Ulos Sibolang", "api-puitika.com/files/kainName/sibolang.jpg"),
                    DetailCloth("Ulos Mangiring", "api-puitika.com/files/kainName/mangiring.jpg"),
                    DetailCloth("Ulos Bintang Maratur", "api-puitika.com/files/kainName/bm.jpg")
                )
            ),
            DetailRegion(
                id = 2,
                name = "Riau",
                description = "Provinsi Riau, yang terletak di pantai tengah timur Sumatra, ditandai oleh keberagaman budayanya, dengan pengaruh dari komunitas Melayu, Minangkabau, dan Tionghoa. Daerah ini terkenal dengan produksi batiknya, di mana pola dan motif yang rumit sering diilhami oleh alam dan cerita rakyat tradisional. Batik Riau dikenal dengan desain elegannya dan penggunaan warna-warna alam. Pembuatan batik di Riau seringkali merupakan kegiatan komunal, melibatkan para pengrajin yang mahir mewariskan teknik mereka dari generasi ke generasi.",
                imageURL = "https://images.unsplash.com/photo-1698285608712-5ebb1c0b21fe?q=80&w=2624&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                listKain = listOf(
                    DetailCloth(
                        "Batik Bunga Riau",
                        "api-puitika.com/files/kainName/ambatukam-1.jpg"
                    ),
                    DetailCloth(
                        "Songket Siku Awan",
                        "api-puitika.com/files/kainName/ambatukam-2.jpg"
                    ),
                    DetailCloth(
                        "Songket Siku Keluang",
                        "api-puitika.com/files/kainName/ambatukam-3.jpg"
                    ),
                    DetailCloth(
                        "Songket Pucuk Rebung",
                        "api-puitika.com/files/kainName/ambatukam-4.jpg"
                    ),
                    DetailCloth(
                        "Songket Wajik Sempurna",
                        "api-puitika.com/files/kainName/ambatukam-5.jpg"
                    ),
                    DetailCloth(
                        "Songket Awan Larat",
                        "api-puitika.com/files/kainName/ambatukam-6.jpg"
                    )
                )
            ),
            DetailRegion(
                id = 3,
                name = "Palembang",
                description = "Palembang, yang terletak di Sumatera Selatan, memiliki latar belakang sejarah yang kaya sebagai pusat perdagangan. Daerah ini dikenal dengan songketnya yang unik, yang ditandai dengan pola rumit dan penggunaan benang emas dan perak. Songket Palembang sering menampilkan motif yang diilhami oleh alam, mitologi, dan peristiwa sejarah. Kerajinan tenun songket sangat melekat dalam identitas budaya daerah ini dan diwariskan dari generasi ke generasi, dengan para pengrajin yang mahir menggunakan teknik tradisional untuk membuat tekstil yang indah ini.",
                imageURL = "https://images.unsplash.com/photo-1604361726591-d50d6483a0aa?q=80&w=3017&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                listKain = listOf(
                    DetailCloth("Songket Limar", "api-puitika.com/files/kainName/ambatukam-1.jpg"),
                    DetailCloth(
                        "Songket Bungo Pacik",
                        "api-puitika.com/files/kainName/ambatukam-2.jpg"
                    ),
                    DetailCloth("Songket Lepus", "api-puitika.com/files/kainName/ambatukam-3.jpg"),
                    DetailCloth("Songket Tawur", "api-puitika.com/files/kainName/ambatukam-4.jpg"),
                    DetailCloth("Songket Tretes", "api-puitika.com/files/kainName/ambatukam-5.jpg")
                )
            ),
            DetailRegion(
                id = 4,
                name = "Lombok",
                description = "Lombok, sebuah pulau di timur Bali, terkenal dengan budaya Sasak dan pemandangannya yang indah. Pulau ini menghasilkan tekstil tradisional yang indah, termasuk songket dan ikat. Songket Lombok menampilkan pola rumit dengan benang emas dan perak, sering digunakan dalam pakaian upacara. Ikat, teknik pewarnaan ikat, juga sangat menonjol dengan warna-warna cerah dan desain geometris. Tekstil Lombok mencerminkan kekayaan budaya pulau ini dan keahlian tinggi para pembuatnya.",
                imageURL = "https://images.unsplash.com/photo-1605752660759-2db7b7de8fa9?q=80&w=2970&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                listKain = listOf(
                    DetailCloth(
                        "Songket Merak Lombok",
                        "api-puitika.com/files/kainName/ambatukam-1.jpg"
                    ),
                    DetailCloth(
                        "Songket Wayang Lombok",
                        "api-puitika.com/files/kainName/ambatukam-2.jpg"
                    ),
                    DetailCloth(
                        "Tenun Subhanale",
                        "api-puitika.com/files/kainName/ambatukam-3.jpg"
                    ),
                    DetailCloth(
                        "Songket Bintang Empat",
                        "api-puitika.com/files/kainName/ambatukam-4.jpg"
                    ),
                    DetailCloth("Songket Keker", "api-puitika.com/files/kainName/ambatukam-5.jpg")
                )
            ),
            DetailRegion(
                id = 5,
                name = "Sumatera Barat",
                description = "Sumatra Barat adalah rumah bagi suku Minangkabau, yang terkenal dengan struktur sosial matrilineal dan arsitektur uniknya. Daerah ini terkenal dengan songketnya, sejenis kain tenun yang dihiasi dengan benang logam. Songket Minangkabau ditandai dengan motif rumit yang sering menggambarkan pemandangan alam atau peristiwa sejarah. Warna-warna cerah dan pola yang rinci membuat songket Minangkabau menjadi kain yang dicari, digunakan dalam upacara tradisional dan sebagai simbol identitas budaya.",
                imageURL = "https://images.unsplash.com/photo-1654593777128-d187ed9820e5?q=80&w=3174&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                listKain = listOf(
                    DetailCloth(
                        "Songket Tanah Liek",
                        "api-puitika.com/files/kainName/ambatukam-1.jpg"
                    ),
                    DetailCloth(
                        "Songket Pandai Sikek",
                        "api-puitika.com/files/kainName/ambatukam-2.jpg"
                    ),
                    DetailCloth(
                        "Songket Silungkang",
                        "api-puitika.com/files/kainName/ambatukam-3.jpg"
                    ),
                    DetailCloth(
                        "Batik Rangkiang",
                        "api-puitika.com/files/kainName/ambatukam-4.jpg"
                    ),
                    DetailCloth(
                        "Batik Lengkuak Daun Pakis",
                        "api-puitika.com/files/kainName/ambatukam-5.jpg"
                    )
                )
            ),
            DetailRegion(
                id = 6,
                name = "Bali",
                description = "Bali, destinasi wisata populer, terkenal dengan seni dan budayanya yang berwarna-warni. Batik Bali adalah bentuk seni tekstil yang mencolok, dengan pola rumit yang diilhami oleh mitologi Hindu, alam, dan kehidupan sehari-hari. Pulau ini juga menghasilkan songket, yang digunakan dalam upacara tradisional dan pernikahan Bali. Warna dan motif dalam tekstil Bali sering membawa makna simbolis, dan kerajinan yang terlibat dalam pembuatannya sangat dihargai.",
                imageURL = "https://plus.unsplash.com/premium_photo-1668883188861-39974ed9ad99?q=80&w=2400&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                listKain = listOf(
                    DetailCloth(
                        "Songket Sedimen",
                        "api-puitika.com/files/kainName/ambatukam-5.jpg"
                    ),
                    DetailCloth(
                        "Batik Burung Bali",
                        "api-puitika.com/files/kainName/ambatukam-1.jpg"
                    ),
                    DetailCloth(
                        "Batik Merak Abyorhokokai",
                        "api-puitika.com/files/kainName/ambatukam-2.jpg"
                    ),
                    DetailCloth("Batik Sating", "api-puitika.com/files/kainName/ambatukam-3.jpg"),
                    DetailCloth(
                        "Batik Singo Barong",
                        "api-puitika.com/files/kainName/ambatukam-4.jpg"
                    ),
                    DetailCloth(
                        "Batik Ulamsari Mas",
                        "api-puitika.com/files/kainName/ambatukam-5.jpg"
                    ),
                    DetailCloth(
                        "Songket Beratan",
                        "api-puitika.com/files/kainName/ambatukam-6.jpg"
                    ),
                    DetailCloth(
                        "Batik Buketan Hitam",
                        "api-puitika.com/files/kainName/ambatukam-7.jpg"
                    )
                )
            )
        )

    )


