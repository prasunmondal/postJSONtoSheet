package com.prasunmondal.postjsontosheets

import com.prasunmondal.postjsontosheets.operations.DeploymentID.Companion.deploymentId
import java.io.FileNotFoundException

object StringConstants {
    //      Script ID: 1rm7eBju_fdhGl7faifjtMNrmtl43fOM2SdHly3K7kJ5gOwQIV9ZyCZkY
    //      Script Edit: https://script.google.com/home/projects/1rm7eBju_fdhGl7faifjtMNrmtl43fOM2SdHly3K7kJ5gOwQIV9ZyCZkY/edit
    //    public static String DB_SERVER_SCRIPT_URL = "https://script.google.com/macros/s/AKfycbwzBamFrEhCDbH1B31N9yfK7XT91UGxBf7-78kAL63fR7u6FBI/exec";
    //    public static String deployment_id =
    //        "AKfycbyN8JbyAj0ZWbhc43Fbj1HtD2mhAd_qGu_N3qhNhjDKoRKO7qZMXFE1AH_iAt1V2gnH5g"
    //        ;
    //    public static String DB_SERVER_SCRIPT_URL =
    @JvmField
    var DB_SHEET_ID = "1uu_VTWuTpLyjyl_X6VbqdkYkKoR-mkMzk-a4AuYpkjc"
    var DB_TAB_APP_OWNER = "Sheet2"
    var DB_OPERATION = "INSERT_OBJECT"

    @JvmStatic
    @get:Throws(FileNotFoundException::class)
    val dBServerScriptURL: String
        get() = "https://script.google.com/macros/s/" + deploymentId + "/exec"
}