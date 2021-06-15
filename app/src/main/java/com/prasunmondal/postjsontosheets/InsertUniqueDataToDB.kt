package com.prasunmondal.postjsontosheets

import com.google.gson.Gson
import org.json.JSONObject
import com.google.gson.GsonBuilder
import com.prasunmondal.postjsontosheets.clients.fetchAll.FetchAllResponse
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
        var sp: ArrayList<String> = mutableListOf("Prasun", "Dona") as ArrayList<String>
        val map = mutableMapOf<String, String>()
        map["one"] = "1"
        map.put("two","two")
        map.put("three","3")

//        val map = mutableMapOf("one" to 1, "two" to 2)
//        numbersMap.put("three", 3)
//        println(numbersMap)
        var y = TestClass(9, "prasunmondal", Secondary("ding-ding-ding"), sp, map)
        println("Check -- Sent   Object: $y")
        scriptUrl = URL(StringConstants.dBServerScriptURL)
        postDataParams.put("sheetId", StringConstants.DB_SHEET_ID)
        postDataParams.put("tabName", tabName)
//        postDataParams.put("opCode", "INSERT_UNIQUE")
        postDataParams.put("opCode", StringConstants.DB_OPERATION)
        postDataParams.put("uniqueCol", uniqueCol)
        postDataParams.put("objectData", Gson().toJson(y))
    }
}