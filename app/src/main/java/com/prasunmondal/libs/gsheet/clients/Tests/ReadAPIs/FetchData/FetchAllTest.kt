package com.prasunmondal.libs.gsheet.clients.Tests.ReadAPIs.FetchData

import com.prasunmondal.libs.gsheet.clients.Tests.ModelInsertObject
import com.prasunmondal.libs.gsheet.clients.APIRequests.ReadAPIs.FetchData.GSheetFetchAll
import com.prasunmondal.libs.gsheet.clients.GScript
import com.prasunmondal.libs.gsheet.clients.Tests.ProjectConfig
import com.prasunmondal.libs.gsheet.clients.APIRequests.CreateAPIs.GSheetInsertObject
import com.prasunmondal.libs.gsheet.clients.APIRequests.DeleteAPIs.GSheetDeleteAll

class FetchAllTest {
    constructor() {
        test()
    }
    fun test() {
        val e = GSheetDeleteAll()
        e.setUId("test-e.234")
        e.sheetId(ProjectConfig.DB_SHEET_ID)
        e.tabName("Sheet2")
        GScript.addRequest(e)

        val f = GSheetInsertObject()
        f.setUId("test-t218625")
        f.sheetId(ProjectConfig.DB_SHEET_ID)
        f.tabName("Sheet2")
        f.setDataObject(ModelInsertObject("Prasun", "Mondal"))
        GScript.addRequest(f)

        val d = GSheetInsertObject()
        d.setUId("test-d218625")
        d.sheetId(ProjectConfig.DB_SHEET_ID)
        d.tabName("Sheet2")
        d.setDataObject(ModelInsertObject("Dona", "Mondal"))
        GScript.addRequest(d)

        val t = GSheetFetchAll()
        t.setUId("test-ti35uy2t")
        t.sheetId(ProjectConfig.DB_SHEET_ID)
        t.tabName("Sheet2")
        GScript.addRequest(t)

        val w = GSheetFetchAll()
        w.setUId("test-w245ueyt")
        w.sheetId(ProjectConfig.DB_SHEET_ID)
        w.tabName("Sheet2")
        GScript.addRequest(w)

        val responses = GScript.execute(ProjectConfig.dBServerScriptURL)

        if(!(responses["test-ti35uy2t"]!!.statusCode == 200
                    && responses["test-w245ueyt"]!!.statusCode == 200)) {
            throw AssertionError()
        }
    }
}