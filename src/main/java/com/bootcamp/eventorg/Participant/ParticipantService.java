package com.bootcamp.eventorg.Participant;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipantService {

    private final ParticipantRepository participantRepository;

    public ParticipantService(ParticipantRepository participantRepository){
        this.participantRepository = participantRepository;
    }
    List<Participant>findAll(){
        return participantRepository.findAll();
    }
    Participant findById(@PathVariable Integer id){
        Optional<Participant> participants = participantRepository.findById(id);
        if(participants.isEmpty()){
            throw new ParticipantNotFoundException(id);
        }return participants.get();
    }
    public Participant create(Participant participant){
        return participantRepository.save(participant);
    }
    void update(Participant participant, Integer id){
        if(!participantRepository.existsById(id)){
            throw  new ParticipantNotFoundException(id);
        }

        Participant updatedParticipant = new Participant(
                id,
                participant.getEvent_id(),
                participant.getName(),
                participant.getEmail()
        );
        participantRepository.save(updatedParticipant);
    }
    public void delete(Integer id){
        participantRepository.delete(
                participantRepository.findById(id)
                        .orElseThrow(()-> new ParticipantNotFoundException(id))
        );
    }



}
