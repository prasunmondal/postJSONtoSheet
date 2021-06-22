package com.prasunmondal.postjsontosheets

import com.prasunmondal.postjsontosheets.operations.DeploymentID.Companion.deploymentId
import java.io.FileNotFoundException

object StringConstants {
    @JvmField
    var DB_SHEET_ID = "1uu_VTWuTpLyjyl_X6VbqdkYkKoR-mkMzk-a4AuYpkjc"
    var DB_TAB_APP_OWNER = "Sheet2"

    @JvmStatic
    @get:Throws(FileNotFoundException::class)
    val dBServerScriptURL: String
        get() = "https://script.google.com/macros/s/" + deploymentId + "/exec"
}