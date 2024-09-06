package com.bootcamp.eventorg.event;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/events")
public class EventController {

    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    // Get all data
    @GetMapping("")
    List<Event> findAll() {
        return eventRepository.findAll();
    }

    @GetMapping("/{id}")
    Event findById(@PathVariable Integer id) {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isEmpty()) {
            throw new EventNotFoundException();
        }
        return event.get();
    }

    // Post data
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@Valid @RequestBody Event event) {
        eventRepository.save(event);
    }

    // PUT
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@Valid @RequestBody Event event, @PathVariable Integer id) {
        if(!eventRepository.existsById(id)){
            throw new EventNotFoundException();
        }
        Event existingEvent = eventRepository.findById(id).orElseThrow(EventNotFoundException::new);
        Event updatedEvent = new Event(
                id,
                event.title(),
                event.startOn(),
                event.completeOn(),
                event.participant(),
                event.location(),
                existingEvent.version()
        );
        eventRepository.save(event);
    }

     @ResponseStatus(HttpStatus.NO_CONTENT)
     @DeleteMapping("/{id}")
     void delete(@PathVariable Integer id) {
         eventRepository.delete(eventRepository.findById(id).get());
     }


    @GetMapping("/location/{location}")
    List<Event> findByLocation(@PathVariable String location){
        return eventRepository.findAllByLocation(location);
    }
    @GetMapping("/title/{title}")
    List<Event> findByTitle(@PathVariable String title){
        return eventRepository.findByTitle(title);
    }
    @GetMapping("/location")
    List<Event> findByLocationAndParticipant(@RequestParam String location,
                                             @RequestParam(required = false) Integer participant){
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