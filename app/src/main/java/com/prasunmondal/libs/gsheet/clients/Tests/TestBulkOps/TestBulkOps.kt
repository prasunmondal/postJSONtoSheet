package com.prasunmondal.libs.gsheet.clients.Tests.TestBulkOps

import com.prasunmondal.libs.gsheet.clients.GScript
import com.prasunmondal.libs.gsheet.clients.Tests.ProjectConfig
import com.prasunmondal.libs.logs.instant.terminal.LogMe

class TestBulkOps {
    init {
        test()
    }

    fun test() {

        GScript.addRequest(TestSheet1Model.prepareFetchAllRequest())
        GScript.addRequest(TestSheet1Model.customFetchRequest())
//        GScript.addRequest(TestSheet2Model.prepareFetchAllRequest())
        GScript.execute(ProjectConfig.dBServerScriptURL)
        LogMe.log(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ")
        TestSheet1Model.fetchAll()
        TestSheet1Model.fetchAll()
        TestSheet1Model.fetchAll()
        TestSheet1Model.fetchAll()
    }
}