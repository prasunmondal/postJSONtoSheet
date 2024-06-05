package com.prasunmondal.GSheet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.prasunmondal.GSheet.AppContexts.AppContexts
import com.prasunmondal.GSheet.Clients.commons.APIRequests
import com.prasunmondal.GSheet.Clients.delete.Delete
import com.prasunmondal.GSheet.Clients.get.Get
import com.prasunmondal.GSheet.Clients.commons.newSet.APIRequests.CreateAPIs.InsertObject
import com.prasunmondal.GSheet.Tests.Insert.Test
import com.prasunmondal.GSheet.Tests.ProjectConfig
import com.tech4bytes.mbrosv3.Utils.DB.clients.GScript

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppContexts.set(this)
        testAll()
    }

    fun testAll() {

        Test.start()

//        testGetAll()
//        testDeleteAll()
//        testInsertObj()


//        var t = Get.builder()
//            .scriptId(StringConstants.dBServerScriptURL)
//            .sheetId(StringConstants.DB_SHEET_ID)
//            .tabName(StringConstants.DB_TAB_APP_OWNER)
//            .postCompletion(null)
//            .build()
//
//        var t3 = Get.builder()
//            .scriptId(StringConstants.dBServerScriptURL)
//            .sheetId(StringConstants.DB_SHEET_ID)
//            .tabName(StringConstants.DB_TAB_APP_OWNER)
//            .postCompletion(null)
//            .build()
//
//        var t2 = Get.builder()
//            .scriptId(StringConstants.dBServerScriptURL)
//            .sheetId(StringConstants.DB_SHEET_ID)
//            .tabName(StringConstants.DB_TAB_APP_OWNER)
//            .postCompletion(null)
//            .conditionAnd("name", "\"prasunmondal\"")
//            .conditionAnd("number", "9")
//            .build()
//
//        var tOr = Get.builder()
//            .scriptId(StringConstants.dBServerScriptURL)
//            .sheetId(StringConstants.DB_SHEET_ID)
//            .tabName(StringConstants.DB_TAB_APP_OWNER)
//            .postCompletion(null)
//            .conditionOr("name", "\"prasunmondal\"")
//            .conditionOr("number", "9")
//            .build()
//
//
//        var ipca = IsPresentConditionalAnd()
//            .scriptId(StringConstants.dBServerScriptURL)
//            .sheetId(StringConstants.DB_SHEET_ID)
//            .tabName(StringConstants.DB_TAB_APP_OWNER)
//            .keys("\"prasunmondal,9")
//            .values("name,number")
//            .build()
//
//        var sp: ArrayList<String> = mutableListOf("Prasun", "Dona") as ArrayList<String>
//        val map = mutableMapOf<String, String>()
//        map["one"] = "1"
//        map.put("two", "two")
//        map.put("three", "3")
//        var v = TestClass(9, "prasunmondal", Secondary("ding-ding-ding"), sp, map)
//        var v2 = TestClass(9, "\"prasunmondal\"", Secondary("ding-ding-ding"), sp, map)
//
////        var io0 = PostObject.builder()
////            .sheetId(StringConstants.DB_SHEET_ID)
////            .tabName(StringConstants.DB_TAB_APP_OWNER)
////            .dataObject(v as Object)
////            .uniqueColumn("name")
////            .uniqueColumn("number")
////            .build()
////
////
////        var io1 = PostObject.builder()
//////            .scriptId(StringConstants.dBServerScriptURL)
////            .sheetId(StringConstants.DB_SHEET_ID)
////            .tabName(StringConstants.DB_TAB_APP_OWNER)
////            .dataObject(v2 as Object).build()
////
////
////        var io2 = PostObject.builder()
//////            .scriptId(StringConstants.dBServerScriptURL)
////            .sheetId(StringConstants.DB_SHEET_ID)
////            .tabName(StringConstants.DB_TAB_APP_OWNER)
////            .dataObject(v as Object).build()
////
////
////        var io3 = PostObject.builder()
//////            .scriptId(StringConstants.dBServerScriptURL)
////            .sheetId(StringConstants.DB_SHEET_ID)
////            .tabName(StringConstants.DB_TAB_APP_OWNER)
////            .dataObject(v as Object)
////            .uniqueColumn("name")
////            .uniqueColumn("number")
////            .build()
//
//        var io4 = PostRaw.builder()
//            .scriptId(StringConstants.dBServerScriptURL)
//            .sheetId(StringConstants.DB_SHEET_ID)
//            .tabName(StringConstants.DB_TAB_APP_OWNER)
//            .dataObject(v as Object)
//            .build()
//
//        var deleteAll = Delete.builder()
//            .scriptId(StringConstants.dBServerScriptURL)
//            .sheetId(StringConstants.DB_SHEET_ID)
//            .tabName(StringConstants.DB_TAB_APP_OWNER)
//            .deleteAll()
//            .build()
//
//        var deleteAnd = Delete.builder()
//            .scriptId(StringConstants.dBServerScriptURL)
//            .sheetId(StringConstants.DB_SHEET_ID)
//            .tabName(StringConstants.DB_TAB_APP_OWNER)
//            .conditionAnd("name", "prasunmondal")
//            .build()
//
//        var deleteOr = Delete.builder()
//            .scriptId(StringConstants.dBServerScriptURL)
//            .sheetId(StringConstants.DB_SHEET_ID)
//            .tabName(StringConstants.DB_TAB_APP_OWNER)
//            .conditionOr("name", "prasunmondal")
//            .conditionOr("number", "9")
//            .conditionOr("number", "")
//            .build()
//
//        var insertDataSequence = PostSequence.builder()
//            .scriptId(StringConstants.dBServerScriptURL)
//            .sheetId(StringConstants.DB_SHEET_ID)
//            .tabName(StringConstants.DB_TAB_APP_OWNER)
//            .dataObject(listOf("prasun", "mondal"))
//            .build()
//
////        executor(t)
////        executor(t2)
////        executor(tOr)
////        executor(ipca)
////        executor(io0)
////        executor(io1)
////        executor(io2)
////        executor(io3)
////        executor(io4)
////        executor(t3)
////        executor(insertDataSequence)
////        executor(deleteAnd)
////        executor(deleteOr)
////        executor(deleteAll)
//
//
//        var insertDataSequence2 = PostSequence.builder()
//            .scriptId(StringConstants.dBServerScriptURL)
//            .sheetId(StringConstants.DB_SHEET_ID)
//            .tabName(StringConstants.DB_TAB_APP_OWNER)
//            .dataObject(listOf("prasun", "mondal"))
//            .build()
//
//        var insertDataSequence3 = PostSequence.builder()
//            .scriptId(StringConstants.dBServerScriptURL)
//            .sheetId(StringConstants.DB_SHEET_ID)
//            .tabName(StringConstants.DB_TAB_APP_OWNER)
//            .dataObject(listOf("prasun", "mondal"))
//            .build()
//
//
//        val t4 = Get.builder()
//            .scriptId(StringConstants.dBServerScriptURL)
//            .sheetId(StringConstants.DB_SHEET_ID)
//            .tabName(StringConstants.DB_TAB_APP_OWNER)
//            .postCompletion(null)
//            .build()
//

    }

    private fun testDeleteAll() {
        val request = Delete.builder()
            .scriptId("")
            .sheetId(ProjectConfig.DB_SHEET_ID)
            .tabName(ProjectConfig.DB_TAB_APP_OWNER)
            .build()

        GScript.addRequest(request)
        GScript.execute(ProjectConfig.dBServerScriptURL)
    }
    private fun testGetAll() {
        val t4 = Get.builder()
            .scriptId(ProjectConfig.dBServerScriptURL)
            .sheetId(ProjectConfig.DB_SHEET_ID)
            .tabName(ProjectConfig.DB_TAB_APP_OWNER)
            .postCompletion(null)
            .build()

        GScript.addRequest(t4)
        GScript.addRequest(t4)
        GScript.addRequest(t4)
        GScript.execute(ProjectConfig.dBServerScriptURL)

//        val test_insert_java_obj = PostObject.builder()
//            .scriptId("")
//            .sheetId(StringConstants.DB_SHEET_ID)
//            .tabName(StringConstants.DB_TAB_APP_OWNER)
//            .dataObject(com.prasunmondal.postjsontosheets.Test("Dona", "Mondal") as Object)
//            .build()
//
//        GScript.add(test_insert_java_obj)
//        GScript.add(test_insert_java_obj)
//        GScript.execute(StringConstants.dBServerScriptURL)
//        GScript.add(test_insert_java_obj)
//        GScript.execute(StringConstants.dBServerScriptURL)
    }

