package com.prasunmondal.postjsontosheets

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import com.prasunmondal.postjsontosheets.clients.fetch.Fetch
import com.prasunmondal.postjsontosheets.clients.fetch.IsPresentConditionalAnd
import com.prasunmondal.postjsontosheets.clients.insert.InsertObject
import com.prasunmondal.postjsontosheets.operations.*
import java.lang.reflect.Type
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        testAll()
    }

    fun testAll() {

        var t = Fetch.builder()
                .scriptId(StringConstants.dBServerScriptURL)
                .sheetId(StringConstants.DB_SHEET_ID)
                .tabName(StringConstants.DB_TAB_APP_OWNER)
                .postCompletion(null)
                .build().execute()
        println("bound: " + t.getResponseCode())
        println("bound: " + t.getRawResponse())

        var t2 = Fetch.builder()
            .scriptId(StringConstants.dBServerScriptURL)
            .sheetId(StringConstants.DB_SHEET_ID)
            .tabName(StringConstants.DB_TAB_APP_OWNER)
            .postCompletion(null)
            .conditionAnd("name","\"prasunmondal\"")
            .conditionAnd("number","9")
            .build().execute()

        println("bound: " + t2.getResponseCode())
        println("bound: " + t2.getRawResponse())


        var tOr = Fetch.builder()
            .scriptId(StringConstants.dBServerScriptURL)
            .sheetId(StringConstants.DB_SHEET_ID)
            .tabName(StringConstants.DB_TAB_APP_OWNER)
            .postCompletion(null)
            .conditionOr("name","\"prasunmondal\"")
            .conditionOr("number","9")
            .build().execute()

        println("bound: " + tOr.getResponseCode())
        println("bound: " + tOr.getRawResponse())

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

        var io0 = InsertObject.builder()
            .scriptId(StringConstants.dBServerScriptURL)
            .sheetId(StringConstants.DB_SHEET_ID)
            .tabName(StringConstants.DB_TAB_APP_OWNER)
            .dataObject(v as Object)
            .uniqueColumn("name")
            .uniqueColumn("number")
            .build()


        var io1 = InsertObject.builder()
            .scriptId(StringConstants.dBServerScriptURL)
            .sheetId(StringConstants.DB_SHEET_ID)
            .tabName(StringConstants.DB_TAB_APP_OWNER)
            .dataObject(v2 as Object).build()


        var io2 = InsertObject.builder()
            .scriptId(StringConstants.dBServerScriptURL)
            .sheetId(StringConstants.DB_SHEET_ID)
            .tabName(StringConstants.DB_TAB_APP_OWNER)
            .dataObject(v as Object).build()


        var io3 = InsertObject.builder()
            .scriptId(StringConstants.dBServerScriptURL)
            .sheetId(StringConstants.DB_SHEET_ID)
            .tabName(StringConstants.DB_TAB_APP_OWNER)
            .dataObject(v as Object)
            .uniqueColumn("name")
            .uniqueColumn("number")
            .build()


//        var io0response = io0.execute()
//        println("bound: " + io0response.getResponseCode())
//        var io1response = io1.execute()
//        println("bound: " + io1response.getResponseCode())
//        var io2response = io2.execute()
//        println("bound: " + io2response.getResponseCode())
//        var io3response = io3.execute()
//        println("bound: " + io3response.getResponseCode())
//        println(t.getRawResponse())
//        println(t.getResponseCode())

        val view = findViewById<Button>(R.id.DELETE_CONDITIONAL_AND)
//        deleteAll(view)
//        insertObjectUnique(view)
//        insertObject(view)
//        insertObject(view)
//        insertObject(view)
//        insertObject(view)
//        insertObjectUnique(view)
//        fetchAll(view)
//        fetchByConditionAND(view)
//        fetchByConditionOR(view)
//        insertRawObject(view)
//        isPresentConditionAND(view)
//
//
//        isPresentConditionOR(view)
        deleteConditionalAnd(view)
//        deleteConditionalOr(view)
    }

    fun get(view: View) {
        var t2: TestClass? = null
        FetchDataFromDB("\"prasunmondal3\",deew", StringConstants.DB_TAB_APP_OWNER, "name,number") { p1 ->
            var t2 = TestClass.parseJSONObject(
                    object : TypeToken<ArrayList<TestClass>>() {}.type,
                    JSONUtils.jsonStringCleanUp(p1)
            )
            println("Check -- Parsed Object: $t2")
        }.execute()
    }

    fun fetchByConditionOR(view: View) {
        val fetchByConditionOr = FETCH_BY_CONDITION_OR("\"prasunmondal\",9", StringConstants.DB_TAB_APP_OWNER, "name,number") { p1 ->
            var t2 = TestClass.parseJSONObject(
                    object : TypeToken<ArrayList<TestClass>>() {}.type,
                    JSONUtils.jsonStringCleanUp(p1)
            )
            println("Check -- Parsed Object: $t2")
        }.execute()
    }

    fun isPresentConditionOR(view: View) {
        IS_PRESENT_CONDITIONAL_OR("\"prasunmondal\",9", StringConstants.DB_TAB_APP_OWNER, "name,number") { p1 ->
            var t2 = TestClass.parseBoolean(p1)
            println("Check -- Parsed Object: $t2")
        }.execute()
    }

    fun isPresentConditionAND(view: View) {
        IS_PRESENT_CONDITIONAL_AND("\"prasunmondal\",9", StringConstants.DB_TAB_APP_OWNER, "name,number") { p1 ->
            var t2 = TestClass.parseBoolean(p1)
            println("Check -- Parsed Object: $t2")
        }.execute()
    }

    fun fetchByConditionAND(view: View) {
        val fetchByConditionAND = FETCH_BY_CONDITION_AND("\"prasunmondal\",9", StringConstants.DB_TAB_APP_OWNER, "name,number") { p1 ->
            var t2 = TestClass.parseJSONObject(
                    object : TypeToken<ArrayList<TestClass>>() {}.type,
                    JSONUtils.jsonStringCleanUp(p1)
            )
            println("Check -- Parsed Object: $t2")
        }.execute()
    }

    fun fetchAll(view: View) {
        val fetchAll = FETCH_ALL(StringConstants.DB_TAB_APP_OWNER) { p1 ->
            var t2 = TestClass.parseJSONObject(
                    object : TypeToken<ArrayList<TestClass>>() {}.type,
                    JSONUtils.jsonStringCleanUp(p1)
            )
            println("Check -- Parsed Object: $t2")
        }.execute()
    }

    fun deleteAll(view: View) {
        DELETE_ALL(StringConstants.DB_TAB_APP_OWNER) { p1 ->
            var t2 = TestClass.parseDeleteResponse(p1)
            println("Check -- Parsed Object: $t2")
        }.execute()
    }

    fun deleteConditionalAnd(view: View) {
        DELETE_CONDITIONAL_AND("prasunmondal,9", StringConstants.DB_TAB_APP_OWNER, "name,number") { p1 ->
            var t2 = TestClass.parseDeleteResponse(p1)
            println("Check -- Parsed Object: $t2")
        }.execute()
    }

    fun deleteConditionalOr(view: View) {
        DELETE_CONDITIONAL_OR("prasunmondal,9", StringConstants.DB_TAB_APP_OWNER, "name,number") { p1 ->
            var t2 = TestClass.parseDeleteResponse(p1)
            println("Check -- Parsed Object: $t2")
        }.execute()
    }

    fun insertRawObject(view: View) {
        INSERT_RAW_OBJECT(
                "data",
                StringConstants.DB_TAB_APP_OWNER
        ) { }.execute()
    }

    fun insertObject(view: View) {
        INSERT_OBJECT(
                "data",
                StringConstants.DB_TAB_APP_OWNER
        ) { }.execute()
    }

    fun insertObjectUnique(view: View) {
        INSERT_OBJECT_UNIQUE(
                "data",
                StringConstants.DB_TAB_APP_OWNER, "name,number"
        ) { }.execute()
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
        fun parseJSONObject(
                type: Type,
                jsonString: String?,
        ): ArrayList<*> {
            Log.e("parsing: ", jsonString!!)
            var arrayLabel = "records"
            val parser = JsonParser()
            val jsonObject = parser.parse(jsonString).asJsonObject
            var jsonarray: JsonArray? = null
            try {
                jsonarray = jsonObject.getAsJsonArray(arrayLabel)
            } catch (e: Exception) {
                Log.e("parseJSONObject", "Error while parsing")
            }
            val result: ArrayList<*> = GsonBuilder().create().fromJson(
                    jsonarray.toString(),
                    type
            )
            return result
        }

        fun parseBoolean(jsonString: String?): Boolean? {
            if (jsonString!!.contains("\"records\":true"))
                return true
            if (jsonString!!.contains("\"records\":false"))
                return false
            return null
        }

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