package com.prasunmondal.postjsontosheets.clients.fetchAll

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.prasunmondal.postjsontosheets.clients.JsonTags
import java.lang.reflect.Type
import java.util.ArrayList

class FetchAllResponse {
    var responsePayload: String

    constructor(responsePayload: String) {
        this.responsePayload = responsePayload
    }

    fun getObject(): FetchAllResponse {
        return this
    }

    fun getRawData(): String {
        return this.responsePayload
    }

    fun parseToObject(jsonString: String?, type: Type): ArrayList<*> {
        Log.e("parsing to object ", jsonString!!)
        var arrayLabel = JsonTags.RESPONSE_DATA_CODE
        var jsonarray: JsonArray? = null
        try {
            jsonarray = getJsonObject()!!.getAsJsonArray(arrayLabel)
        } catch (e: Exception) {
            Log.e("parseJSONObject", "Error while parsing")
        }
        val result: ArrayList<*> = GsonBuilder().create().fromJson(jsonarray.toString(), type)
        return result
    }

    fun getResponseCode(): Int {
        return getJsonObject()!!.get(JsonTags.RESPONSE_RESPONSE_CODE).asInt
    }

    fun getOpCode(): Int {
        return getJsonObject()!!.get(JsonTags.RESPONSE_OP_CODE).asInt
    }

    fun getJsonObject(): JsonObject? {
        val parser = JsonParser()
        return parser.parse(responsePayload).asJsonObject
    }

    fun getExceptionMessage(): String {
        return ""
    }

    fun getExceptionStackTrace(): String {
        return ""
    }
}