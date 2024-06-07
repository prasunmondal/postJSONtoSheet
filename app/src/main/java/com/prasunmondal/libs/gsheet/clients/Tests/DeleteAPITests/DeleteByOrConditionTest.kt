package com.prasunmondal.libs.gsheet.clients.Tests.DeleteAPITests

import com.prasunmondal.libs.gsheet.clients.Tests.ModelInsertObject
import com.prasunmondal.libs.gsheet.clients.APIRequests.DeleteAPIs.DeleteByOrCondition
import com.prasunmondal.libs.gsheet.clients.GScript
import com.prasunmondal.libs.gsheet.clients.Tests.ProjectConfig
import com.prasunmondal.libs.gsheet.clients.APIRequests.CreateAPIs.InsertObject

class DeleteByOrConditionTest {
    constructor() {
        test()
    }
    fun test() {
        val a = InsertObject()
        a.setUId("test-a-3456")
        a.sheetId(ProjectConfig.DB_SHEET_ID)
        a.tabName("Sheet2")
        a.setDataObject(ModelInsertObject("Swagata", "Sarkar"))
        GScript.addRequest(a)

        val t = DeleteByOrCondition()
        t.setUId("test-ti87686")
        t.sheetId(ProjectConfig.DB_SHEET_ID)
        t.tabName("Sheet2")
        t.conditionOr("name", "Swagata")
        t.conditionOr("title", "Sarkar")
        GScript.addRequest(t)

        val responses = GScript.execute(ProjectConfig.dBServerScriptURL)

        if(!(responses["test-a-3456"]!!.statusCode == 200
                    && responses["test-ti87686"]!!.statusCode == 200)) {
            throw AssertionError()
        }
    }
}