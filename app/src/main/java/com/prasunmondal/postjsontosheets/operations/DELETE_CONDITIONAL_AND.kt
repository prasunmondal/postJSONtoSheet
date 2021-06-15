package com.prasunmondal.postjsontosheets.operations

import com.prasunmondal.postjsontosheets.ExecutePostCalls
import com.prasunmondal.postjsontosheets.StringConstants
import com.prasunmondal.postjsontosheets.clients.fetchAll.FetchAllResponse
import org.json.JSONObject
import java.net.URL
import java.util.function.Consumer

@Suppress("ClassName")
class DELETE_CONDITIONAL_AND {
    private var onCompletion: Consumer<FetchAllResponse>? = null
    private val postDataParams = JSONObject()
    private var scriptUrl: URL? = null

    constructor(keys: String?, tabName: String?, column: String?, onCompletion: Consumer<FetchAllResponse>?) {
        this.onCompletion = onCompletion
        scriptUrl = URL(StringConstants.getDBServerScriptURL())
        postDataParams.put("opCode", "DELETE_CONDITIONAL_AND")
        postDataParams.put("sheetId", StringConstants.DB_SHEET_ID)
        postDataParams.put("tabName", tabName)
        postDataParams.put("dataValue", keys)
        postDataParams.put("dataColumn", column)
    }

    fun execute() {
        val c = ExecutePostCalls(scriptUrl, postDataParams, onCompletion)
        c.execute()
    }
}