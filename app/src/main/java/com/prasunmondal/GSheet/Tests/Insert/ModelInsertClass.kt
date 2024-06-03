package com.prasunmondal.GSheet.Tests.Insert

import com.prasunmondal.GSheet.Tests.ProjectConfig
import com.prasunmondal.GSheet.serializer.Tech4BytesSerializable

class ModelInsertObject {
    var name = ""
    var title = ""

    constructor(name: String, title: String) {
        this.name = name
        this.title = title
    }

    override fun toString(): String {
        return "ModelInsertObject(name='$name', title='$title')"
    }
}
object ModelInsertClass : Tech4BytesSerializable<ModelInsertObject>(
    ProjectConfig.dBServerScriptURL,
    ProjectConfig.DB_SHEET_ID,
    "Sheet2",
    query = null,
    appendInServer = true,
    appendInLocal = true)