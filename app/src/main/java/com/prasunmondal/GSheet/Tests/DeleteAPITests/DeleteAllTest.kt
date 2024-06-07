package com.prasunmondal.GSheet.Tests.DeleteAPITests

import com.prasunmondal.GSheet.Clients.commons.newSet.APIRequests.DeleteAPIs.DeleteAll
import com.prasunmondal.GSheet.Clients.commons.newSet.APIRequests.ReadAPIs.FetchAll
import com.prasunmondal.GSheet.Clients.commons.newSet.GScript
import com.prasunmondal.GSheet.Tests.ProjectConfig

class DeleteAllTest {
    constructor() {
        test()
    }
    fun test() {
        val t = DeleteAll()
        t.setUId("test-ti35uy2t")
        t.sheetId(ProjectConfig.DB_SHEET_ID)
        t.tabName("Sheet2")
        GScript.addRequest(t)

        val responses = GScript.execute(ProjectConfig.dBServerScriptURL)

        if(!(responses["test-ti35uy2t"]!!.statusCode == 200)) {
            throw AssertionError()
        }
    }
}