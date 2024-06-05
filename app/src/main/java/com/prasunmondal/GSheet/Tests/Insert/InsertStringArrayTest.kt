package com.prasunmondal.GSheet.Tests.Insert

import com.prasunmondal.GSheet.Clients.commons.newSet.APIRequests.APIRequests2
import com.prasunmondal.GSheet.Clients.commons.newSet.APIRequests.CreateAPIs.InsertStringArray
import com.prasunmondal.GSheet.Clients.commons.newSet.APIRequests.CreateAPIs.InsertUniqueObject
import com.prasunmondal.GSheet.Clients.commons.newSet.GScript
import com.prasunmondal.GSheet.Tests.ProjectConfig

class InsertStringArrayTest {

    constructor() {
        testNewObjectInsert()
    }
    fun testNewObjectInsert() {
        val t = InsertStringArray()
        t.setUId("test-ti735058")
        t.sheetId(ProjectConfig.DB_SHEET_ID)
        t.tabName("Sheet2")
        t.dataObject("prasun2,mondal1")
        GScript.addRequest(t as APIRequests2)

        val w = InsertStringArray()
        w.setUId("test-w23u5y44")
        w.sheetId(ProjectConfig.DB_SHEET_ID)
        w.tabName("Sheet2")
        w.dataObject(listOf("prasun2","mondal1"))
        GScript.addRequest(w as APIRequests2)

        val responses = GScript.execute(ProjectConfig.dBServerScriptURL)

        if(!(responses["test-ti735058"]!!.statusCode == 200
                    && responses["test-w23u5y44"]!!.statusCode == 400)) {
            throw AssertionError()
        }
    }
}