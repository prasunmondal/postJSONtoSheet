package com.prasunmondal.postjsontosheets

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun post(view: View) {
//        try {
            val sd = InsertUniqueDataToDB(
                "data",
                StringConstants.DB_TAB_APP_OWNER, "1,2"
            ) { }
            sd.execute()
//        } catch (e: Exception) {
//            Log.e("SignUp: ", Arrays.toString(e.stackTrace))
//            System.err.println("Error in calling")
//        }
    }


}

class TestClass {
    var number: Int
    var name: String
    var second: Secondary
    var arrayList: ArrayList<String>
    var map: Map<String, String>

    constructor(number: Int, name: String, secondary: Secondary, arrayList: ArrayList<String>, map: Map<String, String>) {
        this.number = number
        this.name = name
        this.second = secondary
        this.arrayList = arrayList
        this.map = map
    }
}

class Secondary {
    var seconday: String

    constructor(seconday: String) {
        this.seconday = seconday
    }
}