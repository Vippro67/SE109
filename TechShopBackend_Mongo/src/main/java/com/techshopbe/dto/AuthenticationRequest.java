package com.techshopbe.dto;

public class AuthenticationRequest {

    private String email;
    private String pswd;

    public AuthenticationRequest() {
    }

    public AuthenticationRequest(String email, String pswd) {
        this.email = email;
        this.pswd = pswd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getpswd() {
        return pswd;
    }

    public void setpswd(String pswd) {
        this.pswd = pswd;
    }
}
