package com.prasunmondal.libs.gsheet.post.serializable

import com.prasunmondal.libs.gsheet.clients.APIResponse

class PostObjectResponse : APIResponse {

    constructor(responsePayload: String) {
        this.content = responsePayload
    }

    fun getObject(): PostObjectResponse {
        return this
    }
}