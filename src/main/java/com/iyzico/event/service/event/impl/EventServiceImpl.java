package com.iyzico.event.service.event.impl;

import com.iyzico.event.dao.event.EventRepository;
import com.iyzico.event.model.event.Event;
import com.iyzico.event.service.BaseService;
import com.iyzico.event.service.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by TCMBAS on 27/04/2017.
 */
@Service("eventService")
public class EventServiceImpl extends BaseService implements EventService
{
    private final EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(@Qualifier("eventRepository") EventRepository eventRepository)
    {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> findAllEvents()
    {
        return eventRepository.findAll();
    }

    @Override
    public Event findByEventName(String eventName)
    {
        return eventRepository.findByEventName(eventName);
    }
}
