package com.tech4bytes.mbrosv3.Utils.centralCache

import com.prasunmondal.GSheet.AppContexts.AppContexts
import com.prasunmondal.GSheet.Logs.LogMe
import com.prasunmondal.GSheet.serializer.IOObjectToFile

class CacheFilesList : java.io.Serializable {
    companion object {

        private val cacheFilesFileName: String = "cacheFilesIndex.dat"

        fun getCacheFilesList(): MutableList<String> {
            val readObj = IOObjectToFile()
            val list = try {
                readObj.ReadObjectFromFile(
                    AppContexts.get(),
                    cacheFilesFileName
                ) as MutableList<String>
            } catch (e: Exception) {
                LogMe.log("Couldn't read file: $cacheFilesFileName")
                mutableListOf()
            }
            return list
        }

        fun addToCacheFilesList(filename: String) {
            val list = getCacheFilesList()
            if (list.contains(filename))
                return
            list.add(filename)
            val writeObj = IOObjectToFile()
            writeObj.WriteObjectToFile(AppContexts.get(), cacheFilesFileName, list)
        }

        fun removeFromCacheFilesList(classKey: String) {
            val list = getCacheFilesList()
            if (!list.contains(classKey))
                return
            list.remove(classKey)
            val writeObj = IOObjectToFile()
            writeObj.WriteObjectToFile(AppContexts.get(), cacheFilesFileName, list)
        }

        fun clearCacheFilesList() {
            val writeObj = IOObjectToFile()
            writeObj.WriteObjectToFile(AppContexts.get(), cacheFilesFileName, null)
        }
    }
}