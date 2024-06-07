package com.prasunmondal.libs.gsheet.clients.APIResponses

class ReadResponse : APIResponse() {
    lateinit var sheetId: String
    lateinit var tabName: String
    lateinit var data: String
    var query: Boolean = true
}