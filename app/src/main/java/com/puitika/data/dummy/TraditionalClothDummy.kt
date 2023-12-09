package com.puitika.data.dummy

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class TraditionalCloth(
    val error: Boolean,
    val message:String,
    val data: List<DetailTraditionalCloth>
)


@Parcelize
data class DetailTraditionalCloth(
    val id:Int = 1,
    val name:String,
    val description:String = "ha",
    val category: String = "ha",
    val subcategory:String = "ha",
    val origin:String = "ha",
    val imageUrl:String
): Parcelable

val dummyTraditionalCloths = TraditionalCloth(
    error = false,
    message = "Success",
    data = listOf(
        DetailTraditionalCloth(
            id = 1,
            name = "Batik Singo Barong",
            description = "Singo Barong adalah makhluk mitologi Bali yang terkenal akan keseramannya. Namun, pada motif batik Bali ini, Singo Barong dibuat sedemikian rupa menjadi lebih unik dan cantik tanpa mengurangi sisi seram makhluk tersebut. Hal itu bisa dilihat dari gambar Singo Barong yang menyeringai dan bergigi tajam pada motif batik Bali ini. Secara filosofis, motif Singo Barong memiliki makna kekuatan, keperkasaan, serta penolak bala bagi orang-orang yang memakai batik Singo Barong.",
            category = "Batik",
            subcategory = "Batik Tulis",
            origin = "Bali",
            imageUrl = "https://images.unsplash.com/photo-1616125162686-770bf85622b9?q=80&w=3000&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
        ),
        DetailTraditionalCloth(
            id = 2,
            name = "Songket Sedimen",
            description = "Songket Sedimen Bali merupakan salah satu jenis kain songket yang memiliki keunikan tersendiri. Kain ini dihasilkan melalui teknik tenun tradisional yang melibatkan penyisipan benang emas atau perak ke dalam pola tenunan. Songket Sedimen Bali terkenal dengan kekayaan warna dan motifnya yang rumit, menciptakan tampilan yang mewah dan elegan. Motif-motif khasnya sering terinspirasi dari alam, mitologi, atau corak tradisional Bali. Proses pembuatannya membutuhkan keahlian tinggi dari para pengrajin yang secara cermat menenun setiap benang logam untuk menciptakan pola yang indah. Kain songket ini sering digunakan dalam upacara adat, pernikahan, atau sebagai busana khas Bali yang memancarkan keanggunan dan keindahan budaya setempat.",
            category = "Tenun",
            subcategory = "Tenun Songket",
            origin = "Bali",
            imageUrl = "https://plus.unsplash.com/premium_photo-1674478556502-6ae0b6dd02a6?q=80&w=2160&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
        ),
        DetailTraditionalCloth(
            id = 3,
            name = "Songket Beratan",
            description = "Kain Songket Beratan Bali adalah salah satu warisan budaya Bali yang memukau dengan keindahan dan kehalusan tenunannya. Terkenal dengan motif dan warna yang elegan, Songket Beratan Bali menggunakan teknik tradisional tenun yang melibatkan penyisipan benang emas atau perak ke dalam kain. Motif-motif khasnya sering terinspirasi dari alam, simbol-simbol keagamaan, atau desain tradisional Bali. Kain Songket Beratan sering dihasilkan dalam warna-warna cerah dan kontras, menciptakan tampilan yang anggun dan kaya akan simbolisme budaya. Kain ini sering digunakan dalam acara-acara adat, upacara pernikahan, dan acara penting lainnya, memancarkan keanggunan dan kekayaan kultur Bali. Proses pembuatannya membutuhkan keahlian tinggi dari para pengrajin yang menggabungkan tradisi dengan inovasi untuk menciptakan karya seni tekstil yang memukau.",
            category = "Tenun",
            subcategory = "Tenun Songket",
            origin = "Bali",
            imageUrl = "https://plus.unsplash.com/premium_photo-1675798517644-86c59dd1874a?q=80&w=3087&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
        ),
        DetailTraditionalCloth(
            id = 4,
            name = "Batik Buketan Hitam",
            description = "Batik Buketan Hitam merupakan kain batik asal Bali yang menampilkan desain khas dengan motif buketan hitam yang elegan. Motif ini memberikan sentuhan keanggunan dan keindahan pada kain batik, menciptakan kombinasi yang sempurna antara tradisional dan modern. Proses pembuatannya melibatkan teknik batik tulis, di mana setiap motif dan detailnya dikerjakan secara manual dengan menggunakan canting. Batik Buketan Hitam sering dipilih untuk acara-acara formal maupun sehari-hari karena kesan eksklusif dan keunikan desainnya.",
            category = "Batik",
            subcategory = "Batik Tulis",
            origin = "Bali",
            imageUrl = "https://images.unsplash.com/photo-1638780331467-1284e176308d?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTV8fGJhdGlrfGVufDB8fDB8fHww"
        ),
        DetailTraditionalCloth(
            id = 5,
            name = "Batik Burung Bali",
            description = "Batik Burung Bali adalah kain batik yang menggambarkan keindahan burung-burung endemik Pulau Bali. Dengan warna-warna cerah dan motif yang hidup, batik ini menciptakan nuansa kealamian dan keberagaman hayati Bali. Pembuatan Batik Burung Bali melibatkan teknik batik tulis yang memungkinkan setiap detail, termasuk bulu dan bentuk burung, dikerjakan secara teliti oleh para pengrajin. Kain ini sering menjadi pilihan untuk acara-acara yang membutuhkan sentuhan keindahan alam dan kekayaan hayati Bali.",
            category = "Batik",
            subcategory = "Batik Tulis",
            origin = "Bali",
            imageUrl = "https://images.unsplash.com/photo-1575277340549-70f2441dee09?q=80&w=3087&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
        ),
        DetailTraditionalCloth(
            id = 6,
            name = "Ulos Batak Toba",
            description = "Ulos Batak Toba adalah kain tradisional suku Batak Toba yang berasal dari Sumatra Utara. Kain ini memiliki nilai simbolis dalam budaya Batak sebagai lambang kasih sayang, kebahagiaan, dan keberuntungan. Ulos Batak Toba sering diberikan sebagai hadiah pada upacara adat, pernikahan, atau acara penting lainnya. Proses pembuatannya melibatkan tenaga kerja yang handal dan penuh keahlian untuk menghasilkan kain yang halus dan indah.",
            category = "Ulos",
            subcategory = "Ulos Batak Toba",
            origin = "Sumatra Utara",
            imageUrl = "https://images.unsplash.com/photo-1680345575909-99633d4b6f46?q=80&w=2970&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
        ),
        DetailTraditionalCloth(
            id = 7,
            name = "Ulos Ragidup",
            description = "Ulos Ragidup berasal dari suku Batak Karo di Sumatra Utara. Kain ini memiliki makna sakral dalam upacara adat suku Batak Karo. Ulos Ragidup sering digunakan dalam berbagai acara adat, seperti pernikahan dan pemakaman. Desainnya yang kaya akan simbol-simbol tradisional Karo membuat kain ini menjadi bagian penting dari warisan budaya suku Batak Karo.",
            category = "Ulos",
            subcategory = "Ulos Karo",
            origin = "Sumatra Utara",
            imageUrl = "https://images.unsplash.com/photo-1672716912467-fd99b71cf780?q=80&w=2970&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
        ),
        DetailTraditionalCloth(
            id = 8,
            name = "Kain Tenun Sumba",
            description = "Kain Tenun Sumba berasal dari Pulau Sumba, Nusa Tenggara Timur. Kain ini terkenal dengan keindahan motif dan warnanya yang khas. Proses pembuatannya melibatkan tenun tradisional dengan benang tenun yang dihasilkan melalui proses alami. Kain Tenun Sumba sering digunakan dalam upacara adat, seperti pernikahan dan acara keagamaan, sebagai simbol kemakmuran dan keberuntungan.",
            category = "Tenun",
            subcategory = "Tenun Sumba",
            origin = "Nusa Tenggara Timur",
            imageUrl = "https://images.unsplash.com/photo-1515405295579-ba7b45403062?q=80&w=2160&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
        ),
        DetailTraditionalCloth(
            id = 9,
            name = "Kain Ikat Troso",
            description = "Kain Ikat Troso berasal dari Desa Troso, Jawa Tengah. Kain ini dihasilkan melalui teknik ikat, di mana benang dicelupkan dan diikat sebelum proses pewarnaan. Motif kain ini terinspirasi dari alam sekitar dan memiliki keunikan tersendiri. Kain Ikat Troso sering digunakan sebagai busana sehari-hari maupun dalam acara-acara khusus.",
            category = "Ikat",
            subcategory = "Ikat Troso",
            origin = "Jawa Tengah",
            imageUrl = "https://images.unsplash.com/photo-1523483402728-beb8ed707470?q=80&w=2608&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
        ),
        DetailTraditionalCloth(
            id = 10,
            name = "Kain Tenun Lombok",
            description = "Kain Tenun Lombok berasal dari Pulau Lombok, Nusa Tenggara Barat. Kain ini dihasilkan melalui tenun tradisional dengan menggunakan alat tenun manual. Motif dan warnanya mencerminkan kekayaan budaya dan alam Lombok. Kain Tenun Lombok sering digunakan dalam berbagai upacara adat dan acara keagamaan.",
            category = "Tenun",
            subcategory = "Tenun Lombok",
            origin = "Nusa Tenggara Barat",
            imageUrl = "https://images.unsplash.com/photo-1652598908580-360b58f2ac20?q=80&w=3062&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
        ),
    )
)



