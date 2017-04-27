package com.iyzico.event.service.event.impl;

import com.iyzico.event.dao.event.EventRepository;
import com.iyzico.event.model.event.Event;
import com.iyzico.event.service.BaseService;
import com.iyzico.event.service.event.EventService;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by TCMBAS on 27/04/2017.
 */
@Service("eventService")
public class EventServiceImpl extends BaseService implements EventService
{
    @Setter
    @Resource
    private EventRepository eventRepository;

    @Override
    public List<Event> findAllEvents()
    {
        return eventRepository.findAll();
    }
}
