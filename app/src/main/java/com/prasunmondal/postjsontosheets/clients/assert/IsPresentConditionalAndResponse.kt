package com.prasunmondal.postjsontosheets.clients.get

import com.prasunmondal.postjsontosheets.clients.commons.APIResponse

class IsPresentConditionalAndResponse : APIResponse {

    constructor(responsePayload: String) {
        this.content = responsePayload
    }

    fun getObject(): IsPresentConditionalAndResponse {
        return this
    }
}