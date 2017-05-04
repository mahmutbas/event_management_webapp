package com.iyzico.event.service.speaker;

import com.iyzico.event.model.speaker.Speaker;

import java.util.List;

/**
 * Created by TCMBAS on 30/04/2017.
 */
public interface SpeakerService
{
    List<Speaker> findAllSpeakers();

    Speaker findByFullName(String fullName);

    List<Speaker> findAllSpeakersByEventId(String eventId);
}
