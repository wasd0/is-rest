package com.wasd.isrest.utils

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory

object Logger {

    val Log: org.slf4j.Logger = LoggerFactory.getLogger(Logger::class.java)

    fun tryConvertJsonToString(json: Any): String? {
        try {
            return ObjectMapper().writeValueAsString(json)
        } catch (e: JsonProcessingException) {
            Log.error("Json object not valid")
            return null
        }
    }
}