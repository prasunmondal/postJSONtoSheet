package com.prasunmondal.GSheet.Clients.commons.newSet

import com.prasunmondal.GSheet.Clients.commons.APIRequests
import com.prasunmondal.GSheet.Clients.commons.APIResponse
import com.prasunmondal.GSheet.Clients.commons.ExecutePostCalls
import com.prasunmondal.GSheet.Clients.commons.ExecutePostCallsString
import com.prasunmondal.GSheet.Clients.commons.newSet.APIRequests.APIRequests2
import com.prasunmondal.GSheet.Clients.post.serializable.PostObjectResponse
import com.tech4bytes.mbrosv3.Utils.DB.clients.GScript
import com.tech4bytes.mbrosv3.Utils.DB.clients.GScriptDuplicateCallKey
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL
import java.util.UUID
import java.util.function.Consumer

abstract class GScript {

    lateinit var scriptURL: String
    lateinit var json: JSONObject
    var onCompletion: Consumer<PostObjectResponse>? = null

    abstract fun getJSON(): JSONObject

    fun execute(scriptURL: String): PostObjectResponse {
//        GScript.addRequest(this)
//        GScript.execute(scriptURL)
//
        return PostObjectResponse("")

//        val scriptUrl = URL(scriptURL)
//
//        val c = ExecutePostCalls(scriptUrl, getJSON()) { response -> postExecute(response) }
//        var response = c.execute().get()
//        return PostObjectResponse(response).getObject()
    }
    fun postExecute(response: String) {
        if (onCompletion == null)
            return
        var responseObj = PostObjectResponse(response)
        onCompletion!!.accept(responseObj)
    }

    fun setPostCompletion(onCompletion: Consumer<PostObjectResponse>?) {
        this.onCompletion = onCompletion
    }

    companion object {
        var calls = mutableMapOf<String, APIRequests2>()

        fun addRequest(apiCall: APIRequests2?): String? {
            if(apiCall == null)
                return null

            val uid = generateUniqueString()
            addRequest(uid, apiCall)
            return uid
        }

        fun addRequest(uid: String, apiCall: APIRequests2) {
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

//            calls.forEach { t, u ->
//                u.
//            }

            calls.clear()
            return map
        }

        fun generateUniqueString(): String {
            val currentTimeMillis = System.currentTimeMillis()
            return UUID.randomUUID().toString() + "-" + currentTimeMillis
        }
    }
}