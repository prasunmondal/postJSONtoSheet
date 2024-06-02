package com.prasunmondal.GSheet.Clients.post.raw

import com.prasunmondal.GSheet.Clients.commons.APIResponse

class PostRawResponse : APIResponse {

    constructor(responsePayload: String) {
        this.content = responsePayload
    }

    fun getObject(): PostRawResponse {
        return this
    }

    fun numberOfRecordsAdded(): Int {
        return 1
    }
}