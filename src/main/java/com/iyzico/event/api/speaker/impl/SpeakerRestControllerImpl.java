package com.iyzico.event.api.speaker.impl;

import com.iyzico.event.api.BaseApiController;
import com.iyzico.event.api.speaker.SpeakerRest;
import com.iyzico.event.model.speaker.Speaker;
import com.iyzico.event.service.speaker.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by TCMBAS on 30/04/2017.
 */
@RestController
public class SpeakerRestControllerImpl extends BaseApiController implements SpeakerRest
{
    private final SpeakerService speakerService;

    @Autowired
    public SpeakerRestControllerImpl(@Qualifier("speakerService") SpeakerService speakerService)
    {
        this.speakerService = speakerService;
    }

    @Override
    public List<Speaker> findAllSpeakersByEventId(String eventId)
    {
        return speakerService.findAllSpeakersByEventId(eventId);
    }
}
