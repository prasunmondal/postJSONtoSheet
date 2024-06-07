package com.prasunmondal.GSheet.Clients.commons.Tests.CreateAPIs

import com.prasunmondal.GSheet.Clients.commons.newSet.APIRequests.CreateAPIs.InsertObject
import com.prasunmondal.GSheet.Clients.commons.newSet.GScript
import com.prasunmondal.GSheet.Clients.commons.Tests.ModelInsertObject
import com.prasunmondal.GSheet.Clients.commons.Tests.ProjectConfig
import com.prasunmondal.GSheet.Clients.commons.newSet.APIRequests.DeleteAPIs.DeleteAll
import com.prasunmondal.GSheet.Logs.LogMe

class InsertObjectTest {

    constructor() {
        test()
    }
    fun test() {
        val e = DeleteAll()
        e.setUId("test-e.234")
        e.sheetId(ProjectConfig.DB_SHEET_ID)
        e.tabName("Sheet2")
        GScript.addRequest(e)

        val t = InsertObject()
        t.setUId("test-t218625")
        t.sheetId(ProjectConfig.DB_SHEET_ID)
        t.tabName("Sheet2")
        t.setDataObject(ModelInsertObject("Prasun", "Mondal"))
        GScript.addRequest(t)

        val d = InsertObject()
        d.setUId("test-d218625")
        d.sheetId(ProjectConfig.DB_SHEET_ID)
        d.tabName("Sheet2")
        d.setDataObject(ModelInsertObject("Dona", "Mondal"))
        GScript.addRequest(d)

        // Should fail due to incorrect Sheet Name
        val w = InsertObject()
        w.setUId("test-w8324545")
        w.sheetId(ProjectConfig.DB_SHEET_ID)
        w.tabName("Sheet3")
        w.setDataObject(ModelInsertObject("Prasun", "Mondal"))
        GScript.addRequest(w)

        val responses = GScript.execute(ProjectConfig.dBServerScriptURL)

        LogMe.log((responses["test-e.234"]!!.statusCode % 100 == 2).toString())
        if(!(responses["test-e.234"]!!.statusCode / 100 == 2
                    && responses["test-t218625"]!!.statusCode == 200
                    && responses["test-d218625"]!!.statusCode == 200
                    && responses["test-w8324545"]!!.statusCode == 500)) {
            throw AssertionError()
        }
    }
}