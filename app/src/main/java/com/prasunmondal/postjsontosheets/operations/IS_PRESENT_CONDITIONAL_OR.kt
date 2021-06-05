package com.prasunmondal.postjsontosheets.operations

import com.prasunmondal.postjsontosheets.ExecutePostCalls
import com.prasunmondal.postjsontosheets.StringConstants
import org.json.JSONObject
import java.net.URL
import java.util.function.Consumer

@Suppress("ClassName")
class IS_PRESENT_CONDITIONAL_OR {
    private var onCompletion: Consumer<String>? = null
    private val postDataParams = JSONObject()
    private var scriptUrl: URL? = null

    constructor(keys: String?, tabName: String?, column: String?, onCompletion: Consumer<String>?) {
        this.onCompletion = onCompletion
        scriptUrl = URL(StringConstants.getDBServerScriptURL())
        postDataParams.put("opCode", "IS_PRESENT_CONDITIONAL_OR")
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