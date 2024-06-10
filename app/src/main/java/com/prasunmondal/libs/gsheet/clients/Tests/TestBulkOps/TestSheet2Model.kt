package com.prasunmondal.libs.gsheet.clients.Tests.TestBulkOps

import com.prasunmondal.libs.gsheet.clients.APIRequests.APIRequests
import com.prasunmondal.libs.gsheet.clients.APIRequests.ReadAPIs.FetchData.GSheetFetchAll
import com.prasunmondal.libs.gsheet.clients.Tests.ModelInsertObject
import com.prasunmondal.libs.gsheet.clients.Tests.ProjectConfig
import com.prasunmondal.libs.gsheet.serializer.Tech4BytesSerializable

object TestSheet2Model : Tech4BytesSerializable<ModelInsertObject>(
    ProjectConfig.dBServerScriptURL,
    ProjectConfig.DB_SHEET_ID,
    "TestSheet2",
    query = null,
    classTypeForResponseParsing = ModelInsertObject::class.java,
    appendInServer = true,
    appendInLocal = true
) {
    override fun getRequest(): APIRequests {
        val t = GSheetFetchAll<ModelInsertObject>()
        t.sheetId = ProjectConfig.DB_SHEET_ID
        t.tabName = "TestSheet2"
        t.classTypeForResponseParsing = ModelInsertObject::class.java
        return t
    }
}