package com.iyzico.event.service.speaker;

import com.iyzico.event.model.speaker.Speaker;

import java.util.List;

/**
 * Created by TCMBAS on 30/04/2017.
 */
public interface SpeakerService
{
    public List<Speaker> findAllSpeakers();

    public Speaker findByFullName(String fullName);

    public List<Speaker> findAllSpeakersByEventId(String eventId);
}
