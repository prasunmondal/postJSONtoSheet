package com.prasunmondal.postjsontosheets.clients.fetchAll

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import com.prasunmondal.postjsontosheets.JSONUtils
import com.prasunmondal.postjsontosheets.TestClass
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

    fun parseData(type: Type) {
        var t2 = TestClass.parseJSONObject(
                object : TypeToken<ArrayList<TestClass>>() {}.type,
                JSONUtils.jsonStringCleanUp(this.responsePayload)
        )
        println("Check -- Parsed Object: $t2")
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