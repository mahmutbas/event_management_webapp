package com.iyzico.event.api.event.impl;

import com.iyzico.event.api.BaseApiController;
import com.iyzico.event.api.event.EventRest;
import com.iyzico.event.model.event.Event;
import com.iyzico.event.service.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by TCMBAS on 27/04/2017.
 */
@RestController
public class EventRestControllerImpl extends BaseApiController implements EventRest
{
    @Autowired
    @Qualifier("eventService")
    public EventService eventService;

    @Override
    public List<Event> findAllEvents()
    {
        return eventService.findAllEvents();
    }
}
