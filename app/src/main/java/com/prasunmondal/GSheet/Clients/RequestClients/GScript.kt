package com.tech4bytes.mbrosv3.Utils.DB.clients

import com.prasunmondal.GSheet.Clients.commons.APICalls
import com.prasunmondal.GSheet.Clients.commons.APIResponse
import com.prasunmondal.GSheet.Clients.commons.ExecutePostCallsString
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL
import java.util.UUID


class GScriptDuplicateCallKey : Exception()
class GScript {
    companion object {
        var calls = mutableMapOf<String, APICalls>()

        fun add(apiCall: APICalls): String {
            val uid = generateUniqueString()
            add(uid, apiCall)
            return uid
        }

        fun add(uid: String, apiCall: APICalls) {
            if (calls.containsKey(uid)) {
                throw GScriptDuplicateCallKey()
            }
            calls[uid] = apiCall
        }

        fun getCombinedJson(): Array<JSONObject> {
            var jsonArray = mutableListOf<JSONObject>()
            calls.forEach { uid, apiCall ->
                val requestJson = apiCall.getJSON()
                requestJson.put("opId", uid)
                jsonArray.add(requestJson)
            }
            return jsonArray.toTypedArray()
        }

        fun execute(scriptURL: String): MutableMap<String, APIResponse> {
            val scriptUrl = URL(scriptURL)
            val jsonObjectArray = getCombinedJson()

            val jsonArray = JSONArray()
            for (jsonObject in jsonObjectArray) {
                jsonArray.put(jsonObject)
            }
            val finalRequestJSON = "operations=$jsonArray"
            val d = ExecutePostCallsString(scriptUrl, finalRequestJSON) { }//response -> postExecute(response) }
            val response2 = d.execute().get()

            System.out.println("response2: $response2")

            val apiResponsesList = APIResponse.convertJsonArrayStringToList(response2)
            val map: MutableMap<String, APIResponse> = mutableMapOf()
            for(apiResponse in apiResponsesList) {
                map[apiResponse.get("opId").toString()] = APIResponse.parseToAPIResponse(apiResponse)
            }
            calls.clear()
            return map
        }

        fun generateUniqueString(): String {
            val currentTimeMillis = System.currentTimeMillis()
            return UUID.randomUUID().toString() + "-" + currentTimeMillis
        }
    }
}