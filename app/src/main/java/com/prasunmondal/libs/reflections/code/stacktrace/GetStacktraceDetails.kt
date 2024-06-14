package com.prasunmondal.libs.reflections.code.stacktrace

object GetStacktraceDetails {

    fun className(caller: StackTraceElement): String {
        val className = caller.className
        return className.substring(className.lastIndexOf('.') + 1).substringBefore('$')
    }
}