package com.tech4bytes.mbrosv3.Utils.DB.clients

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.prasunmondal.postjsontosheets.clients.commons.APICalls
import com.prasunmondal.postjsontosheets.clients.commons.APIResponse
import com.prasunmondal.postjsontosheets.clients.commons.ExecutePostCalls
import com.prasunmondal.postjsontosheets.clients.commons.ExecutePostCallsString
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL
import java.util.UUID


class GScriptDuplicateCallKey: Exception()
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

        fun execute(scriptURL: String): APIResponse {
            val scriptUrl = URL(scriptURL)
//            val postDataParams = JSONObject()
            val jsonObjectArray = getCombinedJson()

            val jsonArray = JSONArray()
            for (jsonObject in jsonObjectArray) {
                jsonArray.put(jsonObject)
            }

            val finalJSON = "operations="+jsonArray.toString()
            System.out.println("finalJSON: $finalJSON")

//            val jsonTree = Gson().toJsonTree(finalJSON).asJsonObject
//            val jsonObjectWithOp = JSONObject()
//            jsonObjectWithOp.put("operations", jsonTree)


//            val jsonObject = JSONObject(finalJSON)
            val d = ExecutePostCallsString(scriptUrl, finalJSON) { }//response -> postExecute(response) }
            val response2 = d.execute().get()
            System.out.println(response2.toString())
            return APIResponse()
//            return PostSequenceResponse(response).getObject()
//            postDataParams.put("operations", )
//            postDataParams.put("tabName", this.tabName)
//            postDataParams.put("objectData", Gson().toJson(this.dataObject))

//            val c = ExecutePostCalls(scriptUrl, postDataParams) { response -> postExecute(response) }
//            var response = c.execute().get()
//            return PostObjectResponse(response).getObject()
        }

        fun generateUniqueString(): String {
            val currentTimeMillis = System.currentTimeMillis()
            return UUID.randomUUID().toString() + "-" + currentTimeMillis
        }
    }
}