package com.iyzico.event.api.event;

import com.iyzico.event.model.event.Event;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by TCMBAS on 27/04/2017.
 */
@RequestMapping(path = "/api")
public interface EventRest
{
    //http://localhost:8080/api/event/all
    @RequestMapping(path = "/event/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    List<Event> findAllEvents();
}
