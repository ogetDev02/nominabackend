package com.oget.ogetpro.utils;

import org.slf4j.Logger;
import java.util.Collections;
import java.util.List;

public class Helper
{
    public static <T> List<T> emptyIfNull(final List<T> iterable) {
    	return iterable == null ? Collections.<T> emptyList() : iterable;
    }
    
    public static void writeLog(final Logger logger, final String type, final String msg) {
        if (type.equals("INFO")) {
            logger.info(msg);
        }
        else if (type.equals("ERROR")) {
            logger.error(msg);
        }
        else if (type.equals("WARN")) {
            logger.warn(msg);
        }
        else if (type.equals("DEBUG")) {
            logger.debug(msg);
            System.out.println("[" + type + "] - " + msg);
        }
        else if (type.equals("TRACE")) {
            logger.trace(msg);
        }
    }
    
    public static void writeLog(final Logger logger, final String type, String msg, final String codCamara) {
        msg = "[" + codCamara + "] " + msg;
        if (type.equals("INFO")) {
            logger.info(msg);
        }
        else if (type.equals("ERROR")) {
            logger.error(msg);
        }
        else if (type.equals("WARN")) {
            logger.warn(msg);
        }
        else if (type.equals("DEBUG")) {
            logger.debug(msg);
            System.out.println("[" + type + "] - " + msg);
        }
        else if (type.equals("TRACE")) {
            logger.trace(msg);
        }
    }
    
    public static void writeLog(final Logger logger, final String type, final String msg, final Exception e) {
        if (type.equals("INFO")) {
            logger.info(msg);
        }
        else if (type.equals("ERROR")) {
            logger.error(msg);
        }
        else if (type.equals("WARN")) {
            logger.warn(msg);
        }
        else if (type.equals("DEBUG")) {
            logger.debug(msg);
        }
        else if (type.equals("TRACE")) {
            logger.trace(msg);
        }
        System.out.println("[" + type + "] - " + msg);
        if (e != null) {
            logger.error("[EXCEPTION]: " + e.getMessage());
        }
    }
    
    public static String processDBMessage(final String dbResult, final String part) {
        String response = "";
        if (dbResult != null) {
            if (part.equals("CODE")) {
                response = dbResult.substring(0, 1);
            }
            else if (part.equals("MSG")) {
                response = dbResult.substring(2);
            }
        }
        return response;
    }
    
    public static boolean isNull(final Long data) {
        return data == null || data.equals(0L);
    }
    
    public static boolean isNull(final Object data) {
        return data == null || data.equals(0);
    }
    
    public static boolean isNullOrEmpty(final String data) {
        return data == null || data.isEmpty();
    }
    
    public static String getParameterRY(final String param, final int part) {
        String response = "";
        if (param != null) {
            if (part == 1) {
                response = param.substring(0, 1);
            }
            else if (part == 2 && param.length() > 2) {
                response = param.substring(2);
            }
        }
        return response;
    }
}