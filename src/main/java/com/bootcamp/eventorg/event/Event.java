package com.bootcamp.eventorg.event;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.time.LocalDateTime;

public record Event(
        @Id Integer id,
        @NotEmpty
        String title,
        LocalDateTime startOn,
        LocalDateTime completeOn,
        @Positive
        Integer participant,
        Location location,
        @Version Integer version

) {
        public Event{
                if(!completeOn.isAfter(startOn)){
                        throw new IllegalArgumentException("Complete must be after start");
                }
        }
}
