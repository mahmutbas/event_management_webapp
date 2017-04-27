package com.iyzico.event.util;

import com.iyzico.event.service.event.EventService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by TCMBAS on 27/04/2017.
 */
@Service("serviceLocator")
public class ServiceLocator
{
    @Setter
    @Autowired
    @Qualifier("eventService")
    public EventService eventService;
}
