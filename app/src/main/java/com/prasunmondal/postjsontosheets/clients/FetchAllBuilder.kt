package com.prasunmondal.postjsontosheets.clients

import com.google.gson.reflect.TypeToken
import com.prasunmondal.postjsontosheets.ExecutePostCalls
import com.prasunmondal.postjsontosheets.JSONUtils
import com.prasunmondal.postjsontosheets.TestClass
import org.json.JSONObject
import java.net.URL
import java.util.ArrayList
import java.util.function.Consumer


class FetchAll private constructor() : FetchAllFlow.ScriptIdBuilder,
        FetchAllFlow.SheetIdBuilder,
        FetchAllFlow.TabNameBuilder,
        FetchAllFlow.FinalRequestBuilder {
    lateinit var scriptURL: String
    lateinit var sheetId: String
    lateinit var tabName: String
    var onCompletion: Consumer<String>? = null

    override fun scriptId(scriptURL: String): FetchAllFlow.SheetIdBuilder {
        this.scriptURL = scriptURL
        return this
    }

    override fun sheetId(sheetId: String): FetchAllFlow.TabNameBuilder {
        this.sheetId = sheetId
        return this
    }

    override fun tabName(tabName: String): FetchAllFlow.FinalRequestBuilder {
        this.tabName = tabName
        return this
    }

    override fun build(): FetchAllFlow {
        return FetchAllFlow(scriptURL, sheetId, tabName, onCompletion)
    }

    companion object {
        fun builder(): FetchAllFlow.ScriptIdBuilder {
            return FetchAll()
        }
    }

    override fun postCompletion(onCompletion: Consumer<String>?): FetchAllFlow.FinalRequestBuilder {
        this.onCompletion = onCompletion
        return this
    }
}

class FetchAllFlow(private var scriptURL: String,
                   private var sheetId: String,
                   private var tabName: String,
                   private var onCompletion: Consumer<String>?) {

    private var operationCode = "FETCH_ALL"

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
        fun build(): FetchAllFlow
        fun postCompletion(onCompletion: Consumer<String>?): FinalRequestBuilder
    }

    fun execute(): FetchAllResponse {
        val scriptUrl = URL(this.scriptURL)
        val postDataParams = JSONObject()
        postDataParams.put("opCode", operationCode)
        postDataParams.put("sheetId", this.sheetId)
        postDataParams.put("tabName", this.tabName)

        var response = FetchAllResponse(onCompletion)
        val c = ExecutePostCalls(scriptUrl, postDataParams) { p1 ->
            response.setInboundResponse(p1)
        }
        c.execute()
        return response
    }
}

class FetchAllResponse {
    lateinit var inboundResponse: String
    var onCompletion: Consumer<String>?

    constructor(onCompletion: Consumer<String>?) {
        this.onCompletion = onCompletion
    }

    @JvmName("setInboundResponse1")
    fun setInboundResponse(inboundResponse: String) {
        this.inboundResponse = inboundResponse
    }

    fun parseData() {
        var t2 = TestClass.parseJSONObject(
                    object : TypeToken<ArrayList<TestClass>>() {}.type,
                    JSONUtils.jsonStringCleanUp(this.inboundResponse)
            )
        println("Check -- Parsed Object: $t2")
    }
}