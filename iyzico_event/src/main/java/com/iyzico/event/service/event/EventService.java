package com.iyzico.event.service.event;

import com.iyzico.event.model.event.Event;
import com.iyzico.event.service.BaseService;

import java.util.List;

/**
 * Created by TCMBAS on 27/04/2017.
 */
public interface EventService
{
    public List<Event> findAllEvents();
}
