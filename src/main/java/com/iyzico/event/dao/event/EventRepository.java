package com.iyzico.event.dao.event;

import com.iyzico.event.model.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by TCMBAS on 27/04/2017.
 */
@Repository("eventRepository")
public interface EventRepository  extends JpaRepository<Event, String>
{
    public Event findByEventName(String eventName);
}
