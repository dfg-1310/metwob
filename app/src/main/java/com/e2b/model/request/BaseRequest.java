package com.e2b.model.request;


import com.e2b.utils.GsonUtils;

/**
 * Class is used to base request structure.
 */

public class BaseRequest<T> {

    public static <T> T fromJson(Class<T> tClass, String s) {
        return GsonUtils.parseJson(s, tClass);
    }

    public String toJson() {
        return GsonUtils.getJson(this);
    }

    public String emptyJson() {
        return "{}";
    }
}
