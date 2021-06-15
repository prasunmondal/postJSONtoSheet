package com.prasunmondal.postjsontosheets.clients.fetchAll

import java.util.function.Consumer

interface IsPresentConditionalAndFlow {

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
        fun build(): IsPresentConditionalAnd
        fun postCompletion(onCompletion: Consumer<String>?): FinalRequestBuilder
    }

    fun execute(): IsPresentConditionalAndResponse
}