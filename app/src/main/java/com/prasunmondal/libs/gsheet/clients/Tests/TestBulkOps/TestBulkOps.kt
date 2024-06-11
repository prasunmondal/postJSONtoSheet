package com.prasunmondal.libs.gsheet.clients.Tests.TestBulkOps

import com.prasunmondal.libs.gsheet.clients.GScript
import com.prasunmondal.libs.gsheet.clients.Tests.ProjectConfig

class TestBulkOps {
    init {
        test()
    }

    fun test() {
        TestSheet1Model.get()
        GScript.addRequest(TestSheet1Model.prepareFetchAllRequest())
        GScript.addRequest(TestSheet1Model.customFetchRequest())
        GScript.addRequest(TestSheet2Model.prepareFetchAllRequest())
        GScript.execute(ProjectConfig.dBServerScriptURL)
    }
}