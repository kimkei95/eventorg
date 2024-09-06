package com.bootcamp.eventorg.event;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record Event(
        Integer id,
        @NotEmpty
        String title,
        LocalDateTime startOn,
        LocalDateTime completeOn,
        @Positive
        Integer participant,
        Location location
) {
        public Event{
                if(!completeOn.isAfter(startOn)){
                        throw new IllegalArgumentException("Complete must be after start");
                }
        }
}
