package com.prasunmondal.GSheet.Clients.commons

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import org.json.JSONObject
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

    fun getExceptionMessage(): String {
        return ""
    }

    fun getExceptionStackTrace(): String {
        return ""
    }

    fun isOperationLocked(): Boolean {
        return getJsonObject()!!.get(JsonTags.RESPONSE_IS_LOCKED_OPERATION).asBoolean
    }

    override fun toString(): String {
        return "APIResponse(content='$content', statusCode=$statusCode, affectedRows=$affectedRows, opId='$opId', logs='$logs')"
    }

    companion object {
        fun <T> JsonArrayToObjectArray(jsonString: String): List<T> {
            val jsonArrayString = jsonString.trimIndent()
            val gson = Gson()
            val jsonArray = JsonParser().parse(jsonArrayString).asJsonArray
            val contentListType = object : TypeToken<List<T>>() {}.type
            return gson.fromJson(jsonArray, contentListType)
        }

        open fun convertJsonArrayStringToList(jsonArrayString: String?): List<JSONObject> {
            val jsonObjectList: MutableList<JSONObject> = ArrayList()
            try {
                val jsonArray = JSONArray(jsonArrayString)
                for (i in 0 until jsonArray.length()) {
                    jsonObjectList.add(jsonArray.getJSONObject(i))
                }
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
            return jsonObjectList
        }

        fun parseToAPIResponse(jsonString: JSONObject): APIResponse {
            Log.e("parsing to object ", jsonString.toString())
            var result = APIResponse()
            result.opId = jsonString.getString("opId")
            result.affectedRows = try {(jsonString.getString("affectedRows")).toInt()} catch (e: Exception) {0}
            result.statusCode = try {(jsonString.getString("statusCode")).toInt()} catch (e: Exception) {0}
            result.content = jsonString.getString("content")
            result.logs = jsonString.getString("logs")
            return result
        }
    }
}