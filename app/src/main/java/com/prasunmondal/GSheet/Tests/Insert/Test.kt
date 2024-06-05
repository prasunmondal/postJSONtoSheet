package com.prasunmondal.GSheet.Tests.Insert

import com.prasunmondal.GSheet.Clients.commons.newSet.APIRequests.APIRequests2
import com.prasunmondal.GSheet.Clients.commons.newSet.APIRequests.CreateAPIs.InsertObject
import com.prasunmondal.GSheet.Clients.commons.newSet.GScript
import com.prasunmondal.GSheet.Logs.LogMe
import com.prasunmondal.GSheet.Tests.ProjectConfig

class Test {

    companion object {

        fun start() {
//            testClearObjects()
//            testInsertObject()
//            testGetObjects()
//            testMultiple()


            testNewObjectInsert()
        }

        private fun testNewObjectInsert() {
//            InsertObject().setDataObject(ModelInsertObject("Prasun", "Mondal"))
//                .setSheetId(ProjectConfig.DB_SHEET_ID)
//                .setTabName("Sheet2")
//                .execute(ProjectConfig.dBServerScriptURL)

            val t = InsertObject()
            t.setUId("test-t218625")
            t.sheetId(ProjectConfig.DB_SHEET_ID)
            t.tabName("Sheet2")
            t.setDataObject(ModelInsertObject("Prasun", "Mondal"))
            GScript.addRequest(t as APIRequests2)

            val w = InsertObject()
            w.setUId("test-w8324545")
            w.sheetId(ProjectConfig.DB_SHEET_ID)
            w.tabName("Sheet3")
            w.setDataObject(ModelInsertObject("Prasun", "Mondal"))
            GScript.addRequest(w as APIRequests2)

            val responses = GScript.execute(ProjectConfig.dBServerScriptURL)

            if(!(responses["test-t218625"]!!.statusCode == 200
                && responses["test-w8324545"]!!.statusCode == 500)) {
                throw AssertionError()
            }
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