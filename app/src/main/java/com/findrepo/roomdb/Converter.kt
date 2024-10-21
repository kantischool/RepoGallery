package com.findrepo.roomdb

import androidx.room.TypeConverter
import com.findrepo.repogallery.model.item.License
import com.findrepo.repogallery.model.item.Owner
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken




class Converter {
    @TypeConverter
    fun fromStringToOwner(json: String): Owner? {
        return Gson().fromJson(json, Owner::class.java)
    }

    @TypeConverter
    fun fromOwnerToString(customObject: Owner): String {
        return Gson().toJson(customObject)
    }

    @TypeConverter
    fun fromStringToLicense(json: String): License? {
        return Gson().fromJson(json, License::class.java)
    }

    @TypeConverter
    fun fromLicenseToString(customObject: License): String {
        return Gson().toJson(customObject)
    }

    @TypeConverter
    fun fromStringToTopic(json: String): List<String>? {
        return Gson().fromJson(json, object : TypeToken<List<String?>?>() {}.type)
    }

    @TypeConverter
    fun fromTopicToString(customObject: List<String>): String {
        return Gson().toJson(customObject)
    }
}