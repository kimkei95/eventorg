package com.bootcamp.eventorg.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcClientEventRepository {
    private static final Logger log  = LoggerFactory.getLogger(JdbcClientEventRepository.class);
    private final JdbcClient jdbcClient;

    public JdbcClientEventRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

//    public List<Event>findAll(){
//        return jdbcClient.sql("select*from event")
//                .query(Event.class).list();
//    }
//    public Optional<Event> findById(Integer id){
//        return jdbcClient.sql("select id,title,start_on,complete_on,participant,location" +
//                 " from Event where id = :id")
//                .param("id",id)
//                .query(Event.class)
//                .optional();
//    }
    public void create(Event event) {
        var updated = jdbcClient.sql("insert into Event(id,title,start_on,complete_on,participant,location) " +
                        "values (?,?,?,?,?,?)")
                .params(List.of(event.getId(),
                        event.getTitle(),
                        event.getStartOn(),
                        event.getCompleteOn(),
                        event.getParticipant(),
                        event.getLocation()))
                .update();

        Assert.state(updated == 1, "Failed to create event " + event.getTitle());
    }
//    public void update(Event event, Integer id){
//        var updated = jdbcClient.sql("update Event set title =?, start_on =?, complete_on=?," +
//                " participant=?, location=? where id=?")
//                .params(List.of(event.title(),
//                        event.startOn(),
//                        event.completeOn(),
//                        event.participant(),
//                        event.location().toString(),id))
//                .update();
//        Assert.state(updated ==1,"Failed to update event"+event.title());
//    }
//    public void delete(Integer id){
//        var updated =jdbcClient.sql("delete from Event where id = :id")
//                .param("id",id)
//                .update();
//        Assert.state(updated ==1,"Failed to delete event"+id);
//    }
//    public List<Event> findByLocation(String location){
//        return jdbcClient.sql("select id,title,start_on,complete_on,participant,location" +
//                " from Event where location =:location")
//                .param("location",location)
//                .query(Event.class)
//                .list();
//    }
    public  void saveAll(List<Event>events){
        events.forEach(this::create);
    }

    public int count() {
        return jdbcClient.sql("select*from event")
                .query()
                .listOfRows()
                .size();
    }
}


