//package com.prasunmondal.GSheet.Clients.commons.newSet.APIRequests.CreateAPIs
//
//import com.google.gson.Gson
//import com.prasunmondal.GSheet.Clients.commons.APIRequests
//import com.prasunmondal.GSheet.Clients.commons.ExecutePostCalls
//import com.prasunmondal.GSheet.Clients.commons.newSet.APIRequests.APIRequests2
//import com.prasunmondal.GSheet.Clients.post.serializable.PostObjectFlow
//import com.prasunmondal.GSheet.Clients.post.serializable.PostObjectResponse
//import org.json.JSONObject
//import java.net.URL
//import java.util.function.Consumer
//
//class InsertObjectUnique : APIRequests2() {
//    private lateinit var scriptURL: String
//    private lateinit var sheetId: String
//    private lateinit var tabName: String
//    private lateinit var dataObject: Any
//    private var uniqueColumn = "";
//
//    fun dataObject(dataObject: Any): PostObjectFlow.FinalRequestBuilder {
//        this.dataObject = dataObject
//        return this
//    }
//
//
//
//    override fun uniqueColumn(uniqueColumn: String): PostObjectFlow.FinalRequestBuilder {
//        if (uniqueColumn == "")
//            return this
//        if (this.uniqueColumn != "")
//            this.uniqueColumn += ","
//        this.uniqueColumn += uniqueColumn
//        return this
//    }
//
//    override fun getJSON(): JSONObject {
////        val scriptUrl = URL(this.scriptURL)
//        val postDataParams = JSONObject()
//        postDataParams.put("opCode", "INSERT_OBJECT")
//        postDataParams.put("sheetId", this.sheetId)
//        postDataParams.put("tabName", this.tabName)
//        postDataParams.put("objectData", Gson().toJson(this.dataObject))
//        return postDataParams
//    }
//
//    fun execute(): PostObjectResponse {
//        return insertUniqueData()
//    }
//
//    private fun insertUniqueData(): PostObjectResponse {
//        val scriptUrl = URL(this.scriptURL)
//        val postDataParams = JSONObject()
//        postDataParams.put("opCode", "INSERT_OBJECT_UNIQUE")
//        postDataParams.put("sheetId", this.sheetId)
//        postDataParams.put("tabName", this.tabName)
//        postDataParams.put("objectData", Gson().toJson(this.dataObject))
//        postDataParams.put("uniqueCol", this.uniqueColumn)
//
//        val c = ExecutePostCalls(scriptUrl, postDataParams) { response -> postExecute(response) }
//        var response = c.execute().get()
//        return PostObjectResponse(response).getObject()
//    }
//}