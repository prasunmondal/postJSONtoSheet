package com.prasunmondal.libs.gsheet.clients.APIResponses

class ReadResponse : APIResponse2() {
    lateinit var sheetId: String
    lateinit var tabName: String
    lateinit var data: String
    var query: Boolean = true
}