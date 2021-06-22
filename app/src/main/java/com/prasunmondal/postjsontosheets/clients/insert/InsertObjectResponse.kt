package com.prasunmondal.postjsontosheets.clients.insert

import com.prasunmondal.postjsontosheets.clients.APIResponse

class InsertObjectResponse: APIResponse {

    constructor(responsePayload: String) {
        this.responsePayload = responsePayload
    }

    fun getObject(): InsertObjectResponse {
        return this
    }
}