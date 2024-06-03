package com.prasunmondal.GSheet.Clients.commons.newSet.APIResponses

class ReadResponse : APIResponse2() {
    lateinit var sheetId: String
    lateinit var tabName: String
    lateinit var data: String
    var query: Boolean = true
}