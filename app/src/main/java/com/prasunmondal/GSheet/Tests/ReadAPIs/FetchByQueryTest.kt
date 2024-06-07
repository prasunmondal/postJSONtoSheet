package com.prasunmondal.GSheet.Tests.ReadAPIs

import com.prasunmondal.GSheet.Clients.commons.newSet.APIRequests.APIRequests2
import com.prasunmondal.GSheet.Clients.commons.newSet.APIRequests.ReadAPIs.FetchAll
import com.prasunmondal.GSheet.Clients.commons.newSet.APIRequests.ReadAPIs.FetchByQuery
import com.prasunmondal.GSheet.Clients.commons.newSet.GScript
import com.prasunmondal.GSheet.Tests.ProjectConfig

class FetchByQueryTest {
    constructor() {
        test()
    }
    fun test() {
        val t = FetchByQuery()
        t.setUId("test-83567t")
        t.sheetId(ProjectConfig.DB_SHEET_ID)
        t.tabName("Sheet2")
        t.query("=QUERY(IMPORTRANGE(\"https://docs.google.com/spreadsheets/d/1p3v4SgXPfB70YjCXCOj57BdLrDiFBoynt7yIWPQ8WmI\",\"sheet2!A1:Az\"), \"select *\")")
        GScript.addRequest(t)

        val responses = GScript.execute(ProjectConfig.dBServerScriptURL)

        if(!(responses["test-83567t"]!!.statusCode == 200
                    && responses["test-83567t"]!!.statusCode == 200)) {
            throw AssertionError()
        }
    }
}