package br.com.sailboat.canoe.helper;

import java.util.List;


public class ListHelper {

    public static boolean isNotEmpty(List list) {
        return !isNullOrEmpty(list);
    }

    public static boolean isNullOrEmpty(List list) {
        return (list == null || list.isEmpty());
    }

}
