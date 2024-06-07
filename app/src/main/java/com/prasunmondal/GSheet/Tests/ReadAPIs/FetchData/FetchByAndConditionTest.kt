package com.prasunmondal.GSheet.Tests.ReadAPIs.FetchData

import com.prasunmondal.GSheet.Clients.commons.newSet.APIRequests.ReadAPIs.FetchData.FetchByAndCondition
import com.prasunmondal.GSheet.Clients.commons.newSet.GScript
import com.prasunmondal.GSheet.Logs.LogMe
import com.prasunmondal.GSheet.Tests.ProjectConfig

class FetchByAndConditionTest {
    constructor() {
        test()
    }
    fun test() {
        val t = FetchByAndCondition()
        t.setUId("test-tiu2t4t")
        t.sheetId(ProjectConfig.DB_SHEET_ID)
        t.tabName("Sheet2")
        t.conditionAnd("name","Swagata")
        GScript.addRequest(t)

        val w = FetchByAndCondition()
        w.setUId("test-wiew7triq")
        w.sheetId(ProjectConfig.DB_SHEET_ID)
        w.tabName("Sheet2")
        w.conditionAnd("name","Mondal")
        GScript.addRequest(w)

        val r = FetchByAndCondition()
        r.setUId("test-r2654643")
        r.sheetId(ProjectConfig.DB_SHEET_ID)
        r.tabName("Sheet2")
        r.conditionAnd("name","Prasun")
        GScript.addRequest(r)

        // TODO - Fix multiple AND conditions
        val z = FetchByAndCondition()
        z.setUId("test-z5634243")
        z.sheetId(ProjectConfig.DB_SHEET_ID)
        z.tabName("Sheet2")
        z.conditionAnd("name","Prasun")
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