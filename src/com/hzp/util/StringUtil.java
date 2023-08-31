package com.hzp.util;

/**
 * @BelongsProject: webbegin
 * @BelongsPackage: com.hzp.util
 * @Author: ASUS
 * @CreateTime: 2023-06-03  09:45
 * @Description: TODO
 * @Version: 1.0
 */
public class StringUtil {
    public static boolean isEmpty(String str){
        return str==null||"".equals(str);
    }

    public static boolean isNoEmpty(String str){
        return !isEmpty(str);
    }
}
