package com.prasunmondal.postjsontosheets.clients.fetchAll

import com.prasunmondal.postjsontosheets.ExecutePostCalls
import org.json.JSONObject
import java.net.URL
import java.util.function.Consumer

class IsPresentConditionalAnd private constructor() : IsPresentConditionalAndFlow, IsPresentConditionalAndFlow.ScriptIdBuilder,
        IsPresentConditionalAndFlow.SheetIdBuilder,
        IsPresentConditionalAndFlow.TabNameBuilder,
        IsPresentConditionalAndFlow.FinalRequestBuilder {
    lateinit var scriptURL: String
    lateinit var sheetId: String
    lateinit var tabName: String
    var onCompletion: Consumer<String>? = null

    override fun scriptId(scriptURL: String): IsPresentConditionalAndFlow.SheetIdBuilder {
        this.scriptURL = scriptURL
        return this
    }

    override fun sheetId(sheetId: String): IsPresentConditionalAndFlow.TabNameBuilder {
        this.sheetId = sheetId
        return this
    }

    override fun tabName(tabName: String): IsPresentConditionalAndFlow.FinalRequestBuilder {
        this.tabName = tabName
        return this
    }

    override fun postCompletion(onCompletion: Consumer<String>?): IsPresentConditionalAndFlow.FinalRequestBuilder {
        this.onCompletion = onCompletion
        return this
    }

    override fun build(): IsPresentConditionalAnd {
        this.scriptURL = scriptURL
        this.sheetId = sheetId
        this.tabName = tabName
        this.onCompletion = onCompletion
        return this
    }

    override fun execute(): IsPresentConditionalAndResponse {
        val scriptUrl = URL(this.scriptURL)
        val postDataParams = JSONObject()
        postDataParams.put("opCode", "FETCH_ALL")
        postDataParams.put("sheetId", this.sheetId)
        postDataParams.put("tabName", this.tabName)

        val c = ExecutePostCalls(scriptUrl, postDataParams, onCompletion)
        var response = c.execute().get()
        return IsPresentConditionalAndResponse(response).getObject()
    }

    companion object {
        fun builder(): IsPresentConditionalAndFlow.ScriptIdBuilder {
            return IsPresentConditionalAnd()
        }
    }
}