package com.prasunmondal.GSheet.Clients.commons.newSet.APIRequests

import com.prasunmondal.GSheet.Clients.commons.newSet.APIRequests.APIRequests2
import com.prasunmondal.GSheet.Clients.commons.newSet.APIRequests.CreateAPIs.InsertObject
import com.prasunmondal.GSheet.Clients.post.serializable.PostObjectFlow

abstract class CreateRequest : APIRequests2() {
    lateinit var sheetId: String
    lateinit var tabName: String
    lateinit var data: String
    var appendInServer: Boolean = true

    fun settSheetId(sheetId: String) {
        this.sheetId = sheetId
    }

    fun settTabName(tabName: String) {
        this.tabName = tabName
    }
}