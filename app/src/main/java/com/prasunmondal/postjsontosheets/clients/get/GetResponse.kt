package com.prasunmondal.postjsontosheets.clients.get

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonParser
import com.prasunmondal.postjsontosheets.clients.commons.APIResponse
import com.prasunmondal.postjsontosheets.clients.commons.JSONUtils
import java.lang.reflect.Type
import java.util.ArrayList

class GetResponse: APIResponse {

    constructor(responsePayload: String) {
        this.responsePayload = responsePayload
    }

    fun getObject(): GetResponse {
        return this
    }

    fun getParsedList(type: Type): ArrayList<*> {
        var jsonString = JSONUtils.jsonStringCleanUp(this.getRawResponse())
        Log.e("parsing: ", jsonString!!)
        var arrayLabel = "records"
        val parser = JsonParser()
        val jsonObject = parser.parse(jsonString).asJsonObject
        var jsonarray: JsonArray? = null
        try {
            jsonarray = jsonObject.getAsJsonArray(arrayLabel)
        } catch (e: Exception) {
            Log.e("parseJSONObject", "Error while parsing")
        }
        val result: ArrayList<*> = GsonBuilder().create().fromJson(
            jsonarray.toString(),
            type
        )
        return result
    }
}