package com.autotests.restapi.models;

import lombok.Getter;

@Getter
public class PlayerModel {
    private Integer id;
    private Integer country_id;
    private String timezone_id;
    private String username;
    private String email;
    private String name;
    private String surname;
    private String gender;
    private String phone_number;
    private String birthdate;
    private Boolean bonuses_allowed;
    private Boolean is_verified;
}