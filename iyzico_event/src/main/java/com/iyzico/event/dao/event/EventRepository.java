package com.iyzico.event.dao.event;

import com.iyzico.event.model.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by TCMBAS on 27/04/2017.
 */
public interface EventRepository  extends JpaRepository<Event, String>
{
    public Event findByEventName(String eventName);

   /* public List<User> findAllUsers();

    @Query("select user from User user where user.email=:email and user.deleted='0'")
    public User findUserByEmail(@Param("email") String email);*/
}
