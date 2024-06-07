package com.prasunmondal.libs.gsheet.clients.Tests.DeleteAPITests

import com.prasunmondal.libs.gsheet.clients.APIRequests.DeleteAPIs.DeleteAll
import com.prasunmondal.libs.gsheet.clients.GScript
import com.prasunmondal.libs.gsheet.clients.Tests.ProjectConfig

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