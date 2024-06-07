package com.prasunmondal.GSheet.Tests.DeleteAPITests

import com.prasunmondal.GSheet.Clients.commons.newSet.APIRequests.DeleteAPIs.DeleteAll
import com.prasunmondal.GSheet.Clients.commons.newSet.APIRequests.DeleteAPIs.DeleteByAndCondition
import com.prasunmondal.GSheet.Clients.commons.newSet.APIRequests.DeleteAPIs.DeleteByOrCondition
import com.prasunmondal.GSheet.Clients.commons.newSet.APIRequests.ReadAPIs.FetchAll
import com.prasunmondal.GSheet.Clients.commons.newSet.GScript
import com.prasunmondal.GSheet.Tests.ProjectConfig

class DeleteByOrConditionTest {
    constructor() {
        test()
    }
    fun test() {
        val t = DeleteByOrCondition()
        t.setUId("test-ti87686")
        t.sheetId(ProjectConfig.DB_SHEET_ID)
        t.tabName("Sheet2")
        t.conditionOr("name", "Swagata")
        t.conditionOr("title", "Sarkar")
        GScript.addRequest(t)

        val responses = GScript.execute(ProjectConfig.dBServerScriptURL)

        if(!(responses["test-ti87686"]!!.statusCode == 200)) {
            throw AssertionError()
        }
    }
}