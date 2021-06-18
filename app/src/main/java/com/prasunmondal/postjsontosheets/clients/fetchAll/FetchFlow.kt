package com.prasunmondal.postjsontosheets.clients.fetchAll

import java.util.function.Consumer

interface FetchFlow {

    interface ScriptIdBuilder {
        fun scriptId(scriptId: String): SheetIdBuilder
    }

    interface SheetIdBuilder {
        fun sheetId(sheetId: String): TabNameBuilder
    }

    interface TabNameBuilder {
        fun tabName(tabName: String): FinalRequestBuilder
    }

    interface FinalRequestBuilder {
        // All optional parameters goes here
        fun build(): Fetch
        fun postCompletion(onCompletion: Consumer<FetchResponse>?): FinalRequestBuilder
    }

    fun execute(): FetchResponse
}