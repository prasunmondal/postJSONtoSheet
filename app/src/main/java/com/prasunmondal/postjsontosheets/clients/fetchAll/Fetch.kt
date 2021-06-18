package com.prasunmondal.postjsontosheets.clients.fetchAll

import com.prasunmondal.postjsontosheets.ExecutePostCalls
import org.json.JSONObject
import java.net.URL
import java.util.function.Consumer

class Fetch private constructor() : FetchFlow, FetchFlow.ScriptIdBuilder,
        FetchFlow.SheetIdBuilder,
        FetchFlow.TabNameBuilder,
        FetchFlow.FinalRequestBuilder {
    lateinit var scriptURL: String
    lateinit var sheetId: String
    lateinit var tabName: String
    var onCompletion: Consumer<FetchResponse>? = null

    override fun scriptId(scriptURL: String): FetchFlow.SheetIdBuilder {
        this.scriptURL = scriptURL
        return this
    }

    override fun sheetId(sheetId: String): FetchFlow.TabNameBuilder {
        this.sheetId = sheetId
        return this
    }

    override fun tabName(tabName: String): FetchFlow.FinalRequestBuilder {
        this.tabName = tabName
        return this
    }

    override fun postCompletion(onCompletion: Consumer<FetchResponse>?): FetchFlow.FinalRequestBuilder {
        this.onCompletion = onCompletion
        return this
    }

    override fun build(): Fetch {
        this.scriptURL = scriptURL
        this.sheetId = sheetId
        this.tabName = tabName
        this.onCompletion = onCompletion
        return this
    }

    override fun execute(): FetchResponse {
        val scriptUrl = URL(this.scriptURL)
        val postDataParams = JSONObject()
        postDataParams.put("opCode", "FETCH_ALL")
        postDataParams.put("sheetId", this.sheetId)
        postDataParams.put("tabName", this.tabName)

        val c = ExecutePostCalls(scriptUrl, postDataParams) { response -> postExecute(response) }
        var response = c.execute().get()
        return FetchResponse(response).getObject()
    }

    private fun postExecute(response: String) {
        if(onCompletion == null)
            return
        var responseObj = FetchResponse(response)
        onCompletion!!.accept(responseObj)
    }

    companion object {
        fun builder(): FetchFlow.ScriptIdBuilder {
            return Fetch()
        }
    }
}