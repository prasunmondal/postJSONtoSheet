package com.prasunmondal.libs.gsheet.clients.Tests.ReadAPIs.FetchData

import com.prasunmondal.libs.gsheet.clients.newSet.APIRequests.ReadAPIs.FetchData.FetchByQuery
import com.prasunmondal.libs.gsheet.clients.newSet.GScript
import com.prasunmondal.libs.gsheet.clients.Tests.ProjectConfig

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