package com.volodymyrvasylyshyn.helperserver.request;


public record ResetPasswordRequest(String token,String password) { }
