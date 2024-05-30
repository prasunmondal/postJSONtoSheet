package com.prasunmondal.postjsontosheets.clients.commons

import org.json.JSONObject

interface APICalls {
    fun getJSON(): JSONObject
    fun execute(): APIResponse
}