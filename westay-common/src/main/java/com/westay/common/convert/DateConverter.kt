package com.westay.common.convert

import cn.hutool.core.util.StrUtil
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


/**
 * 日期转换
 *
 * @author Mark sunlightcs@gmail.com
 */
@Component
class DateConverter : Converter<String, Date?> {
    override fun convert(source: String): Date? {
        val value: String = source.trim { it <= ' ' }
        if (StrUtil.isEmpty(value)) {
            return null
        }
        return if (source.matches("^\\d{4}-\\d{1,2}$".toRegex())) {
            parseDate(source, formatList[0])
        } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$".toRegex())) {
            parseDate(source, formatList[1])
        } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$".toRegex())) {
            parseDate(source, formatList[2])
        } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$".toRegex())) {
            parseDate(source, formatList[3])
        } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}.*T.*\\d{1,2}:\\d{1,2}:\\d{1,2}.*..*$".toRegex())) {
            parseDate(source, formatList[4])
        } else {
            throw IllegalArgumentException("Invalid boolean value '$source'")
        }
    }

    /**
     * 格式化日期
     * @param dateStr String 字符型日期
     * @param format String 格式
     * @return Date 日期
     */
    fun parseDate(dateStr: String?, format: String?): Date? {
        var date: Date? = null
        try {
            val dateFormat: DateFormat = SimpleDateFormat(format)
            date = dateFormat.parse(dateStr)
        } catch (e: Exception) {
            logger.error("Formatted date with date: {} and format : {} ", dateStr, format)
        }
        return date
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(DateConverter::class.java)
        private val formatList: MutableList<String> = ArrayList(5)

        init {
            formatList.add("yyyy-MM")
            formatList.add("yyyy-MM-dd")
            formatList.add("yyyy-MM-dd HH:mm")
            formatList.add("yyyy-MM-dd HH:mm:ss")
            formatList.add("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
        }
    }
}

