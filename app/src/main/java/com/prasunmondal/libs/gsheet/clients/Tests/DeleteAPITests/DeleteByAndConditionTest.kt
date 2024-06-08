package com.prasunmondal.libs.gsheet.clients.Tests.DeleteAPITests

import com.prasunmondal.libs.gsheet.clients.Tests.ModelInsertObject
import com.prasunmondal.libs.gsheet.clients.APIRequests.DeleteAPIs.GSheetDeleteByAndCondition
import com.prasunmondal.libs.gsheet.clients.GScript
import com.prasunmondal.libs.gsheet.clients.Tests.ProjectConfig
import com.prasunmondal.libs.gsheet.clients.APIRequests.CreateAPIs.GSheetInsertObject

class DeleteByAndConditionTest {
    constructor() {
        test()
    }
    fun test() {
        val a = GSheetInsertObject()
        a.setUId("test-a-3456")
        a.sheetId(ProjectConfig.DB_SHEET_ID)
        a.tabName("Sheet2")
        a.setDataObject(ModelInsertObject("Swagata", "Mondal"))
        GScript.addRequest(a)

        val t = GSheetDeleteByAndCondition()
        t.setUId("test-ti35uy2t")
        t.sheetId(ProjectConfig.DB_SHEET_ID)
        t.tabName("Sheet2")
        t.conditionAnd("name", "Swagata")
        t.conditionAnd("title", "Mondal")
        GScript.addRequest(t)

        val responses = GScript.execute(ProjectConfig.dBServerScriptURL)

        if(!(responses["test-ti35uy2t"]!!.statusCode == 200)) {
            throw AssertionError()
        }
    }
}