package com.prasunmondal.postjsontosheets

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.prasunmondal.postjsontosheets.clients.commons.APICalls
import com.prasunmondal.postjsontosheets.clients.delete.Delete
import com.prasunmondal.postjsontosheets.clients.get.Get
import com.prasunmondal.postjsontosheets.clients.get.IsPresentConditionalAnd
import com.prasunmondal.postjsontosheets.clients.post.serializable.PostObject
import com.prasunmondal.postjsontosheets.clients.post.raw.PostRaw
import com.prasunmondal.postjsontosheets.clients.post.raw.PostSequence
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        testAll()
    }

    fun testAll() {

        var t = Get.builder()
                .scriptId(StringConstants.dBServerScriptURL)
                .sheetId(StringConstants.DB_SHEET_ID)
                .tabName(StringConstants.DB_TAB_APP_OWNER)
                .postCompletion(null)
                .build()

        var t3 = Get.builder()
            .scriptId(StringConstants.dBServerScriptURL)
            .sheetId(StringConstants.DB_SHEET_ID)
            .tabName(StringConstants.DB_TAB_APP_OWNER)
            .postCompletion(null)
            .build()

        var t2 = Get.builder()
            .scriptId(StringConstants.dBServerScriptURL)
            .sheetId(StringConstants.DB_SHEET_ID)
            .tabName(StringConstants.DB_TAB_APP_OWNER)
            .postCompletion(null)
            .conditionAnd("name","\"prasunmondal\"")
            .conditionAnd("number","9")
            .build()

        var tOr = Get.builder()
            .scriptId(StringConstants.dBServerScriptURL)
            .sheetId(StringConstants.DB_SHEET_ID)
            .tabName(StringConstants.DB_TAB_APP_OWNER)
            .postCompletion(null)
            .conditionOr("name","\"prasunmondal\"")
            .conditionOr("number","9")
            .build()


        var ipca = IsPresentConditionalAnd()
            .scriptId(StringConstants.dBServerScriptURL)
            .sheetId(StringConstants.DB_SHEET_ID)
            .tabName(StringConstants.DB_TAB_APP_OWNER)
            .keys("\"prasunmondal,9")
            .values("name,number")
            .build()

        var sp: ArrayList<String> = mutableListOf("Prasun", "Dona") as ArrayList<String>
        val map = mutableMapOf<String, String>()
        map["one"] = "1"
        map.put("two","two")
        map.put("three","3")
        var v = TestClass(9, "prasunmondal", Secondary("ding-ding-ding"), sp, map)
        var v2 = TestClass(9, "\"prasunmondal\"", Secondary("ding-ding-ding"), sp, map)

        var io0 = PostObject.builder()
            .scriptId(StringConstants.dBServerScriptURL)
            .sheetId(StringConstants.DB_SHEET_ID)
            .tabName(StringConstants.DB_TAB_APP_OWNER)
            .dataObject(v as Object)
            .uniqueColumn("name")
            .uniqueColumn("number")
            .build()


        var io1 = PostObject.builder()
            .scriptId(StringConstants.dBServerScriptURL)
            .sheetId(StringConstants.DB_SHEET_ID)
            .tabName(StringConstants.DB_TAB_APP_OWNER)
            .dataObject(v2 as Object).build()


        var io2 = PostObject.builder()
            .scriptId(StringConstants.dBServerScriptURL)
            .sheetId(StringConstants.DB_SHEET_ID)
            .tabName(StringConstants.DB_TAB_APP_OWNER)
            .dataObject(v as Object).build()


        var io3 = PostObject.builder()
            .scriptId(StringConstants.dBServerScriptURL)
            .sheetId(StringConstants.DB_SHEET_ID)
            .tabName(StringConstants.DB_TAB_APP_OWNER)
            .dataObject(v as Object)
            .uniqueColumn("name")
            .uniqueColumn("number")
            .build()

        var io4 = PostRaw.builder()
            .scriptId(StringConstants.dBServerScriptURL)
            .sheetId(StringConstants.DB_SHEET_ID)
            .tabName(StringConstants.DB_TAB_APP_OWNER)
            .dataObject(v as Object)
            .build()

        var deleteAll = Delete.builder()
            .scriptId(StringConstants.dBServerScriptURL)
            .sheetId(StringConstants.DB_SHEET_ID)
            .tabName(StringConstants.DB_TAB_APP_OWNER)
            .deleteAll()
            .build()

        var deleteAnd = Delete.builder()
            .scriptId(StringConstants.dBServerScriptURL)
            .sheetId(StringConstants.DB_SHEET_ID)
            .tabName(StringConstants.DB_TAB_APP_OWNER)
            .conditionAnd("name","prasunmondal")
            .build()

        var deleteOr = Delete.builder()
            .scriptId(StringConstants.dBServerScriptURL)
            .sheetId(StringConstants.DB_SHEET_ID)
            .tabName(StringConstants.DB_TAB_APP_OWNER)
            .conditionOr("name","prasunmondal")
            .conditionOr("number","9")
            .conditionOr("number","")
            .build()

        var insertDataSequence = PostSequence.builder()
            .scriptId(StringConstants.dBServerScriptURL)
            .sheetId(StringConstants.DB_SHEET_ID)
            .tabName(StringConstants.DB_TAB_APP_OWNER)
            .dataObject(listOf("prasun", "mondal"))
            .build()

        executor(t)
        executor(t2)
        executor(tOr)
        executor(ipca)
        executor(io0)
        executor(io1)
        executor(io2)
        executor(io3)
        executor(io4)
        executor(t3)
        executor(insertDataSequence)
        executor(deleteAnd)
        executor(deleteOr)
        executor(deleteAll)

    }

    fun executor(call: APICalls) {
        val result = call.execute()
        println("DB-Call: Raw Response:" + result.getRawResponse())
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