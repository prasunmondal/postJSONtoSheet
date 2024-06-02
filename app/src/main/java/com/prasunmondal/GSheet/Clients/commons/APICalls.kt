package com.prasunmondal.GSheet.Clients.commons

import org.json.JSONObject

interface APICalls {
    fun getJSON(): JSONObject
    fun execute(): APIResponse
}