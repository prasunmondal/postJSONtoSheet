package com.prasunmondal.postjsontosheets.clients.commons

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
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

    override fun toString(): String {
        return "APIResponse(content='$content', statusCode=$statusCode, affectedRows=$affectedRows, opId='$opId', logs='$logs')"
    }

    companion object {
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
//    companion object {
//        fun <T> parseToObject(jsonString: String?, type: Type): ArrayList<T> {
//            Log.e("parsing to object ", jsonString!!)
//            var arrayLabel = JsonTags.RESPONSE_DATA_CODE
//            var jsonarray: JsonArray? = null
//            var result = arrayListOf<T>()
//            try {
//                jsonarray = getJsonObject()!!.getAsJsonArray(arrayLabel)
//                result = GsonBuilder().create().fromJson(jsonarray.toString(), type)
//            } catch (e: NullPointerException) {
//                Log.e("parseJSONObject", "No value fetched")
//            } catch (e: Exception) {
//                Log.e("parseJSONObject", "Error while parsing")
//            }
//
//            return result
//        }
//    }
}