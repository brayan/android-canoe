package br.com.sailboat.canoe.helper;

public class StringHelper {

    public static boolean isNotEmpty(String text) {
        return !isNullOrEmpty(text);
    }

    public static boolean isNullOrEmpty(String text) {
        return text == null || text.trim().isEmpty();
    }

    public static String upperCaseFirstLetter(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }

    public static String getValueOrEmptyString(String value) {
        if (StringHelper.isNotEmpty(value)) {
            return value;
        } else {
            return "";
        }
    }

}