//    fun testInsertObj() {
//        val test_insert_java_obj = InsertObject.builder()
//            .scriptId("")
//            .sheetId(ProjectConfig.DB_SHEET_ID)
//            .tabName(ProjectConfig.DB_TAB_APP_OWNER)
//            .dataObject(Test("Dona", "Mondal") as Object)
//            .build()
//
//        GScript.addRequest(test_insert_java_obj)
//        GScript.addRequest(test_insert_java_obj)
//        GScript.addRequest(test_insert_java_obj)
//        GScript.addRequest(test_insert_java_obj)
//        GScript.addRequest(test_insert_java_obj)
//        GScript.addRequest(test_insert_java_obj)
//        GScript.addRequest(test_insert_java_obj)
//        GScript.addRequest(test_insert_java_obj)
//        GScript.addRequest(test_insert_java_obj)
//        GScript.addRequest(test_insert_java_obj)
//        GScript.addRequest(test_insert_java_obj)
//        GScript.addRequest(test_insert_java_obj)
//        GScript.addRequest(test_insert_java_obj)
//        GScript.addRequest(test_insert_java_obj)
//        GScript.addRequest(test_insert_java_obj)
//        GScript.execute(ProjectConfig.dBServerScriptURL)
//        GScript.addRequest(test_insert_java_obj)
//        GScript.execute(ProjectConfig.dBServerScriptURL)
//    }

    fun executor(call: APIRequests) {
        val result = call.execute()
        println("DB-Call: Raw Response:" + result.content)
//        println("DB-Call: Response Code:" + result.getResponseCode())
    }
}

class TestClass {
    var number: Int
    var name: String
    var second: Secondary
    var arrayList: ArrayList<String>
    var map: Map<String, String>

    constructor(
        number: Int,
        name: String,
        secondary: Secondary,
        arrayList: ArrayList<String>,
        map: Map<String, String>
    ) {
        this.number = number
        this.name = name
        this.second = secondary
        this.arrayList = arrayList
        this.map = map
    }

    override fun toString(): String {
        return "TestClass: number: " + number +
                " name: " + name +
                " arrayList: " + arrayList +
                " map: " + map +
                " secondary:" + second
    }

    companion object {

        fun parseDeleteResponse(jsonString: String): String? {
            if (jsonString.contains("SUCCESS:"))
                return "SUCCESS"
            throw java.lang.Exception("DELETE FAILED");
        }


    }
}

class Secondary {
    var seconday: String

    constructor(seconday: String) {
        this.seconday = seconday
    }

    override fun toString(): String {
        return seconday
    }
}

class Test {
    var name = ""
    var title = ""

    constructor(name: String, title: String) {
        this.name = name
        this.title = title
    }
}