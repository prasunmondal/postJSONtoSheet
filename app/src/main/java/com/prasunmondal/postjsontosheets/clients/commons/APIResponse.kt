package com.prasunmondal.postjsontosheets.clients.commons

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import java.lang.reflect.Type

open class APIResponse {
    var content: String = ""
    var statusCode: Int = 0
    var affectedRows = 0
    var opId = ""
    var logs = ""

//    fun getContent(): String {
//        return this.content
//    }

//    fun getStatusCode(): Int {
//        return statusCode
//    }

    fun getUID(): Int {
        return getJsonObject()!!.get(JsonTags.RESPONSE_OP_CODE).asInt
    }

    fun getJsonObject(): JsonObject? {
        val parser = JsonParser()
        return parser.parse(content).asJsonObject
    }

    fun <T> parseToObject(jsonString: String?, type: Type): ArrayList<T> {
        Log.e("parsing to object ", jsonString!!)
        var arrayLabel = JsonTags.RESPONSE_DATA_CODE
        var jsonarray: JsonArray? = null
        var result = arrayListOf<T>()
        try {
            jsonarray = getJsonObject()!!.getAsJsonArray(arrayLabel)
            result = GsonBuilder().create().fromJson(jsonarray.toString(), type)
        } catch (e: NullPointerException) {
            Log.e("parseJSONObject", "No value fetched")
        } catch (e: Exception) {
            Log.e("parseJSONObject", "Error while parsing")
        }

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