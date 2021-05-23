package com.prasunmondal.postjsontosheets;

import org.json.JSONObject;

import java.net.URL;
import java.util.function.Consumer;

public class FetchDataFromDB {
    private Consumer<String> onCompletion;
    private JSONObject postDataParams = new JSONObject();
    private URL scriptUrl;

    public FetchDataFromDB(String keys, String tabName, String column, Consumer<String> onCompletion) throws Exception {
        this.onCompletion = onCompletion;

        scriptUrl = new URL(StringConstants.DB_SERVER_SCRIPT_URL);

        postDataParams.put("opCode", "FETCH_OBJECT");
        postDataParams.put("sheetId", StringConstants.DB_SHEET_ID);
        postDataParams.put("tabName", tabName);
        postDataParams.put("keys", keys);
        postDataParams.put("searchColumn", column);
    }

    public void execute() {
        ExecutePostCalls c = new ExecutePostCalls(scriptUrl, postDataParams, onCompletion);
        c.execute();
    }
}
