package com.prasunmondal.GSheet.Tests.Insert

import com.prasunmondal.GSheet.Logs.LogMe
import com.prasunmondal.GSheet.Tests.ProjectConfig
import com.tech4bytes.mbrosv3.Utils.DB.clients.GScript

class Test {

    companion object {

        fun start() {
//            testClearObjects()
//            testInsertObject()
//            testGetObjects()
            testMultiple()
        }
        fun testInsertObject() {
            ModelInsertClass.saveToServer(ModelInsertObject("Prasun", "Mondal"))
        }

        fun testClearObjects() {
            ModelInsertClass.deleteDataFromServer()
        }

        fun testGetObjects() {
            val list = ModelInsertClass.get(false)
            list.forEach {
                LogMe.log(it.toString())
            }
        }

        fun testMultiple() {
            GScript.addRequest(ModelInsertClass.getGetRequest(false))
            GScript.execute(ProjectConfig.dBServerScriptURL)
        }
    }
}