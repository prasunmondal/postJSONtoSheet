package com.prasunmondal.GSheet.Tests.ReadAPIs

import com.prasunmondal.GSheet.Clients.commons.newSet.APIRequests.APIRequests2
import com.prasunmondal.GSheet.Clients.commons.newSet.APIRequests.CreateAPIs.InsertStringArray
import com.prasunmondal.GSheet.Clients.commons.newSet.APIRequests.CreateAPIs.InsertUniqueObject
import com.prasunmondal.GSheet.Clients.commons.newSet.APIRequests.ReadAPIs.FetchAll
import com.prasunmondal.GSheet.Clients.commons.newSet.GScript
import com.prasunmondal.GSheet.Tests.ProjectConfig

class FetchAllTest {
    constructor() {
        test()
    }
    fun test() {
        val t = FetchAll()
        t.setUId("test-ti35uy2t")
        t.sheetId(ProjectConfig.DB_SHEET_ID)
        t.tabName("Sheet2")
        GScript.addRequest(t as APIRequests2)

        val w = FetchAll()
        w.setUId("test-w245ueyt")
        w.sheetId(ProjectConfig.DB_SHEET_ID)
        w.tabName("Sheet2")
        GScript.addRequest(w as APIRequests2)

        val responses = GScript.execute(ProjectConfig.dBServerScriptURL)

        if(!(responses["test-ti35uy2t"]!!.statusCode == 200
                    && responses["test-w245ueyt"]!!.statusCode == 200)) {
            throw AssertionError()
        }
    }
}