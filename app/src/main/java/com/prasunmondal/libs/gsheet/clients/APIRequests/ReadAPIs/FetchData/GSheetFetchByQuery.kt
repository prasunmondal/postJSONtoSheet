package com.prasunmondal.libs.gsheet.clients.APIRequests.ReadAPIs.FetchData

import com.prasunmondal.libs.gsheet.clients.APIRequests.APIRequests
import com.prasunmondal.libs.gsheet.clients.APIRequests.ReadAPIs.ReadAPIs
import com.prasunmondal.libs.gsheet.clients.APIResponses.APIResponse
import org.json.JSONObject

class GSheetFetchByQuery<T> : ReadAPIs<T>() {
    lateinit var query: String
    override fun getJSON(): JSONObject {
        val postDataParams = JSONObject()
        postDataParams.put("opCode", "FETCH_BY_QUERY")
        postDataParams.put("sheetId", this.sheetId)
        postDataParams.put("tabName", this.tabName)
        postDataParams.put("query", this.query)
        return postDataParams
    }

    fun query(query: String) {
        this.query = query
    }
}