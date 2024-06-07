package com.prasunmondal.GSheet.Tests.Insert

import com.prasunmondal.GSheet.Clients.commons.newSet.APIRequests.APIRequests2
import com.prasunmondal.GSheet.Clients.commons.newSet.APIRequests.InsertAPIs.InsertUniqueObject
import com.prasunmondal.GSheet.Clients.commons.newSet.GScript
import com.prasunmondal.GSheet.Tests.ModelInsertObject
import com.prasunmondal.GSheet.Tests.ProjectConfig

class InsertUniqueObjectTest {

    constructor() {
        test()
    }
    fun test() {
        val t = InsertUniqueObject()
        t.setUId("test-t7569286")
        t.sheetId(ProjectConfig.DB_SHEET_ID)
        t.tabName("Sheet2")
        t.setDataObject(ModelInsertObject("dgbnv", "skjbvkj"))
        t.uniqueColumn("name")
        GScript.addRequest(t)

        val w = InsertUniqueObject()
        w.setUId("test-wi2u5ytf")
        w.sheetId(ProjectConfig.DB_SHEET_ID)
        w.tabName("Sheet3")
        w.setDataObject(ModelInsertObject("Dona", "Mondal"))
        w.uniqueColumn("name")
        GScript.addRequest(w)

        val responses = GScript.execute(ProjectConfig.dBServerScriptURL)

        if(!(responses["test-t7569286"]!!.statusCode == 200
                    && responses["test-wi2u5ytf"]!!.statusCode == 400)) {
            throw AssertionError()
        }
    }
}