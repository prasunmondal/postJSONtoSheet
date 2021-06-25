package com.prasunmondal.postjsontosheets.clients.post.raw

import com.prasunmondal.postjsontosheets.clients.commons.APIResponse
import com.prasunmondal.postjsontosheets.clients.commons.JsonTags

class PostRawResponse: APIResponse {

    constructor(responsePayload: String) {
        this.responsePayload = responsePayload
    }

    fun getObject(): PostRawResponse {
        return this
    }

    fun numberOfRecordsAdded(): Int {
        return 1
    }
}