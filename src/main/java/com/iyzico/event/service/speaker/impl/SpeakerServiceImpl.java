package com.iyzico.event.service.speaker.impl;

import com.iyzico.event.dao.speaker.SpeakerRepository;
import com.iyzico.event.model.speaker.Speaker;
import com.iyzico.event.service.BaseService;
import com.iyzico.event.service.speaker.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by TCMBAS on 30/04/2017.
 */
@Service("speakerService")
public class SpeakerServiceImpl extends BaseService implements SpeakerService
{
    private final SpeakerRepository speakerRepository;

    @Autowired
    public SpeakerServiceImpl(@Qualifier("speakerRepository") SpeakerRepository speakerRepository)
    {
        this.speakerRepository = speakerRepository;
    }

    @Override
    public List<Speaker> findAllSpeakers()
    {
        return speakerRepository.findAll();
    }

    @Override
    public Speaker findByFullName(String fullName)
    {
        return speakerRepository.findByFullName(fullName);
    }

    @Override
    public List<Speaker> findAllSpeakersByEventId(String eventId)
    {
        return speakerRepository.findAllSpeakersByEventId(eventId);
    }
}
