package com.voidhash.hearthstonecodex.framework.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "card_back_table")
data class CardBackModel(

	@PrimaryKey
	@field:SerializedName("cardBackId")
	var cardBackId: Int? = null,

	@field:SerializedName("img")
	var img: String? = null,

	@field:SerializedName("sortCategory")
	var sortCategory: Int? = null,

	@field:SerializedName("sortOrder")
	var sortOrder: Int? = null,

	@field:SerializedName("name")
	var name: String? = null,

	@field:SerializedName("description")
	var description: String? = null,

	@field:SerializedName("imgAnimated")
	var imgAnimated: String? = null,

	@field:SerializedName("howToGet")
	var howToGet: String? = null,

	@field:SerializedName("source")
	var source: String? = null,

	@field:SerializedName("locale")
	var locale: String? = null,

	@field:SerializedName("enabled")
	var enabled: Boolean? = null
)
