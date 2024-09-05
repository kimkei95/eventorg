package com.bootcamp.eventorg.event;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/events")
public class EventController {
//    @GetMapping("/hello")
//    String home(){
//        return "hello guest";
//    }
    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
    //get all data
    @GetMapping("")
    List<Event>findAll(){
        return eventRepository.findAll();
    }


    //get by id

//    @GetMapping("/api/events/{id}")
//    public Event findById(@PathVariable Integer id){
//        return  eventRepository.findById(id);
//    }
@GetMapping("/{id}")
Event findById(@PathVariable Integer id){
    Optional<Event> event = eventRepository.findById(id);
    if(event.isEmpty()){
        throw new EventNotFoundException();
    }return event.get();

}
//post data
@ResponseStatus(HttpStatus.CREATED)
@PostMapping("")
    void create(@Valid  @RequestBody Event event){
        eventRepository.create(event);

}
//PUT
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")

    void update(@Valid @RequestBody Event event,
                @PathVariable Integer id)
    {
        eventRepository.update(event,id);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping ("/{id}")
    void delete(@PathVariable Integer id){
        eventRepository.delete(id);
    }

    @GetMapping("/location")
    Optional<Event>findByLocation(@RequestParam String location){
        return eventRepository.findByLocation(location);
    }
}


