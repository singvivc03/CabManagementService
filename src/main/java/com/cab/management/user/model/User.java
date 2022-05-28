package com.cab.management.user.model;

import lombok.Builder;
import lombok.Getter;

@Builder(toBuilder = true)
@Getter
public class User {

    private final String id;
    private final String firstName;
    private final String lastName;
}
