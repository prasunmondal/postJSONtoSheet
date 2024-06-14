package com.prasunmondal.libs.gsheet.clients.Tests.TestBulkOps

import com.prasunmondal.libs.AppContexts.AppContexts
import com.prasunmondal.libs.gsheet.clients.GScript
import com.prasunmondal.libs.gsheet.clients.Tests.ProjectConfig

class TestBulkOps {
    init {
        test()
    }

    fun test() {
        TestSheet1Model.fetchAll()
        TestSheet1Model.fetchAll()
//        GScript.addRequest(TestSheet1Model.prepareFetchAllRequest())
//        GScript.addRequest(TestSheet1Model.customFetchRequest())
//        GScript.addRequest(TestSheet2Model.prepareFetchAllRequest())
//        GScript.execute(ProjectConfig.dBServerScriptURL)
    }
}