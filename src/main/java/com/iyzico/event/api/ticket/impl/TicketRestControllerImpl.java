package com.iyzico.event.api.ticket.impl;

import com.iyzico.event.api.BaseApiController;
import com.iyzico.event.api.ticket.TicketRest;
import com.iyzico.event.model.ticket.Ticket;
import com.iyzico.event.service.ticket.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by TCMBAS on 04/05/2017.
 */
@RestController
public class TicketRestControllerImpl extends BaseApiController implements TicketRest
{
    private final TicketService ticketService;

    @Autowired
    public TicketRestControllerImpl(@Qualifier("ticketService") TicketService ticketService)
    {
        this.ticketService = ticketService;
    }

    @Override
    public List<Ticket> findAllTicketByEventId(String eventId)
    {
        return ticketService.findAllTicketByEventId(eventId);
    }
}
