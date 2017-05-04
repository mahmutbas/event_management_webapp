package com.iyzico.event.service.ticket;

import com.iyzico.event.model.ticket.Ticket;

import java.util.List;

/**
 * Created by TCMBAS on 04/05/2017.
 */
public interface TicketService
{
    List<Ticket> findAll();

    List<Ticket> findAllTicketByEventId(String eventId);
}
