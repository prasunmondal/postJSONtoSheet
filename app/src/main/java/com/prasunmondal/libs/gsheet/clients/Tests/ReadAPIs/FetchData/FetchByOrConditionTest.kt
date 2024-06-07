package com.prasunmondal.libs.gsheet.clients.Tests.ReadAPIs.FetchData

import com.prasunmondal.libs.gsheet.clients.newSet.APIRequests.ReadAPIs.FetchData.FetchByOrCondition
import com.prasunmondal.libs.gsheet.clients.newSet.GScript
import com.prasunmondal.libs.Logs.LogMe
import com.prasunmondal.libs.gsheet.clients.Tests.ProjectConfig

class FetchByOrConditionTest {
    constructor() {
        test()
    }
    fun test() {
        val t = FetchByOrCondition()
        t.setUId("test-tiu2t4t")
        t.sheetId(ProjectConfig.DB_SHEET_ID)
        t.tabName("Sheet2")
        t.conditionOr("name","Swagata")
        GScript.addRequest(t)

        val w = FetchByOrCondition()
        w.setUId("test-wiew7triq")
        w.sheetId(ProjectConfig.DB_SHEET_ID)
        w.tabName("Sheet2")
        w.conditionOr("name","Mondal")
        GScript.addRequest(w)

        val r = FetchByOrCondition()
        r.setUId("test-r2654643")
        r.sheetId(ProjectConfig.DB_SHEET_ID)
        r.tabName("Sheet2")
        r.conditionOr("name","Prasun")
        GScript.addRequest(r)

        // TODO - Fix multiple AND conditions
        val z = FetchByOrCondition()
        z.setUId("test-z5634243")
        z.sheetId(ProjectConfig.DB_SHEET_ID)
        z.tabName("Sheet2")
        z.conditionOr("name","Prasun")
        GScript.addRequest(z)

        val responses = GScript.execute(ProjectConfig.dBServerScriptURL)
        responses.forEach { key, value ->
            LogMe.log(value.toString())
        }

        if(!(responses["test-tiu2t4t"]!!.statusCode == 200
                    && responses["test-wiew7triq"]!!.statusCode == 204
                    && responses["test-r2654643"]!!.statusCode == 200)) {
            throw AssertionError()
        }
    }
}