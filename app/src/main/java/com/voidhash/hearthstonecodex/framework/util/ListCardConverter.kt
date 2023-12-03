package com.voidhash.hearthstonecodex.framework.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.voidhash.hearthstonecodex.framework.model.MechanicsItem
import com.voidhash.hearthstonecodex.framework.model.RuneCost
import java.lang.reflect.Type

class ListCardConverter {

    private val gson = Gson()
    private val typeString: Type = object : TypeToken<List<String>>() {}.type
    private val typeRuneCost: Type = object : TypeToken<RuneCost>() {}.type
    private val typeMechanics: Type = object : TypeToken<List<MechanicsItem>>() {}.type

    @TypeConverter
    fun fromString(json: String?): List<String>? {
        return if(!json.isNullOrBlank())
            gson.fromJson(json, typeString)
        else
            null
    }

    @TypeConverter
    fun fromList(list: List<String?>?): String? {
        return if(!list.isNullOrEmpty())
            gson.toJson(list, typeString)
        else
            null
    }

    @TypeConverter
    fun fromStringToRuneCost(json: String?): RuneCost? {
        return if(!json.isNullOrBlank())
            gson.fromJson(json, typeRuneCost)
        else
            null
    }

    @TypeConverter
    fun fromRuneCostToString(runeCost: RuneCost?): String? {
        return if(runeCost == null)
            null
        else
            gson.toJson(runeCost, typeRuneCost)
    }

    @TypeConverter
    fun fromStringToMechanicsItem(json: String?): List<MechanicsItem?>? {
        return if(!json.isNullOrBlank())
            gson.fromJson(json, typeMechanics)
        else
            null
    }

    @TypeConverter
    fun fromMechanicsItemToString(list: List<MechanicsItem?>?): String? {
        return if(!list.isNullOrEmpty())
            gson.toJson(list, typeMechanics)
        else
            null
    }
}