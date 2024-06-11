package com.prasunmondal.libs.gsheet.clients.Tests.TestBulkOps

import com.prasunmondal.libs.gsheet.clients.APIRequests.APIRequests
import com.prasunmondal.libs.gsheet.clients.responseCaching.APIRequestsTemplates
import com.prasunmondal.libs.gsheet.clients.APIRequests.ReadAPIs.FetchData.GSheetFetchByAndCondition
import com.prasunmondal.libs.gsheet.clients.Tests.ModelInsertObject
import com.prasunmondal.libs.gsheet.clients.Tests.ProjectConfig

object TestSheet1Model : APIRequestsTemplates<ModelInsertObject>(
    ProjectConfig.dBServerScriptURL,
    ProjectConfig.DB_SHEET_ID,
    "TestSheet1",
    query = null,
    classTypeForResponseParsing = ModelInsertObject::class.java,
    appendInServer = true,
    appendInLocal = true
) {

    fun customFetchRequest(): APIRequests {
        val request = GSheetFetchByAndCondition<ModelInsertObject>()
        request.defaultInitialize(request, this)
        request.conditionAnd("name", "Prasun")
        return request
    }
}