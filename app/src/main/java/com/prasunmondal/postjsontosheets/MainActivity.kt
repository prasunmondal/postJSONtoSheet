package com.prasunmondal.postjsontosheets

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun get(view: View) {
        var t2: TestClass? = null
        val ft = FetchDataFromDB("9", StringConstants.DB_TAB_APP_OWNER, "3") { p1 ->
            var p3 = p1
            p3 = p3.replace("\"\\\"","\"")
            p3 = p3.replace("\\\"\"","\"")

            // handle lists
            p3 = p3.replace("\":\"[\\\"","\":[\"")
            p3 = p3.replace("\\\",\\\"","\",\"")
            p3 = p3.replace("\\\"]\"","\"]")

            // handle maps
            p3 = p3.replace("\":\"{\\\"","\":{\"")
            p3 = p3.replace("\\\":\\\"","\":\"")
            p3 = p3.replace("\\\"}\"","\"}")

            p3 = p3.replace("{\"arraylist\":\"arraylist\",\"map\":\"map\",\"name\":\"name\",\"number\":\"number\",\"second\":\"second\"},","")
            println("Check -- Response to Parse p1: $p1")
            println("Check -- Response to Parse p3: $p3")
            var t2 = TestClass.parseJSONObject(object : TypeToken<ArrayList<TestClass>>() {}.type, p3)
            println("Check -- Parsed Object: $t2")
        }
        ft.execute()

    }

    fun post(view: View) {
            val sd = InsertUniqueDataToDB(
                "data",
                StringConstants.DB_TAB_APP_OWNER, "1,2"
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