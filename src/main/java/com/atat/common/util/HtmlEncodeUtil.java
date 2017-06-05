package com.atat.common.util;

public class HtmlEncodeUtil {

    public static String encode(String str) {
        str = str.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\'", "'").replaceAll("\"", "&quot;").replaceAll("\n", "<br/>");
        return str;
    }

    public static String decode(String str) {
        str = str.replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("'", "\'").replaceAll("&quot;", "\"").replaceAll("<br/>", "\n");
        return str;
    }
}
