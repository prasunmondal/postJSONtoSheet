package com.prasunmondal.GSheet.Clients.delete

import com.prasunmondal.GSheet.Clients.commons.APIResponse

class DeleteResponse : APIResponse {

    constructor(responsePayload: String) {
        this.content = responsePayload
    }

    fun getObject(): DeleteResponse {
        return this
    }
}