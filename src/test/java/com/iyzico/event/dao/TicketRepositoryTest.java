package com.iyzico.event.dao;

import com.iyzico.event.dao.ticket.TicketRepository;
import com.iyzico.event.model.event.Event;
import com.iyzico.event.model.ticket.Ticket;
import com.iyzico.event.model.ticket.enums.PeriodType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by TCMBAS on 03/05/2017.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class TicketRepositoryTest
{
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TicketRepository ticketRepository;

    @Test
    public void testPersistNewObject()
    {
        Ticket adjustedTicket = new Ticket(LocalDate.of(2018, Month.FEBRUARY, 7), LocalDate.of(2018, Month.AUGUST, 6), PeriodType.LAGGARD, BigDecimal.TEN);
        adjustedTicket.setEvent(new Event("JavaDay", LocalDate.now()));
        Ticket ticket = this.entityManager.persist(adjustedTicket);
        ticket = testFindTicketByEventIdAndCurrentDate(ticket.getEvent().getId(), LocalDate.of(2018, Month.FEBRUARY, 9));
        assertEquals(ticket.getEvent().getEventName(), "JavaDay");
        assertNotNull(ticket.getId());
    }

    @Test
    public void testRepositoryCanNotBeEmpty()
    {
        List<Ticket> ticketList = ticketRepository.findAll();
        assertNotNull(ticketList);
        assertThat(ticketList.size()).isGreaterThan(0);
    }

    @Test
    public void testFindTicketsByEventId()
    {
        List<Ticket> ticketList = ticketRepository.findAllTicketByEventId("6D499BD5-2825-4A10-976E-8850D23CA36C");
        assertNotNull(ticketList);
        assertThat(ticketList.size()).isGreaterThan(0);
    }

    private Ticket testFindTicketByEventIdAndCurrentDate(String eventId, LocalDate currentDate)
    {
        return ticketRepository.findTicketByEventIdAndDatePeriod(eventId, currentDate);
    }
}
