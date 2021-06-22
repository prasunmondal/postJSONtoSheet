package com.prasunmondal.postjsontosheets.clients.insert

import com.google.gson.Gson
import com.prasunmondal.postjsontosheets.ExecutePostCalls
import org.json.JSONObject
import java.net.URL
import java.util.function.Consumer

class InsertObject() : InsertObjectFlow, InsertObjectFlow.ScriptIdBuilder,
        InsertObjectFlow.SheetIdBuilder,
        InsertObjectFlow.TabNameBuilder,
        InsertObjectFlow.DataObjectBuilder,
        InsertObjectFlow.FinalRequestBuilder {
    private lateinit var scriptURL: String
    private lateinit var sheetId: String
    private lateinit var tabName: String
    private lateinit var dataObject: Object
    private var uniqueColumn = "";

    var onCompletion: Consumer<InsertObjectResponse>? = null

    override fun scriptId(scriptURL: String): InsertObjectFlow.SheetIdBuilder {
        this.scriptURL = scriptURL
        return this
    }

    override fun sheetId(sheetId: String): InsertObjectFlow.TabNameBuilder {
        this.sheetId = sheetId
        return this
    }

    override fun tabName(tabName: String): InsertObjectFlow.DataObjectBuilder {
        this.tabName = tabName
        return this
    }

    override fun dataObject(dataObject: Object): InsertObjectFlow.FinalRequestBuilder {
        this.dataObject = dataObject
        return this
    }

    override fun postCompletion(onCompletion: Consumer<InsertObjectResponse>?): InsertObjectFlow.FinalRequestBuilder {
        this.onCompletion = onCompletion
        return this
    }

    override fun uniqueColumn(uniqueColumn: String): InsertObjectFlow.FinalRequestBuilder {
        if(uniqueColumn == "")
            return this
        if(this.uniqueColumn != "")
            this.uniqueColumn += ","
        this.uniqueColumn += uniqueColumn
        return this
    }

    override fun build(): InsertObject {
        this.scriptURL = scriptURL
        this.sheetId = sheetId
        this.tabName = tabName
        this.onCompletion = onCompletion
        return this
    }

    override fun execute(): InsertObjectResponse {
        if(this.uniqueColumn == "")
            return insertData()
        return insertUniqueData()
    }

    private fun insertData(): InsertObjectResponse  {
        val scriptUrl = URL(this.scriptURL)
        val postDataParams = JSONObject()
        postDataParams.put("opCode", "INSERT_OBJECT")
        postDataParams.put("sheetId", this.sheetId)
        postDataParams.put("tabName", this.tabName)
        postDataParams.put("objectData", Gson().toJson(this.dataObject))

        val c = ExecutePostCalls(scriptUrl, postDataParams) { response -> postExecute(response) }
        var response = c.execute().get()
        return InsertObjectResponse(response).getObject()
    }

    private fun insertUniqueData(): InsertObjectResponse  {
        val scriptUrl = URL(this.scriptURL)
        val postDataParams = JSONObject()
        postDataParams.put("opCode", "INSERT_OBJECT_UNIQUE")
        postDataParams.put("sheetId", this.sheetId)
        postDataParams.put("tabName", this.tabName)
        postDataParams.put("objectData", Gson().toJson(this.dataObject))
        postDataParams.put("uniqueCol", this.uniqueColumn)

        val c = ExecutePostCalls(scriptUrl, postDataParams) { response -> postExecute(response) }
        var response = c.execute().get()
        return InsertObjectResponse(response).getObject()
    }

    private fun postExecute(response: String) {
        if(onCompletion == null)
            return
        var responseObj = InsertObjectResponse(response)
        onCompletion!!.accept(responseObj)
    }

    companion object {
        fun builder(): InsertObjectFlow.ScriptIdBuilder {
            return InsertObject()
        }
    }
}