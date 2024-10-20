package com.findrepo.utility.util

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

fun <T> String?.parseStringToJSON(clazz: Class<T>): T? {
    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    val jsonAdapter = moshi.adapter(clazz)
    val json = try {
        this?.let { jsonAdapter.fromJson(it) }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
    return json
}