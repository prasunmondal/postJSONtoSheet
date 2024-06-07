package com.prasunmondal.GSheet.Clients.commons.newSet.APIRequests.ReadAPIs.CheckData

import com.prasunmondal.GSheet.Clients.commons.newSet.APIRequests.ReadAPIs.ReadAPIs
import org.json.JSONObject

class CheckDataPresence: ReadAPIs() {
    private var keys = ""
    private var values = ""

    fun keys(keys: String) {
        this.keys = keys
    }

    fun values(values: String) {
        this.values = values
    }

    override fun getJSON(): JSONObject {
        val postDataParams = JSONObject()
        postDataParams.put("opCode", "IS_PRESENT_CONDITIONAL_AND")
        postDataParams.put("sheetId", this.sheetId)
        postDataParams.put("tabName", this.tabName)
        postDataParams.put("dataColumn", keys)
        postDataParams.put("dataValue", values)
        return postDataParams
    }
}