package com.tech4bytes.mbrosv3.Utils.DB.clients.get.ByQuery

import com.prasunmondal.GSheet.Clients.commons.APIResponse
import com.prasunmondal.GSheet.Clients.get.GetResponse
import java.util.function.Consumer

interface GetByQueryFlow {

    interface ScriptIdBuilder {
        fun scriptId(scriptId: String): SheetIdBuilder
    }

    interface SheetIdBuilder {
        fun sheetId(sheetId: String): TabNameBuilder
    }

    interface TabNameBuilder {
        fun tabName(tabName: String): AddQueryBuilder
    }

    interface AddQueryBuilder {
        fun query(query: String): FinalRequestBuilder
    }

    interface FinalRequestBuilder {
        // All optional parameters goes here
        fun build(): GetByQuery
        fun postCompletion(onCompletion: Consumer<APIResponse>?): FinalRequestBuilder
    }

    fun execute(): GetResponse
}