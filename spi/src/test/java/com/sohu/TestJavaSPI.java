package com.sohu;

import com.sohu.spi.LoggerService;
import org.junit.Test;

public class TestJavaSPI {
    @Test
    public void testLog(){
        LoggerService loggerService = LoggerService.getService();
        loggerService.info("info");
        loggerService.debug("debug");
    }
}
