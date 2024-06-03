package com.prasunmondal.GSheet.Clients.commons.newSet.APIRequests

import com.prasunmondal.GSheet.Clients.commons.newSet.APIRequests.APIRequests2

class CreateRequest : APIRequests2() {
    lateinit var sheetId: String
    lateinit var tabName: String
    lateinit var data: String
    var appendInServer: Boolean = true


}