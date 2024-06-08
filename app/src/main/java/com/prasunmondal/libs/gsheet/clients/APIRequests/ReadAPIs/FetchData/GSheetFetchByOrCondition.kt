package com.prasunmondal.libs.gsheet.clients.APIRequests.ReadAPIs.FetchData

import com.prasunmondal.libs.gsheet.clients.APIRequests.ReadAPIs.ReadAPIs
import org.json.JSONObject

class GSheetFetchByOrCondition: ReadAPIs() {
    private var conditionOrColumn = ""
    private var conditionOrValue = ""

    override fun getJSON(): JSONObject {
        val postDataParams = JSONObject()
        postDataParams.put("opCode", "FETCH_BY_CONDITION_OR")
        postDataParams.put("sheetId", this.sheetId)
        postDataParams.put("tabName", this.tabName)
        postDataParams.put("dataColumn", this.conditionOrColumn)
        postDataParams.put("dataValue", this.conditionOrValue)
        return postDataParams
    }

    fun conditionOr(conditionColumn: String, conditionValue: String) {
        if (conditionColumn.isEmpty() || conditionValue.isEmpty())
            return

        if (this.conditionOrColumn.isNotEmpty()) {
            this.conditionOrColumn += ","
            this.conditionOrValue += ","
        }
        this.conditionOrColumn += conditionColumn
        this.conditionOrValue += conditionValue
    }
}