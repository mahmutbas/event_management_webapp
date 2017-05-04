package com.iyzico.event.api.ticket;

import com.iyzico.event.model.ticket.Ticket;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by TCMBAS on 04/05/2017.
 */
@RequestMapping(path = "/api")
public interface TicketRest
{
    //http://localhost:8080/api/ticket/byeventid?eventId=6D499BD5-2825-4A10-976E-8850D23CA36C
    @RequestMapping(path = "/ticket/byeventid", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    List<Ticket> findAllTicketByEventId(String eventId);
}
