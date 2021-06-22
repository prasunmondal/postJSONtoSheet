package com.prasunmondal.postjsontosheets.clients.delete

import com.prasunmondal.postjsontosheets.clients.commons.APIResponse

class DeleteResponse: APIResponse {

    constructor(responsePayload: String) {
        this.responsePayload = responsePayload
    }

    fun getObject(): DeleteResponse {
        return this
    }
}