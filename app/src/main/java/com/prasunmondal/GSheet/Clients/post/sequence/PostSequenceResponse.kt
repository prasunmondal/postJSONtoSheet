package com.prasunmondal.GSheet.Clients.post.raw

import com.prasunmondal.GSheet.Clients.commons.APIResponse

class PostSequenceResponse : APIResponse {

    constructor(responsePayload: String) {
        this.content = responsePayload
    }

    fun getObject(): PostSequenceResponse {
        return this
    }

    fun numberOfRecordsAdded(): Int {
        return 1
    }
}