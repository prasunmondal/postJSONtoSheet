package com.prasunmondal.postjsontosheets

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import com.prasunmondal.postjsontosheets.operations.*
import java.lang.reflect.Type
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun get(view: View) {
        var t2: TestClass? = null



        val fetchDataFromDB = FetchDataFromDB("\"prasunmondal3\",deew", StringConstants.DB_TAB_APP_OWNER, "name,number") { p1 ->
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

    fun deleteConditionalAnd(view: View) {
        val deleteConditionalAnd = DELETE_CONDITIONAL_AND("\"prasunmondal3\",deew", StringConstants.DB_TAB_APP_OWNER, "name,number") { p1 ->
            var t2 = TestClass.parseJSONObject(
                    object : TypeToken<ArrayList<TestClass>>() {}.type,
                    JSONUtils.jsonStringCleanUp(p1)
            )
            println("Check -- Parsed Object: $t2")
        }.execute()
    }

    fun insertObject(view: View) {
            val sd = INSERT_OBJECT(
                "data",
                StringConstants.DB_TAB_APP_OWNER, "name,number"
            ) { }
            sd.execute()
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
        ): ArrayList<TestClass>? {
            Log.e("parsing", jsonString!!)
            if(type == null) {
                System.out.println("Null")
            } else {
                System.out.println(type)
            }
            var arrayLabel = "records"
            val parser = JsonParser()
            val jsonObject = parser.parse(jsonString).asJsonObject ?: return null
            var jsonarray: JsonArray? = null
            try {
                jsonarray = jsonObject.getAsJsonArray(arrayLabel)
            } catch (e: Exception) {
                Log.e("parseJSONObject", "Error while parsing")
            }
            val result: ArrayList<TestClass> = GsonBuilder().create().fromJson(
                jsonarray.toString(),
                type
            )
            return result
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