package com.prasunmondal.GSheet.Clients.commons.newSet.APIRequests.DeleteAPIs

import org.json.JSONObject

class DeleteAll: DeleteAPIs() {
    override fun getJSON(): JSONObject {
        val postDataParams = JSONObject()
        postDataParams.put("opCode", "DELETE_ALL")
        postDataParams.put("sheetId", this.sheetId)
        postDataParams.put("tabName", this.tabName)
        return postDataParams
    }
}