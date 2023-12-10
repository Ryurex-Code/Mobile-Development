package com.puitika.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AccountModel (
    val username: String? = null,
    val email :String? = null,
    val token: String? = null
) : Parcelable