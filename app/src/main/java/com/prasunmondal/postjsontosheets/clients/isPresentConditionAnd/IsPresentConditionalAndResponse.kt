package com.prasunmondal.postjsontosheets.clients.fetchAll

import com.prasunmondal.postjsontosheets.clients.APIResponse

class IsPresentConditionalAndResponse: APIResponse {

    constructor(responsePayload: String) {
        this.responsePayload = responsePayload
    }

    fun getObject(): IsPresentConditionalAndResponse {
        return this
    }
}