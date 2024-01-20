package org.alex.website.common;

/**
 * Save and get current user id
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    /**
     * set id
     */
    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }

    /**
     * get id
     */
    public static Long getCurrentId(){
        return threadLocal.get();
    }
}