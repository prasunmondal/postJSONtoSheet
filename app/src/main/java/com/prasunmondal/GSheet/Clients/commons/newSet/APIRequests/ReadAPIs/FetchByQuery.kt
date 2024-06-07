package com.prasunmondal.GSheet.Clients.commons.newSet.APIRequests.ReadAPIs

import org.json.JSONObject
class FetchByQuery: ReadRequest() {
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