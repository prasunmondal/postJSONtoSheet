package com.tech4bytes.mbrosv3.Utils.centralCache

import com.prasunmondal.libs.Logs.LogMe
import com.tech4bytes.mbrosv3.Utils.Date.Date_Utils
import java.time.LocalDateTime

class CacheModel : java.io.Serializable {
    var entryTime: LocalDateTime
    var expiryTime: LocalDateTime
    var content: Any?

    constructor(content: Any?) {
        entryTime = LocalDateTime.now()
        expiryTime = Date_Utils.getNextTimeOccurrenceTimestamp(1)
//        Toast.makeText(AppContexts.get(), "$entryTime - $expiryTime", Toast.LENGTH_SHORT).show()
        LogMe.log("$entryTime - $expiryTime")
        this.content = content
    }

    override fun toString(): String {
        return "CacheModel(entryTime=$entryTime, expiryTime=$expiryTime, content=$content)"
    }
}