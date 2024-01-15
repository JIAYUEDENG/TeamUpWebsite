package org.alex.website.common;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import java.util.HashMap;
import java.util.Map;

public class LoginObject<T> {
    private Integer code; //编码：1成功，0和其它数字为失败

    private String msg; //错误信息

    private T data; //数据

    private Map map = new HashMap(); //动态数据

    public static <T> LoginObject<T> success(T object) {
        LoginObject<T> r = new LoginObject<T>();
        r.data = object;
        r.code = 1;
        return r;
    }

    public static <T> LoginObject<T> error(String msg) {
        LoginObject r = new LoginObject();
        r.msg = msg;
        r.code = 0;
        return r;
    }

    public LoginObject<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }
}
