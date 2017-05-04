package com.iyzico.event.service.event;

import com.iyzico.event.model.event.Event;

import java.util.List;

/**
 * Created by TCMBAS on 27/04/2017.
 */
public interface EventService
{
    List<Event> findAllEvents();

    Event findByEventName(String eventName);
}
