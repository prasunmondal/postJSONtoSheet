package com.prasunmondal.GSheet.Clients.commons.Tests.ReadAPIs.CheckData

import com.prasunmondal.GSheet.Clients.commons.Tests.ModelInsertObject
import com.prasunmondal.GSheet.Clients.commons.newSet.APIRequests.ReadAPIs.CheckData.CheckDataPresence
import com.prasunmondal.GSheet.Clients.commons.newSet.GScript
import com.prasunmondal.GSheet.Clients.commons.Tests.ProjectConfig
import com.prasunmondal.GSheet.Clients.commons.newSet.APIRequests.CreateAPIs.InsertObject

class CheckDataPresenceTest {
    init {
        test()
    }
    fun test() {
        val f = InsertObject()
        f.setUId("test-t218625")
        f.sheetId(ProjectConfig.DB_SHEET_ID)
        f.tabName("Sheet2")
        f.setDataObject(ModelInsertObject("Prasun", "Mondal"))
        GScript.addRequest(f)

        val g = InsertObject()
        g.setUId("test-t21t525")
        g.sheetId(ProjectConfig.DB_SHEET_ID)
        g.tabName("Sheet2")
        g.setDataObject(ModelInsertObject("Dona", "Mondal"))
        GScript.addRequest(g)

        val t = CheckDataPresence()
        t.setUId("test-t34674")
        t.sheetId(ProjectConfig.DB_SHEET_ID)
        t.tabName("Sheet2")
        t.keys("name")
        t.values("Dona")
        GScript.addRequest(t)

        val w = CheckDataPresence()
        w.setUId("test-w23456")
        w.sheetId(ProjectConfig.DB_SHEET_ID)
        w.tabName("Sheet2")
        w.keys("name")
        w.values("NotPresent")
        GScript.addRequest(w)

        val responses = GScript.execute(ProjectConfig.dBServerScriptURL)

        if(!(responses["test-t34674"]!!.statusCode == 200
                    && responses["test-w23456"]!!.statusCode == 204)) {
            throw AssertionError()
        }
    }
}