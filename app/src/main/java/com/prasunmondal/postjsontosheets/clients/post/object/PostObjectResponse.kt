package com.prasunmondal.postjsontosheets.clients.post.`object`

import com.prasunmondal.postjsontosheets.clients.APIResponse

class PostObjectResponse: APIResponse {

    constructor(responsePayload: String) {
        this.responsePayload = responsePayload
    }

    fun getObject(): PostObjectResponse {
        return this
    }
}