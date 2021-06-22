package com.prasunmondal.postjsontosheets.clients.fetch

import com.prasunmondal.postjsontosheets.clients.APIResponse

class FetchResponse: APIResponse {

    constructor(responsePayload: String) {
        this.responsePayload = responsePayload
    }

    fun getObject(): FetchResponse {
        return this
    }
}