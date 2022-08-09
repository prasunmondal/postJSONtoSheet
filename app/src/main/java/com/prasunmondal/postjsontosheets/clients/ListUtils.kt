package com.prasunmondal.postjsontosheets.clients

class ListUtils {

    companion object {
        fun getCSV(list: ArrayList<String>): String {
            var s = ""
            for (data in list) {
                if (list.get(0) != data)
                    s = "$s,"
                s = "$s$data"
            }
            return s
        }
    }
}