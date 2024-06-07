package com.prasunmondal.libs.gsheet.clients.Tests.CreateAPIs

import com.prasunmondal.libs.gsheet.clients.newSet.APIRequests.CreateAPIs.InsertStringArray
import com.prasunmondal.libs.gsheet.clients.newSet.GScript
import com.prasunmondal.libs.gsheet.clients.Tests.ProjectConfig

class InsertStringArrayTest {

    constructor() {
        test()
    }
    fun test() {
        val t = InsertStringArray()
        t.setUId("test-ti735058")
        t.sheetId(ProjectConfig.DB_SHEET_ID)
        t.tabName("Sheet2")
        t.dataObject("prasun2,mondal1")
        GScript.addRequest(t)

        val w = InsertStringArray()
        w.setUId("test-w23u5y44")
        w.sheetId(ProjectConfig.DB_SHEET_ID)
        w.tabName("Sheet2")
        w.dataObject(listOf("prasun2","mondal1"))
        GScript.addRequest(w)

        val responses = GScript.execute(ProjectConfig.dBServerScriptURL)

        if(!(responses["test-ti735058"]!!.statusCode == 200
                    && responses["test-w23u5y44"]!!.statusCode == 400)) {
            throw AssertionError()
        }
    }
}