package com.prasunmondal.postjsontosheets.clients.post.raw

import com.prasunmondal.postjsontosheets.clients.commons.APIResponse

class PostSequenceResponse: APIResponse {

    constructor(responsePayload: String) {
        System.err.println("bound: " + responsePayload)
        this.responsePayload = responsePayload
    }

    fun getObject(): PostSequenceResponse {
        return this
    }
}