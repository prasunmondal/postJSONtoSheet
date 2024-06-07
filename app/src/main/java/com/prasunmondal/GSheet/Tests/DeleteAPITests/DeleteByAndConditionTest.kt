package com.prasunmondal.GSheet.Tests.DeleteAPITests

import com.prasunmondal.GSheet.Clients.commons.newSet.APIRequests.DeleteAPIs.DeleteByAndCondition
import com.prasunmondal.GSheet.Clients.commons.newSet.GScript
import com.prasunmondal.GSheet.Tests.ProjectConfig

class DeleteByAndConditionTest {
    constructor() {
        test()
    }
    fun test() {
        val t = DeleteByAndCondition()
        t.setUId("test-ti35uy2t")
        t.sheetId(ProjectConfig.DB_SHEET_ID)
        t.tabName("Sheet2")
        t.conditionAnd("name", "Swagata")
        t.conditionAnd("title", "Sarkar")
        GScript.addRequest(t)

        val responses = GScript.execute(ProjectConfig.dBServerScriptURL)

        if(!(responses["test-ti78346"]!!.statusCode == 200)) {
            throw AssertionError()
        }
    }
}