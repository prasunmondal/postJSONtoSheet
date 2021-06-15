package com.prasunmondal.postjsontosheets.clients.fetchAll

import java.util.function.Consumer

interface FetchAllFlow {

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
        fun build(): FetchAll
        fun postCompletion(onCompletion: Consumer<String>?): FinalRequestBuilder
    }

    fun execute(): FetchAllResponse
}