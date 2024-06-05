package com.prasunmondal.GSheet.Tests

import com.prasunmondal.GSheet.Clients.commons.newSet.APIRequests.CreateAPIs.InsertObject
import com.prasunmondal.GSheet.Logs.LogMe
import com.prasunmondal.GSheet.Tests.ReadAPIs.FetchAllTest

class Test {

    companion object {

        fun start() {
//            testClearObjects()
//            testInsertObject()
//            testGetObjects()
//            testMultiple()

//        New     New     New     New     New     New     New     New     New
            InsertObject()
//            InsertUniqueObjectTest()
//            InsertStringArrayTest()
            FetchAllTest()
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

//        fun testMultiple() {
//            GScript.addRequest(ModelInsertClass.getGetRequest(false))
//            GScript.execute(ProjectConfig.dBServerScriptURL)
//        }
    }
}