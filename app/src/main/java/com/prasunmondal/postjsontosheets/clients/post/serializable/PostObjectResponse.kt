package com.prasunmondal.postjsontosheets.clients.post.serializable

import com.prasunmondal.postjsontosheets.clients.commons.APIResponse
import com.prasunmondal.postjsontosheets.clients.commons.JsonTags

class PostObjectResponse: APIResponse {

    constructor(responsePayload: String) {
        this.responsePayload = responsePayload
    }

    fun getObject(): PostObjectResponse {
        return this
    }

    fun numberOfRecordsAdded(): Int {
        return 1
    }
}