package com.prasunmondal.postjsontosheets

import com.google.gson.Gson
import org.json.JSONObject
import com.google.gson.GsonBuilder
import java.net.URL
import java.util.function.Consumer

class InsertUniqueDataToDB(
    data: String?,
    tabName: String?,
    uniqueCol: String?,
    private val onCompletion: Consumer<String>
) {
    private val postDataParams = JSONObject()
    private val scriptUrl: URL
    fun execute() {
        val c = ExecutePostCalls(scriptUrl, postDataParams, onCompletion)
        c.execute()
    }

    init {
        scriptUrl = URL(StringConstants.DB_SERVER_SCRIPT_URL)
        postDataParams.put("data", data)
        postDataParams.put("sheetId", StringConstants.DB_SHEET_ID)
        postDataParams.put("tabName", tabName)
        postDataParams.put("opCode", "INSERT_UNIQUE")
        postDataParams.put("uniqueCol", uniqueCol)
        postDataParams.put("objectData", "{\"ColThree\":8}")
    }
}