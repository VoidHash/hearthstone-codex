package com.voidhash.hearthstonecodex.framework.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "info_table")
data class InfoModel(

	@PrimaryKey
	var id: Int = 100,

	@field:SerializedName("patch")
	var patch: String? = null,

	@field:SerializedName("standard")
	var standard: List<String?>? = null,

	@field:SerializedName("types")
	var types: List<String?>? = null,

	@field:SerializedName("sets")
	var sets: List<String?>? = null,

	@field:SerializedName("races")
	var races: List<String?>? = null,

	@field:SerializedName("classes")
	var classes: List<String?>? = null,

	@field:SerializedName("wild")
	var wild: List<String?>? = null
)
