package com.prasunmondal.GSheet.Clients.get

import com.prasunmondal.GSheet.Clients.commons.APIResponse

class GetResponse : APIResponse {

    constructor(responsePayload: String) {
        this.content = responsePayload
    }

    fun getObject(): GetResponse {
        return this
    }
}