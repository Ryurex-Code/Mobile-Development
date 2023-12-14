package com.puitika.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AccountModel (
    val username: String?,
    val email :String?,
    val apiKey: String?
) : Parcelable