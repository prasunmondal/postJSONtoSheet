package com.prasunmondal.GSheet.Tests.ReadAPIs.CheckData

import com.prasunmondal.GSheet.Clients.commons.newSet.APIRequests.ReadAPIs.CheckData.CheckDataPresence
import com.prasunmondal.GSheet.Clients.commons.newSet.GScript
import com.prasunmondal.GSheet.Tests.ProjectConfig

class CheckDataPresenceTest {
    init {
        test()
    }
    fun test() {
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