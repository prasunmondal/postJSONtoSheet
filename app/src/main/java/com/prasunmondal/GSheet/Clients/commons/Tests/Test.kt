package com.prasunmondal.GSheet.Clients.commons.Tests

import com.prasunmondal.GSheet.Clients.commons.Tests.CreateAPIs.InsertObjectTest
import com.prasunmondal.GSheet.Clients.commons.Tests.DeleteAPITests.DeleteAllTest
import com.prasunmondal.GSheet.Clients.commons.Tests.DeleteAPITests.DeleteByAndConditionTest
import com.prasunmondal.GSheet.Clients.commons.Tests.DeleteAPITests.DeleteByOrConditionTest
import com.prasunmondal.GSheet.Clients.commons.Tests.ReadAPIs.CheckData.CheckDataPresenceTest
import com.prasunmondal.GSheet.Clients.commons.Tests.ReadAPIs.FetchData.FetchAllTest
import com.prasunmondal.GSheet.Clients.commons.Tests.ReadAPIs.FetchData.FetchByAndConditionTest
import com.prasunmondal.GSheet.Clients.commons.Tests.ReadAPIs.FetchData.FetchByOrConditionTest
import com.prasunmondal.GSheet.Clients.commons.Tests.ReadAPIs.FetchData.FetchByQueryTest

class Test {

    companion object {

        fun start() {
//            testClearObjects()
//            testInsertObject()
//            testGetObjects()
//            testMultiple()

//        New     New     New     New     New     New     New     New     New
            InsertObjectTest()
//            InsertUniqueObjectTest()
//            InsertStringArrayTest()
            FetchAllTest()
            FetchByQueryTest()
            FetchByAndConditionTest()
            FetchByOrConditionTest()
            DeleteAllTest()
            DeleteByAndConditionTest()
            DeleteByOrConditionTest()
            CheckDataPresenceTest()
//        }

//        fun testInsertObject() {
//            ModelInsertClass.saveToServer(ModelInsertObject("Prasun", "Mondal"))
//        }
//
//        fun testClearObjects() {
//            ModelInsertClass.deleteDataFromServer()
//        }
//
//        fun testGetObjects() {
//            val list = ModelInsertClass.get(false)
//            list.forEach {
//                LogMe.log(it.toString())
//            }
//        }

//        fun testMultiple() {
//            GScript.addRequest(ModelInsertClass.getGetRequest(false))
//            GScript.execute(ProjectConfig.dBServerScriptURL)
//        }
        }
    }
}