package com.sohu.test;

import com.sohu.spi.LoggerService;

public class Main {
    public static void main(String[] args) {
        LoggerService loggerService = LoggerService.getService();
        loggerService.info("info");
        loggerService.debug("debug");
    }
}
