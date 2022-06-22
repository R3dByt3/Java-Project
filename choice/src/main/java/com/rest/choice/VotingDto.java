package com.rest.choice;

import org.springframework.data.annotation.Id;

public class VotingDto {
    @Id
    public String id;

    public String name;

    public VotingDto() {}

    public VotingDto(String name) {
        this.name = name;
    }
}
