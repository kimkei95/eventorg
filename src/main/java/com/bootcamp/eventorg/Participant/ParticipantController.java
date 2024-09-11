package com.bootcamp.eventorg.Participant;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/participant")
public class ParticipantController {
    public final ParticipantService participantService;
    public ParticipantController(ParticipantService participantService){
        this.participantService = participantService;
    }
    @GetMapping("")
    public List<Participant> findAll(){
        return participantService.findAll();
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Participant create(@Valid @RequestBody Participant participant){
        return participantService.create(participant);
    }
    @GetMapping("/{id}")
    public Participant findById(@PathVariable Integer id){
        return participantService.findById(id);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update (@Valid @RequestBody Participant participant,
                        @PathVariable Integer id){
        participantService.update(participant, id);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")

    public void delete(@PathVariable Integer id){
        participantService.delete(id);
    }

}
