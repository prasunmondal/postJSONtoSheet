package com.prasunmondal.libs.gsheet.clients

import com.prasunmondal.libs.Logs.LogMe
import com.prasunmondal.libs.gsheet.clients.APIRequests.APIRequests
import com.prasunmondal.libs.gsheet.clients.APIRequests.ReadAPIs.ReadAPIs
import com.prasunmondal.libs.gsheet.clients.APIResponses.APIResponse
import com.prasunmondal.libs.gsheet.clients.responseCaching.ResponseCache
import com.prasunmondal.libs.gsheet.exceptions.GScriptDuplicateUIDException
import com.prasunmondal.libs.gsheet.post.serializable.PostObjectResponse
import com.prasunmondal.libs.gsheet.serializer.parsers.Parser
import org.json.JSONArray
import org.json.JSONObject
import java.io.Serializable
import java.net.URL
import java.util.UUID
import java.util.function.Consumer

abstract class GScript: Serializable {

    lateinit var scriptURL: String
    lateinit var json: JSONObject
    var onCompletion: Consumer<PostObjectResponse>? = null

    abstract fun getJSON(): JSONObject

    // TODO: add direct execution
    fun execute(scriptURL: String): APIResponse {
        addRequest(this as APIRequests)
        val responseMap = Companion.execute(scriptURL)
        return responseMap.values.iterator().next()

//        return PostObjectResponse("")
//
//        val scriptUrl = URL(scriptURL)
//
//        val c = ExecutePostCalls(scriptUrl, getJSON()) { response -> postExecute(response) }
//        var response = c.execute().get()
//        return PostObjectResponse(response).getObject()
    }

    fun postExecute(response: String) {
        if (onCompletion == null)
            return
        val responseObj = PostObjectResponse(response)
        onCompletion!!.accept(responseObj)
    }

    fun setPostCompletion(onCompletion: Consumer<PostObjectResponse>?) {
        this.onCompletion = onCompletion
    }

    companion object {
        var calls = mutableMapOf<String, APIRequests>()

        fun addRequest(apiCall: APIRequests?): String? {
            if (apiCall == null)
                return null

            val uid = apiCall.getUId()
            addRequest(uid, apiCall)
            return uid
        }

        fun addRequest(apiCallsList: List<APIRequests>) {
            apiCallsList.forEach {
                addRequest(it)
            }
        }

        fun addRequest(uid: String, apiCall: APIRequests) {
            if (calls.containsKey(uid)) {
                throw GScriptDuplicateUIDException()
            }
            calls[uid] = apiCall
        }

        fun getCombinedJson(): Array<JSONObject> {
            val jsonArray = mutableListOf<JSONObject>()
            calls.forEach { (uid, apiCall) ->
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
            val d = ExecutePostCallsString(
                scriptUrl,
                finalRequestJSON
            ) { }//response -> postExecute(response) }
            val response2 = d.execute().get()

            LogMe.log("response2: $response2")

            val apiResponsesList = Parser.convertJsonArrayStringToJsonObjList(response2)
            val map: MutableMap<String, APIResponse> = mutableMapOf()
            for (apiResponse in apiResponsesList) {
                val responseOpId = apiResponse.get("opId").toString()
                val requestObj = calls[responseOpId]
                map[responseOpId] = APIResponse.parseToAPIResponse(apiResponse)

                val preparedResponse = requestObj!!.prepareResponse(requestObj, map[responseOpId]!!, null)

                // Enable caching of responses only for read APIs
                if(requestObj is ReadAPIs<*>) {
                    ResponseCache.saveToLocal(requestObj, preparedResponse)
                }
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