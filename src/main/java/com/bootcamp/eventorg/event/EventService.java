package com.bootcamp.eventorg.event;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    // Get all data
//    @GetMapping("")
    List<Event> findAll() {
        return eventRepository.findAll();
    }

//    @GetMapping("/{id}")
    Event findById(@PathVariable Integer id) {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isEmpty()) {
            throw new EventNotFoundException(id);
        }
        return event.get();
    }

    // Post data
//    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping("")
    public Event create(Event event) {
        return eventRepository.save(event);
    }

    // PUT
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @PutMapping("/{id}")
    void update( Event event, Integer id) {
        if(!eventRepository.existsById(id)){
            throw new EventNotFoundException(id);
        }
        Event existingEvent = eventRepository.findById(id).orElseThrow(() -> new EventNotFoundException(id));
        Event updatedEvent = new Event(
                id,
                event.getTitle(),
                event.getStartOn(),
                event.getCompleteOn(),
                event.getParticipant(),
                event.getLocation(),
                existingEvent.getVersion()
        );
        eventRepository.save(updatedEvent);
    }

//
    public void delete(Integer id) {
        eventRepository.delete(
                eventRepository
                .findById(id)
                .orElseThrow(()-> new EventNotFoundException(id)));
    }


//    @GetMapping("/location/{location}")
    List<Event> findByLocation(@PathVariable String location){
        return eventRepository.findAllByLocation(location);
    }
//    @GetMapping("/title/{title}")
    List<Event> findByTitle(@PathVariable String title){
        return eventRepository.findByTitle(title);
    }
//    @GetMapping("/location")
    public List<Event> findByLocationAndParticipant(String location,
                                             Integer participant){
        if(location!= null && participant!=null){
            return eventRepository.findAllByLocationAndParticipant(location,participant);
        } else if (location!=null) {
            return eventRepository.findAllByLocation(location);
        } else if (participant!=null) {
            return eventRepository.findAllByParticipant(participant);
        }else{
            return eventRepository.findAll();
        }

    }

}
