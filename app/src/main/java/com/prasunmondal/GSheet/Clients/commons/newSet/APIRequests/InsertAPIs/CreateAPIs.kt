package com.prasunmondal.GSheet.Clients.commons.newSet.APIRequests.InsertAPIs

import com.prasunmondal.GSheet.Clients.commons.newSet.APIRequests.APIRequests2

abstract class CreateAPIs : APIRequests2() {
    lateinit var sheetId: String
    lateinit var tabName: String
    lateinit var data: String
    var appendInServer: Boolean = true

    fun sheetId(sheetId: String) {
        this.sheetId = sheetId
    }

    fun tabName(tabName: String) {
        this.tabName = tabName
    }
}