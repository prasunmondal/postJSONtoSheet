package com.prasunmondal.GSheet.Clients.commons.newSet.APIResponses

import com.prasunmondal.GSheet.Clients.commons.newSet.APIRequests.APIRequests2

class CreateResponse : APIResponse2() {
    lateinit var sheetId: String
    lateinit var tabName: String
    lateinit var data: String
    var appendInServer: Boolean = true


}