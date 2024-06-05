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
            t.sheetId(ProjectConfig.DB_SHEET_ID)
            t.tabName("Sheet2")
            t.setDataObject(ModelInsertObject("Prasun", "Mondal"))

            GScript.addRequest(t as APIRequests2)
            GScript.execute(ProjectConfig.dBServerScriptURL)
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