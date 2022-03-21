package com.autotests.restapi.models;

import lombok.Builder;

@Builder
public class PlayerRegistrationModel {
    public String username;
    public String password_change;
    public String password_repeat;
    public String email;
    public String name;
    public String surname;
    public String currency_code;
}