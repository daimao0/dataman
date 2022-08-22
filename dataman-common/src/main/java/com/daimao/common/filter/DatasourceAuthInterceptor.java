package com.daimao.common.filter;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.daimao.common.exception.AuthException;
import com.daimao.common.utils.secure.ASEUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 数据源拦截器
 *
 * @author daimao
 * @date 2022/8/19 22:04
 */
public class DatasourceAuthInterceptor implements HandlerInterceptor {

    /**
     * @param request  请求
     * @param response 相应
     * @param handler  方法
     * @return 是否通过请求
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String datasourceToken = request.getHeader("datasourceToken");
        if (StrUtil.isBlank(datasourceToken)) {
            throw new AuthException("请先连接数据源");
        }
        try {
            String content = ASEUtils.decodeToken(datasourceToken);
            DatasourceInfo datasourceInfo = JSON.parseObject(content, DatasourceInfo.class);
            String[] uriArr = request.getRequestURI().split("/");
            //如果uri的第二个参数不是过滤的uri则认为其是库名
            if (!uriArr[3].contains("$")) {
                datasourceInfo.setUrl(datasourceInfo.getUrl() + "/" + uriArr[3]);
            }
            UserContext.setUserHolder(datasourceInfo);
        } catch (Exception e) {
            throw new AuthException("token解析失败，请重新连接数据源");
        }

        return true;
    }

    /**
     * @param request  请求
     * @param response 相应
     * @param handler  方法
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        UserContext.remove();
    }
}
