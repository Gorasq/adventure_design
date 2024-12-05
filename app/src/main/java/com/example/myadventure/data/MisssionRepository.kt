package com.example.myadventure.data

import android.content.Context
import com.example.myadventure.model.Mission
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MissionRepository(private val context: Context) {
    private val gson = Gson()

    // JSON 파일에서 미션 데이터를 로드
    fun loadMissions(): List<Mission> {
        val rawResource = context.resources.openRawResource(
            context.resources.getIdentifier("mission", "raw", context.packageName)
        )
        val json = rawResource.bufferedReader().use { it.readText() }
        val type = object : TypeToken<List<Mission>>() {}.type
        return gson.fromJson(json, type)
    }
}
