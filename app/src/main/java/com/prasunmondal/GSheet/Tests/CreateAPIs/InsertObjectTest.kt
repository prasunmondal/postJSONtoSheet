package com.prasunmondal.GSheet.Tests.CreateAPIs

import com.prasunmondal.GSheet.Clients.commons.newSet.APIRequests.CreateAPIs.InsertObject
import com.prasunmondal.GSheet.Clients.commons.newSet.GScript
import com.prasunmondal.GSheet.Tests.ModelInsertObject
import com.prasunmondal.GSheet.Tests.ProjectConfig

class InsertObjectTest {

    constructor() {
        test()
    }
    fun test() {
        val t = InsertObject()
        t.setUId("test-t218625")
        t.sheetId(ProjectConfig.DB_SHEET_ID)
        t.tabName("Sheet2")
        t.setDataObject(ModelInsertObject("Prasun", "Mondal"))
        GScript.addRequest(t)

        val w = InsertObject()
        w.setUId("test-w8324545")
        w.sheetId(ProjectConfig.DB_SHEET_ID)
        w.tabName("Sheet3")
        w.setDataObject(ModelInsertObject("Prasun", "Mondal"))
        GScript.addRequest(w)

        val responses = GScript.execute(ProjectConfig.dBServerScriptURL)

        if(!(responses["test-t218625"]!!.statusCode == 200
                    && responses["test-w8324545"]!!.statusCode == 500)) {
            throw AssertionError()
        }
    }
}