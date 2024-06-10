package com.prasunmondal.libs.gsheet.clients.APIRequests.ReadAPIs.FetchData

import com.prasunmondal.libs.gsheet.clients.APIRequests.APIRequests
import com.prasunmondal.libs.gsheet.clients.APIRequests.ReadAPIs.ReadAPIs
import com.prasunmondal.libs.gsheet.clients.APIResponses.APIResponse
import org.json.JSONObject

class GSheetFetchAll<T> : ReadAPIs<T>() {
    override fun getJSON(): JSONObject {
        val postDataParams = JSONObject()
        postDataParams.put("opCode", "FETCH_ALL")
        postDataParams.put("sheetId", this.sheetId)
        postDataParams.put("tabName", this.tabName)
        return postDataParams
    }
}