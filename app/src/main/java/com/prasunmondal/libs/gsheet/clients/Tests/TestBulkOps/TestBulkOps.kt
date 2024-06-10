package com.prasunmondal.libs.gsheet.clients.Tests.TestBulkOps

import com.prasunmondal.libs.gsheet.clients.APIRequests.CreateAPIs.GSheetInsertObject
import com.prasunmondal.libs.gsheet.clients.APIRequests.DeleteAPIs.GSheetDeleteAll
import com.prasunmondal.libs.gsheet.clients.APIRequests.ReadAPIs.CheckData.GSheetCheckDataPresence
import com.prasunmondal.libs.gsheet.clients.APIRequests.ReadAPIs.FetchData.GSheetFetchAll
import com.prasunmondal.libs.gsheet.clients.GScript
import com.prasunmondal.libs.gsheet.clients.Tests.ModelInsertObject
import com.prasunmondal.libs.gsheet.clients.Tests.ProjectConfig

class TestBulkOps {
    init {
        test()
    }

    fun test() {
        GScript.addRequest(TestSheet1Model.getRequest())
        GScript.addRequest(TestSheet2Model.getRequest())
        GScript.execute(ProjectConfig.dBServerScriptURL)
    }
}