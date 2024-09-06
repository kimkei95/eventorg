package com.bootcamp.eventorg.event;


import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface EventRepository extends ListCrudRepository<Event, Integer> {

    List<Event> findAllByLocation(String location);

    List<Event> findByTitle(String title);

    List<Event> findAllByLocationAndParticipant(String location, Integer participant);


    List<Event> findAllByParticipant(Integer participant);
}