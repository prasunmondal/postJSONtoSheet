package com.prasunmondal.libs

//import com.prasunmondal.GSheet.Clients.get.Get
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.prasunmondal.libs.AppContexts.AppContexts
import com.prasunmondal.libs.gsheet.clients.Tests.Test
import com.prasunmondal.libs.reflections.Tests.Tests

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppContexts.set(this)
        testAll()
    }

    fun testAll() {

//        Test.start()
        Tests()
    }
    private fun testGetAll() {
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