package com.volodymyrvasylyshyn.helperserver.security;

public class SecurityConstants {
    public static final String SECRET = "SecretKeyGenJWT";
    public static final String TOKEN_PREFIX = "BEARER ";
    public static final String HEADER_STRING = "Authorization";
    public static final String[] allowedUrls = {"/swagger-resources/**",
            "/swagger-ui/**",
            "/v2/api-docs",
            "/api/v1/users/forgot",
            "/api/v1/users/forgot/*",
            "/api/v1/auth/*",
            "/api/v1/announcements/telegram",
            "/api/oauth2/**", "/oauth2/**"
    };
    public static final String CONTENT_TYPE = "application/json";
//    public static final long EXPIRATION_TIME= 600_000;// 10 min


}
