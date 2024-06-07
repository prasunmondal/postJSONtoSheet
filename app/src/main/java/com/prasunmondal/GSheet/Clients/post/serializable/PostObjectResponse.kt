package com.prasunmondal.GSheet.Clients.post.serializable

import com.prasunmondal.GSheet.Clients.commons.APIResponse

class PostObjectResponse : APIResponse {

    constructor(responsePayload: String) {
        this.content = responsePayload
    }

    fun getObject(): PostObjectResponse {
        return this
    }
}