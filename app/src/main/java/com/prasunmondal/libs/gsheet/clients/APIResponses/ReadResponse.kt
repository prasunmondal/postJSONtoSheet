package com.prasunmondal.libs.gsheet.clients.APIResponses

import java.io.Serializable

class ReadResponse<T> : APIResponse() {
    lateinit var sheetId: String
    lateinit var tabName: String
    lateinit var parsedResponse: List<T>
    lateinit var data: String
    var query: Boolean = true
}