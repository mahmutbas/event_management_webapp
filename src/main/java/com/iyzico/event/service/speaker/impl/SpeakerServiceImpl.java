package com.iyzico.event.service.speaker.impl;

import com.iyzico.event.dao.speaker.SpeakerRepository;
import com.iyzico.event.model.speaker.Speaker;
import com.iyzico.event.service.BaseService;
import com.iyzico.event.service.speaker.SpeakerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by TCMBAS on 30/04/2017.
 */
@Service("speakerService")
public class SpeakerServiceImpl extends BaseService implements SpeakerService
{
    @Resource(name = "speakerRepository")
    private SpeakerRepository speakerRepository;

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
