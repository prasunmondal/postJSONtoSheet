package com.prasunmondal.postjsontosheets.clients.fetchAll

import com.google.gson.reflect.TypeToken
import com.prasunmondal.postjsontosheets.JSONUtils
import com.prasunmondal.postjsontosheets.TestClass
import java.lang.reflect.Type
import java.util.ArrayList
import java.util.function.Consumer

class FetchAllResponse {
    lateinit var inboundResponse: String
    var onCompletion: Consumer<String>?

    constructor(onCompletion: Consumer<String>?) {
        this.onCompletion = onCompletion
    }

    @JvmName("setInboundResponse1")
    fun executionComplete(inboundResponse: String) {
        this.inboundResponse = inboundResponse
    }

    fun getRawData(): String {
        return this.inboundResponse
    }

    fun parseData(type: Type) {
        var t2 = TestClass.parseJSONObject(
                object : TypeToken<ArrayList<TestClass>>() {}.type,
                JSONUtils.jsonStringCleanUp(this.inboundResponse)
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