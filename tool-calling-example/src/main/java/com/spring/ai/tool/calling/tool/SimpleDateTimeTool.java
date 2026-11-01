package com.spring.ai.tool.calling.tool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.context.i18n.LocaleContextHolder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SimpleDateTimeTool {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Tool(description = "Get the current date and time in users zone.")
    public String getCurrentDateTime(){
        this.logger.info("Tool calling");
        this.logger.info("Get the current date and time in users zone");
        return LocalDateTime
                .now()
                .atZone(LocaleContextHolder.getTimeZone().toZoneId())
                .toString();
    }

    @Tool(description = "Set the alarm for given time.")
     void setAlram(@ToolParam(description = "Time in ISO-8601 format") String time){
        var dateTime = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME);
        this.logger.info("set the alarm for given time. {}",dateTime);
    }
}
