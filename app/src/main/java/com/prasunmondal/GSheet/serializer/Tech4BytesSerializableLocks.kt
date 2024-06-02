package com.prasunmondal.GSheet.serializer

object Tech4BytesSerializableLocks {
    private val locks = HashMap<String, Any>()

    fun getLock(key: String): Any? {
        synchronized(locks) {
            locks.putIfAbsent(key, Any())
            return locks[key]
        }
    }
}