package com.prasunmondal.postjsontosheets.insertObject

import java.util.*
import java.util.function.Consumer

interface InsertObjectFlow {

    interface ScriptIdBuilder {
        fun scriptId(scriptId: String): SheetIdBuilder
    }

    interface SheetIdBuilder {
        fun sheetId(sheetId: String): TabNameBuilder
    }

    interface TabNameBuilder {
        fun tabName(tabName: String): DataObjectBuilder
    }

    interface DataObjectBuilder {
        fun dataObject(dataObject: Object): FinalRequestBuilder
    }

    interface FinalRequestBuilder {
        // All optional parameters goes here
        fun build(): InsertObject
        fun postCompletion(onCompletion: Consumer<InsertObjectResponse>?): FinalRequestBuilder
    }

    fun execute(): InsertObjectResponse
}