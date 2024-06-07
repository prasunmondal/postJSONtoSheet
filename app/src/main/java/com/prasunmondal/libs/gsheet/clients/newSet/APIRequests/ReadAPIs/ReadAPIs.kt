package com.prasunmondal.libs.gsheet.clients.newSet.APIRequests.ReadAPIs

import com.prasunmondal.libs.gsheet.clients.newSet.APIRequests.APIRequests

abstract class ReadAPIs : APIRequests() {
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