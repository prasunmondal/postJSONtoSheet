package com.prasunmondal.GSheet.Clients.commons.newSet.APIRequests

import com.prasunmondal.GSheet.Clients.commons.newSet.GScript
import com.prasunmondal.GSheet.StringUtils.StringUtils

abstract class APIRequests: GScript() {
    private var uId: String = setUId()
    var opCode: String = ""

    fun getUId(): String {
        return uId
    }
    fun setUId(uId: String = ""): String {
        if(uId.isBlank()) {
            this.uId = StringUtils.generateUniqueString()
        } else {
            this.uId = uId
        }
        return this.uId
    }
}