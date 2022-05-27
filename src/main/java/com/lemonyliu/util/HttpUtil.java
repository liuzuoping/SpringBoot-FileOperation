package com.lemonyliu.util;


import com.lemonyliu.constant.CommonConstant;

import javax.servlet.http.HttpServletResponse;

public class HttpUtil {
    /**
     * 添加跨域的响应头
     *
     * @param response
     */
    public static void addCorsResponseHeader(HttpServletResponse response) {
        response.setHeader(CommonConstant.CorsConfigEnum.CORS_ORIGIN.getKey(), CommonConstant.CorsConfigEnum.CORS_ORIGIN.getValue());
        response.setHeader(CommonConstant.CorsConfigEnum.CORS_CREDENTIALS.getKey(), CommonConstant.CorsConfigEnum.CORS_CREDENTIALS.getValue());
        response.setHeader(CommonConstant.CorsConfigEnum.CORS_METHODS.getKey(), CommonConstant.CorsConfigEnum.CORS_METHODS.getValue());
        response.setHeader(CommonConstant.CorsConfigEnum.CORS_MAX_AGE.getKey(), CommonConstant.CorsConfigEnum.CORS_MAX_AGE.getValue());
        response.setHeader(CommonConstant.CorsConfigEnum.CORS_HEADERS.getKey(), CommonConstant.CorsConfigEnum.CORS_HEADERS.getValue());
    }

    public static void addCommonResponseHeader(String title, HttpServletResponse response) {
        response.reset();
        HttpUtil.addCorsResponseHeader(response);
        if (title.substring(title.length()-4, title.length()).equals(".pdf")) {
            response.setHeader(CommonConstant.CONTENT_TYPE_STR, "application/pdf");
            response.setContentType("application/pdf");
        } else if (title.substring(title.length()-4, title.length()).equals(".doc")) {
            response.setHeader(CommonConstant.CONTENT_TYPE_STR, "application/msword");
            response.setContentType("application/msword");
        } else {
            response.setHeader(CommonConstant.CONTENT_TYPE_STR, "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        }
    }
}

