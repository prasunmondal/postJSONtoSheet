package com.prasunmondal.libs.gsheet.clients.APIResponses

import com.prasunmondal.libs.gsheet.clients.APIRequests.APIRequests
import com.prasunmondal.libs.gsheet.clients.APIRequests.ReadAPIs.ReadAPIs
import com.prasunmondal.libs.gsheet.serializer.parsers.Parser

class ReadResponse<T> : APIResponse() {
    lateinit var sheetId: String
    lateinit var tabName: String
    lateinit var parsedResponse: List<T>
    lateinit var data: String
    var query: Boolean = true
}