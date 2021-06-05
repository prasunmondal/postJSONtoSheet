package com.prasunmondal.postjsontosheets;

import com.prasunmondal.postjsontosheets.operations.DeploymentID;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StringConstants {
//      Script ID: 1rm7eBju_fdhGl7faifjtMNrmtl43fOM2SdHly3K7kJ5gOwQIV9ZyCZkY
//      Script Edit: https://script.google.com/home/projects/1rm7eBju_fdhGl7faifjtMNrmtl43fOM2SdHly3K7kJ5gOwQIV9ZyCZkY/edit
//    public static String DB_SERVER_SCRIPT_URL = "https://script.google.com/macros/s/AKfycbwzBamFrEhCDbH1B31N9yfK7XT91UGxBf7-78kAL63fR7u6FBI/exec";
//    public static String deployment_id =
//        "AKfycbyN8JbyAj0ZWbhc43Fbj1HtD2mhAd_qGu_N3qhNhjDKoRKO7qZMXFE1AH_iAt1V2gnH5g"
//        ;
//    public static String DB_SERVER_SCRIPT_URL =

    public static String DB_SHEET_ID = "1uu_VTWuTpLyjyl_X6VbqdkYkKoR-mkMzk-a4AuYpkjc";
    public static String DB_TAB_APP_OWNER = "Sheet2";
    public static String DB_OPERATION = "INSERT_OBJECT";

    public static String getDBServerScriptURL() throws FileNotFoundException {
        return "https://script.google.com/macros/s/" + DeploymentID.Companion.getDeploymentId() + "/exec";
    }
}