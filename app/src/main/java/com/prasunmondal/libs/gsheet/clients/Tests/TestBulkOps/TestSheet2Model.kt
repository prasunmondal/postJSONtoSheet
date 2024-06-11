package com.prasunmondal.libs.gsheet.clients.Tests.TestBulkOps

import com.prasunmondal.libs.gsheet.clients.responseCaching.APIRequestsTemplates
import com.prasunmondal.libs.gsheet.clients.Tests.ModelInsertObject
import com.prasunmondal.libs.gsheet.clients.Tests.ProjectConfig

object TestSheet2Model : APIRequestsTemplates<ModelInsertObject>(
    ProjectConfig.dBServerScriptURL,
    ProjectConfig.DB_SHEET_ID,
    "TestSheet2",
    query = null,
    classTypeForResponseParsing = ModelInsertObject::class.java,
    appendInServer = true,
    appendInLocal = true
) {

}