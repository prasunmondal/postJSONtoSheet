package com.prasunmondal.postjsontosheets.operations

import com.prasunmondal.postjsontosheets.ExecutePostCalls
import com.prasunmondal.postjsontosheets.StringConstants
import org.json.JSONObject
import java.net.URL
import java.util.function.Consumer

@Suppress("ClassName")
class FETCH_ALL {
    private var onCompletion: Consumer<String>? = null
    private val postDataParams = JSONObject()
    private var scriptUrl: URL? = null

    constructor(tabName: String?, onCompletion: Consumer<String>?) {
        this.onCompletion = onCompletion
        scriptUrl = URL(StringConstants.DB_SERVER_SCRIPT_URL)
        postDataParams.put("opCode", "FETCH_ALL")
        postDataParams.put("sheetId", StringConstants.DB_SHEET_ID)
        postDataParams.put("tabName", tabName)
    }

    fun execute() {
        val c = ExecutePostCalls(scriptUrl, postDataParams, onCompletion)
        c.execute()
    }
}