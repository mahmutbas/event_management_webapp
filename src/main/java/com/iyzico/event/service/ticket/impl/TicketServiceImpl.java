package com.iyzico.event.service.ticket.impl;

import com.iyzico.event.dao.ticket.TicketRepository;
import com.iyzico.event.model.ticket.Ticket;
import com.iyzico.event.service.BaseService;
import com.iyzico.event.service.ticket.TicketService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by TCMBAS on 04/05/2017.
 */
@Service("ticketService")
public class TicketServiceImpl extends BaseService implements TicketService
{
    @Resource(name = "ticketRepository")
    private TicketRepository ticketRepository;

    @Override
    public List<Ticket> findAll()
    {
        return ticketRepository.findAll();
    }

    @Override
    public List<Ticket> findAllTicketByEventId(String eventId)
    {
        return ticketRepository.findAllTicketByEventId(eventId);
    }
}
