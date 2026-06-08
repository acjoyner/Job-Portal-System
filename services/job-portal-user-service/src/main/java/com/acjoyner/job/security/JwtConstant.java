package com.acjoyner.job.security;

public class JwtConstant {
    // random string for jwt secret key, should be at least 256 bits (32 characters)
    // 32+ character secret for HMAC-SHA signing. Replace with secure random value in production.
    public static final String JWT_SECRET = "01234567890123456789012345678901";
    public static final String JWT_HEADER = "Authorization";

}
