package com.prasunmondal.postjsontosheets

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.prasunmondal.postjsontosheets.clients.delete.Delete
import com.prasunmondal.postjsontosheets.clients.get.Get
import com.prasunmondal.postjsontosheets.clients.get.IsPresentConditionalAnd
import com.prasunmondal.postjsontosheets.clients.post.serializable.PostObject
import com.prasunmondal.postjsontosheets.clients.post.raw.PostRaw
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
                .build().execute()
        println("bound: " + t.getResponseCode())
        println("bound: " + t.getRawResponse())

        var t2 = Get.builder()
            .scriptId(StringConstants.dBServerScriptURL)
            .sheetId(StringConstants.DB_SHEET_ID)
            .tabName(StringConstants.DB_TAB_APP_OWNER)
            .postCompletion(null)
            .conditionAnd("name","\"prasunmondal\"")
            .conditionAnd("number","9")
            .build().execute()

        println("bound: " + t2.getResponseCode())
        println("bound: " + t2.getRawResponse())


        var tOr = Get.builder()
            .scriptId(StringConstants.dBServerScriptURL)
            .sheetId(StringConstants.DB_SHEET_ID)
            .tabName(StringConstants.DB_TAB_APP_OWNER)
            .postCompletion(null)
            .conditionOr("name","\"prasunmondal\"")
            .conditionOr("number","9")
            .build().execute()

        println("bound: " + tOr.getResponseCode())
        println("bound: " + tOr.getRawResponse())

        var t23 = tOr.getParsedList<TestClass>()
        println(t23)

        var ipca = IsPresentConditionalAnd()
            .scriptId(StringConstants.dBServerScriptURL)
            .sheetId(StringConstants.DB_SHEET_ID)
            .tabName(StringConstants.DB_TAB_APP_OWNER)
            .keys("\"prasunmondal,9")
            .values("name,number")
            .build().execute()

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


        var io0response = io0.execute()
        println("bound: " + io0response.getResponseCode())
        var io1response = io1.execute()
        println("bound: " + io1response.getResponseCode())
        var io2response = io2.execute()
        println("bound: " + io2response.getResponseCode())
        var io3response = io3.execute()
        println("bound: " + io3response.getResponseCode())
        println(t.getRawResponse())
        println(t.getResponseCode())
        var io4response = io4.execute()
        println("bound: " + io4response.getResponseCode())
//        var deleteConditionalAndResponse = deleteAnd.execute()
//        println("bound: " + deleteConditionalAndResponse.getResponseCode())
//        var deleteConditionalOrResponse = deleteOr.execute()
//        println("bound: " + deleteConditionalOrResponse.getResponseCode())
//        var deleteResponse = deleteAll.execute()
//        println("bound: " + deleteResponse.getResponseCode())
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