package ru.netology.delivery;

import lombok.Value;

@Value
public class RegistrationInfo {
    private String city;
    private String name;
    private String phone;
}