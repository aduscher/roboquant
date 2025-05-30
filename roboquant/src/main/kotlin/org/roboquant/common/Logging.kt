/*
 * Copyright 2020-2025 Neural Layer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package org.roboquant.common

import org.slf4j.event.Level
import org.slf4j.spi.LoggingEventBuilder
import kotlin.reflect.KClass

/**
 * Simple Logging object that provides utility methods to create loggers and supports lazy logging
 * It is an implementation of [org.slf4j.Logger].
 */
object Logging {

    /**
     * Logger class that extends a SLF4J logger and allows for Kotlin idiomatic usage patterns
     */
    class Logger(private val slf4jLogger: org.slf4j.Logger) : org.slf4j.Logger by slf4jLogger {

        /**
         * @see org.slf4j.Logger.trace
         */
        inline fun trace(throwable: Throwable? = null, messageProducer: () -> String) {
            if (isTraceEnabled) trace(messageProducer(), throwable)
        }

        /**
         * @see org.slf4j.Logger.debug
         */
        inline fun debug(throwable: Throwable? = null, messageProducer: () -> String) {
            if (isDebugEnabled) debug(messageProducer(), throwable)
        }

        /**
         * @see org.slf4j.Logger.info
         */
        inline fun info(throwable: Throwable? = null, messageProducer: () -> String) {
            if (isInfoEnabled) info(messageProducer(), throwable)
        }

        /**
         * @see org.slf4j.Logger.warn
         */
        inline fun warn(throwable: Throwable? = null, messageProducer: () -> String) {
            if (isWarnEnabled) warn(messageProducer(), throwable)
        }

        /**
         * @see org.slf4j.Logger.error
         */
        inline fun error(throwable: Throwable? = null, messageProducer: () -> String) {
            if (isErrorEnabled) error(messageProducer(), throwable)
        }

        override fun makeLoggingEventBuilder(level: Level?): LoggingEventBuilder? {
            return slf4jLogger.makeLoggingEventBuilder(level)
        }

        override fun atLevel(level: Level?): LoggingEventBuilder? {
            return slf4jLogger.atLevel(level)
        }

        override fun isEnabledForLevel(level: Level?): Boolean {
            return slf4jLogger.isEnabledForLevel(level)
        }

        override fun atTrace(): LoggingEventBuilder? {
            return slf4jLogger.atTrace()
        }

        override fun atDebug(): LoggingEventBuilder? {
            return slf4jLogger.atDebug()
        }

        override fun atInfo(): LoggingEventBuilder? {
            return slf4jLogger.atInfo()
        }

        override fun atWarn(): LoggingEventBuilder? {
            return slf4jLogger.atWarn()
        }

        override fun atError(): LoggingEventBuilder? {
            return slf4jLogger.atError()
        }

    }

    /**
     * Get a logger based on the provided [clazz]
     */
    fun getLogger(clazz: KClass<*>): Logger {
        return getLogger(clazz.qualifiedName ?: "$clazz")
    }

    /**
     * Get a logger based on the provided [name]
     */
    internal fun getLogger(name: String): Logger {
        return Logger(org.slf4j.LoggerFactory.getLogger(name))
    }


}
