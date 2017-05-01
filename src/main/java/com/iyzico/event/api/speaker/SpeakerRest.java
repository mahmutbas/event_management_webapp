package com.iyzico.event.api.speaker;

import com.iyzico.event.model.speaker.Speaker;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by TCMBAS on 30/04/2017.
 */
@RequestMapping(path = "/api")
public interface SpeakerRest
{
    //http://localhost:8080/api/event/all
    @RequestMapping(path = "/speaker/byeventid", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Speaker> findAllSpeakersByEventId(String eventId);
}
