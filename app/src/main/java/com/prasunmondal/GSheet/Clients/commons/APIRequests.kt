package com.prasunmondal.GSheet.Clients.commons

import org.json.JSONObject

interface APIRequests {
    var uId: String
    var opCode: String
    var sheetId: String
    var tabName: String
    var data: String


    fun getJSON(): JSONObject
    fun execute(): APIResponse
}