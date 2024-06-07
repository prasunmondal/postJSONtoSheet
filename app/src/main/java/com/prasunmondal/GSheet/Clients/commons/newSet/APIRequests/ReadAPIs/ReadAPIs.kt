package com.prasunmondal.GSheet.Clients.commons.newSet.APIRequests.ReadAPIs

import com.prasunmondal.GSheet.Clients.commons.newSet.APIRequests.APIRequests2

abstract class ReadAPIs : APIRequests2() {
    lateinit var sheetId: String
    lateinit var tabName: String
    lateinit var data: String

    fun sheetId(sheetId: String) {
        this.sheetId = sheetId
    }

    fun tabName(tabName: String) {
        this.tabName = tabName
    }
}