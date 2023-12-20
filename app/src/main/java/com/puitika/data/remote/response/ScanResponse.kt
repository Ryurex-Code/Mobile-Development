package com.puitika.data.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class ScanResponse(

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("prediksi")
	val prediksi: List<PrediksiItem>
) : Parcelable

@Parcelize
data class PrediksiItem(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("region")
	val region: String,

	@field:SerializedName("percent")
	val percent: String
) : Parcelable
