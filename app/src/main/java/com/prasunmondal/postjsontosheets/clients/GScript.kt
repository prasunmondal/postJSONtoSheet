package com.tech4bytes.mbrosv3.Utils.DB.clients

import com.prasunmondal.postjsontosheets.clients.commons.APICalls
import java.util.UUID




class GScriptDuplicateCallKey: Exception()
class GScript {
    companion object {
        var calls = mutableMapOf<String, APICalls>()

        fun add(apiCall: APICalls) {
            add(generateUniqueString(), apiCall)
        }

        fun add(key: String, apiCall: APICalls) {
            if (calls.containsKey(key)) {
                throw GScriptDuplicateCallKey()
            }
            calls[key] = apiCall
        }

        fun execute() {
            calls.forEach { key, apiCall ->
                apiCall.execute()
            }
            calls = mutableMapOf()
        }

        fun generateUniqueString(): String {
            val currentTimeMillis = System.currentTimeMillis()
            return UUID.randomUUID().toString() + "-" + currentTimeMillis
        }
    }
}