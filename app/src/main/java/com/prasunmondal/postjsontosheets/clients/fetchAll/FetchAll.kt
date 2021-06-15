package com.prasunmondal.postjsontosheets.clients.fetchAll

import com.prasunmondal.postjsontosheets.ExecutePostCalls
import org.json.JSONObject
import java.net.URL
import java.util.function.Consumer

class FetchAll private constructor() : FetchAllFlow, FetchAllFlow.ScriptIdBuilder,
        FetchAllFlow.SheetIdBuilder,
        FetchAllFlow.TabNameBuilder,
        FetchAllFlow.FinalRequestBuilder {
    lateinit var scriptURL: String
    lateinit var sheetId: String
    lateinit var tabName: String
    var onCompletion: Consumer<FetchAllResponse>? = null

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

    override fun postCompletion(onCompletion: Consumer<FetchAllResponse>?): FetchAllFlow.FinalRequestBuilder {
        this.onCompletion = onCompletion
        return this
    }

    override fun build(): FetchAll {
        this.scriptURL = scriptURL
        this.sheetId = sheetId
        this.tabName = tabName
        this.onCompletion = onCompletion
        return this
    }

    override fun execute(): FetchAllResponse {
        val scriptUrl = URL(this.scriptURL)
        val postDataParams = JSONObject()
        postDataParams.put("opCode", "FETCH_ALL")
        postDataParams.put("sheetId", this.sheetId)
        postDataParams.put("tabName", this.tabName)

        val c = ExecutePostCalls(scriptUrl, postDataParams) { response -> postExecute(response) }
        var response = c.execute().get()
        return FetchAllResponse(response).getObject()
    }

    private fun postExecute(response: String) {
        if(onCompletion == null)
            return
        var responseObj = FetchAllResponse(response)
        onCompletion!!.accept(responseObj)
    }

    companion object {
        fun builder(): FetchAllFlow.ScriptIdBuilder {
            return FetchAll()
        }
    }
}