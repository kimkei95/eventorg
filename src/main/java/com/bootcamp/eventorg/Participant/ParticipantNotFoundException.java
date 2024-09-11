package com.bootcamp.eventorg.Participant;

public class ParticipantNotFoundException extends RuntimeException {
    public ParticipantNotFoundException(Integer id) {
        super("id not found");
    }
}
