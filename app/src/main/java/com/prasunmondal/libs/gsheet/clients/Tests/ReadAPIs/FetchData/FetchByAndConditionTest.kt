package com.prasunmondal.libs.gsheet.clients.Tests.ReadAPIs.FetchData

import com.prasunmondal.libs.gsheet.clients.Tests.ModelInsertObject
import com.prasunmondal.libs.gsheet.clients.APIRequests.ReadAPIs.FetchData.FetchByAndCondition
import com.prasunmondal.libs.gsheet.clients.GScript
import com.prasunmondal.libs.Logs.LogMe
import com.prasunmondal.libs.gsheet.clients.Tests.ProjectConfig
import com.prasunmondal.libs.gsheet.clients.APIRequests.CreateAPIs.InsertObject

class FetchByAndConditionTest {
    constructor() {
        test()
    }
    fun test() {
        val a = InsertObject()
        a.setUId("test-a-3456")
        a.sheetId(ProjectConfig.DB_SHEET_ID)
        a.tabName("Sheet2")
        a.setDataObject(ModelInsertObject("Swagata", "Mondal"))
        GScript.addRequest(a)

        val b = InsertObject()
        b.setUId("test-a-3458")
        b.sheetId(ProjectConfig.DB_SHEET_ID)
        b.tabName("Sheet2")
        b.setDataObject(ModelInsertObject("Dona", "Mondal"))
        GScript.addRequest(b)

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