package com.prasunmondal.GSheet.Clients.commons.newSet.APIRequests.CreateAPIs

import com.google.gson.Gson
import org.json.JSONObject

class InsertObject : CreateRequest() {
    private lateinit var dataObject: Any

    fun setDataObject(dataObject: Any): InsertObject {
        this.dataObject = dataObject
        return this
    }
    override fun getJSON(): JSONObject {
        val postDataParams = JSONObject()
        postDataParams.put("opCode", "INSERT_OBJECT")
        postDataParams.put("sheetId", this.sheetId)
        postDataParams.put("tabName", this.tabName)
        postDataParams.put("objectData", Gson().toJson(this.dataObject))
        return postDataParams
    }
}