package com.birol.security;

public interface ISecurityUserService {

    String validatePasswordResetToken(String token);

}
