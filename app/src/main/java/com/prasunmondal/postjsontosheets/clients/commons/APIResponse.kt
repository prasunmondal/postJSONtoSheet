package com.prasunmondal.postjsontosheets.clients.commons

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import java.lang.reflect.Type
import java.util.ArrayList

open class APIResponse {
    var responsePayload: String = ""

    fun getRawResponse(): String {
        return this.responsePayload
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

    fun getExceptionMessage(): String {
        return ""
    }

    fun getExceptionStackTrace(): String {
        return ""
    }

    fun isOperationLocked(): Boolean {
        return getJsonObject()!!.get(JsonTags.RESPONSE_IS_LOCKED_OPERATION).asBoolean
    }
}