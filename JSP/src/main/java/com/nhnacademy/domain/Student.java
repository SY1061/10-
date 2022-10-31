package com.nhnacademy.domain;

import lombok.Value;

@Value
public class Student {
    private final String id;
    private final String name;
    private final String gender;
    private final int age;
}
