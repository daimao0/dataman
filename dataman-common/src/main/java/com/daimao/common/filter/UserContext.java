package com.daimao.common.filter;


import io.netty.util.concurrent.FastThreadLocal;

/**
 * @author daimao
 * @date 2022/8/19 1:53
 */
public class UserContext {
    /**
     * 当前线程的线程变量
     */
    private static final FastThreadLocal<DatasourceInfo> USER_HOLDER = new FastThreadLocal<>();

    /**
     * 存入用户持有的信息
     *
     * @param datasourceInfo 数据源信息
     */
    public static void setUserHolder(DatasourceInfo datasourceInfo) {
        USER_HOLDER.set(datasourceInfo);
    }

    /**
     * 获得数据源信息
     *
     * @return 数据源信息
     */
    public static DatasourceInfo getDatasourceInfo() {
        return USER_HOLDER.get();
    }

    /**
     * 清楚fastThreadLocal，防止内存泄漏
     */
    public static void remove() {
        USER_HOLDER.remove();
    }
}

