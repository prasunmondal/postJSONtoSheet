package com.prasunmondal.libs.gsheet.clients.APIRequests.ReadAPIs.CheckData

import com.prasunmondal.libs.gsheet.clients.APIRequests.ReadAPIs.ReadAPIs
import org.json.JSONObject

// TODO: fix - returns 500 when sheet is empty
class GSheetCheckDataPresence : ReadAPIs<CheckResult>() {
    private var keys = ""
    private var values = ""
    override var classTypeForResponseParsing = CheckResult::class.java

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