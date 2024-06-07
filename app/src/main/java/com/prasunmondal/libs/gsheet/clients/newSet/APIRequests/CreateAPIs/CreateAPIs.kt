package com.prasunmondal.libs.gsheet.clients.newSet.APIRequests.CreateAPIs

import com.prasunmondal.libs.gsheet.clients.newSet.APIRequests.APIRequests

abstract class CreateAPIs : APIRequests() {
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