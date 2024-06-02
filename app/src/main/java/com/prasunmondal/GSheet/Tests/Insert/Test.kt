package com.prasunmondal.GSheet.Tests.Insert

import com.prasunmondal.GSheet.Clients.post.serializable.PostObject
import com.prasunmondal.GSheet.Logs.LogMe
import com.prasunmondal.GSheet.Test
import com.prasunmondal.GSheet.Tests.ProjectConfig

class Test {

    companion object {
        fun testInsertObject() {
            ModelInsertClass.saveToServer(ModelInsertObject("Prasun", "Mondal"))

        }

        fun testClearObjects() {
            ModelInsertClass.deleteDataFromServer()
        }

        fun testGetObjects() {
            val list = ModelInsertClass.get(false)
            list.forEach {
                LogMe.log(list.toString())
            }
        }
    }
}