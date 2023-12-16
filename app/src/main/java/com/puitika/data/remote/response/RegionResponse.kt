package com.puitika.data.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class RegionResponse(

	@field:SerializedName("data")
	val data: List<RegionDetail>,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("status")
	val status: String
) : Parcelable

@Parcelize
data class RegionDetail(

	@field:SerializedName("imageURL")
	val imageURL: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("listKain")
	val listKain: List<RegionCloth>,

	@field:SerializedName("id")
	val id: Int
) : Parcelable

@Parcelize
data class RegionCloth(

	@field:SerializedName("imageUrl")
	val imageUrl: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("name")
	val name: String
) : Parcelable
