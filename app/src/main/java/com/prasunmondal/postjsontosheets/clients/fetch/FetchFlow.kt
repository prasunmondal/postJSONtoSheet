package com.prasunmondal.postjsontosheets.clients.fetch

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
        fun conditionAnd(conditionKey: String, conditionValue: String): FinalRequestBuilder
        fun conditionOr(conditionKey: String, conditionValue: String): FinalRequestBuilder
    }

    fun execute(): FetchResponse
}