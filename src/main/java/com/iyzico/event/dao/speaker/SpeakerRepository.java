package com.iyzico.event.dao.speaker;

import com.iyzico.event.model.speaker.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by TCMBAS on 30/04/2017.
 */
@Repository("speakerRepository")
public interface SpeakerRepository extends JpaRepository<Speaker, String>
{
    Speaker findByFullName(String fullName);

    @Query(value = "select speaker from Speaker speaker where speaker.event.id=:eventId")
    List<Speaker> findAllSpeakersByEventId(@Param("eventId") String eventId);

}
