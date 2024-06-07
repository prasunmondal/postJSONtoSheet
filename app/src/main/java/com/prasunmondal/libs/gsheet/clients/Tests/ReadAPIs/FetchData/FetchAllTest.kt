package com.prasunmondal.libs.gsheet.clients.Tests.ReadAPIs.FetchData

import com.prasunmondal.libs.gsheet.clients.Tests.ModelInsertObject
import com.prasunmondal.libs.gsheet.clients.newSet.APIRequests.ReadAPIs.FetchData.FetchAll
import com.prasunmondal.libs.gsheet.clients.newSet.GScript
import com.prasunmondal.libs.gsheet.clients.Tests.ProjectConfig
import com.prasunmondal.libs.gsheet.clients.newSet.APIRequests.CreateAPIs.InsertObject
import com.prasunmondal.libs.gsheet.clients.newSet.APIRequests.DeleteAPIs.DeleteAll

class FetchAllTest {
    constructor() {
        test()
    }
    fun test() {
        val e = DeleteAll()
        e.setUId("test-e.234")
        e.sheetId(ProjectConfig.DB_SHEET_ID)
        e.tabName("Sheet2")
        GScript.addRequest(e)

        val f = InsertObject()
        f.setUId("test-t218625")
        f.sheetId(ProjectConfig.DB_SHEET_ID)
        f.tabName("Sheet2")
        f.setDataObject(ModelInsertObject("Prasun", "Mondal"))
        GScript.addRequest(f)

        val d = InsertObject()
        d.setUId("test-d218625")
        d.sheetId(ProjectConfig.DB_SHEET_ID)
        d.tabName("Sheet2")
        d.setDataObject(ModelInsertObject("Dona", "Mondal"))
        GScript.addRequest(d)

        val t = FetchAll()
        t.setUId("test-ti35uy2t")
        t.sheetId(ProjectConfig.DB_SHEET_ID)
        t.tabName("Sheet2")
        GScript.addRequest(t)

        val w = FetchAll()
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