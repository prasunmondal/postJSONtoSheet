package com.prasunmondal.postjsontosheets.clients.fetchAll

import com.google.gson.reflect.TypeToken
import com.prasunmondal.postjsontosheets.JSONUtils
import com.prasunmondal.postjsontosheets.TestClass
import java.lang.reflect.Type
import java.util.ArrayList

class FetchAllResponse {
    lateinit var responsePayload: String
    var responseReceived = false

    constructor(responsePayload: String) {
        this.responsePayload = responsePayload
    }

    fun getObject(): FetchAllResponse {
        return this
    }

    @JvmName("setInboundResponse1")
    fun setInboundResponse(inboundResponse: String) {
        println("tt: response received: true")
        this.responseReceived = true
        this.responsePayload = inboundResponse
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
        return 0;
    }

    fun getExceptionMessage(): String {
        return ""
    }

    fun getExceptionStackTrace(): String {
        return ""
    }
}