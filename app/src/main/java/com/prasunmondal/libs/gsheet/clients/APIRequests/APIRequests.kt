package com.prasunmondal.libs.gsheet.clients.APIRequests

import com.prasunmondal.libs.gsheet.clients.GScript
import com.prasunmondal.libs.StringUtils.StringUtils

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