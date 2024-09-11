package com.bootcamp.eventorg.event;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EventNotFoundException extends RuntimeException {
public EventNotFoundException(Integer id){
    super("event not found");
}
}
