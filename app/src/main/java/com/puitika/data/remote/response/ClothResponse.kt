package com.puitika.data.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class ClothResponse(

	@field:SerializedName("data")
	val data: List<ClothDetail>,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("status")
	val status: String
) : Parcelable

@Parcelize
data class ClothDetail(

	@field:SerializedName("subCategory")
	val subCategory: String,

	@field:SerializedName("origin")
	val origin: String,

	@field:SerializedName("imageUrl")
	val imageUrl: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("category")
	val category: String
) : Parcelable
