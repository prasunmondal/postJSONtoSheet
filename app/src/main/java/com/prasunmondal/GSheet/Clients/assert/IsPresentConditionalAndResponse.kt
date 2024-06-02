package com.prasunmondal.GSheet.Clients.get

import com.prasunmondal.GSheet.Clients.commons.APIResponse

class IsPresentConditionalAndResponse : APIResponse {

    constructor(responsePayload: String) {
        this.content = responsePayload
    }

    fun getObject(): IsPresentConditionalAndResponse {
        return this
    }
}