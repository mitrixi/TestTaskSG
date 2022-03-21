package com.autotests.restapi.models;

import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Accessors(chain = true)
public class AuthModel {
    private String grant_type;
    private String scope;
    private String username;
    private String password;
}