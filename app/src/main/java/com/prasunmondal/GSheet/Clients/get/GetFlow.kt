//package com.prasunmondal.GSheet.Clients.get
//
//import java.util.function.Consumer
//
//interface GetFlow {
//
//    interface ScriptIdBuilder {
//        fun scriptId(scriptId: String): SheetIdBuilder
//    }
//
//    interface SheetIdBuilder {
//        fun sheetId(sheetId: String): TabNameBuilder
//    }
//
//    interface TabNameBuilder {
//        fun tabName(tabName: String): FinalRequestBuilder
//    }
//
//    interface FinalRequestBuilder {
//        // All optional parameters goes here
//        fun build(): Get
//        fun postCompletion(onCompletion: Consumer<GetResponse>?): FinalRequestBuilder
//        fun conditionAnd(conditionKey: String, conditionValue: String): FinalRequestBuilder
//        fun conditionOr(conditionKey: String, conditionValue: String): FinalRequestBuilder
//        fun execute(): GetResponse
//    }
//
//    interface ReadyToRun {
//        fun build(): Get
//        fun execute(): GetResponse
//    }
//}