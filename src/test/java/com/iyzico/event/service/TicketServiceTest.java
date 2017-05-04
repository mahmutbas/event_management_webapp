package com.iyzico.event.service;

import com.google.common.collect.Lists;
import com.iyzico.event.dao.ticket.TicketRepository;
import com.iyzico.event.model.ticket.Ticket;
import com.iyzico.event.model.ticket.enums.PeriodType;
import com.iyzico.event.service.ticket.TicketService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * Created by TCMBAS on 04/05/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketServiceTest
{
    @MockBean
    private TicketRepository ticketRepositoryMock;

    @Autowired
    @Qualifier("ticketService")
    public TicketService ticketService;

    @Before
    public void setUp()
    {
        given(this.ticketRepositoryMock.findAllTicketByEventId("6311D552-304F-11E7-93AE-92361F002671")).willReturn(Lists.newArrayList(new Ticket(LocalDate.of(2018, Month.FEBRUARY, 7), LocalDate.of(2018, Month.AUGUST, 6), PeriodType.LAGGARD, BigDecimal.TEN)));
        given(this.ticketRepositoryMock.findAll()).willReturn(Lists.newArrayList(new Ticket(LocalDate.of(2018, Month.FEBRUARY, 7), LocalDate.of(2018, Month.JULY, 9), PeriodType.LAGGARD, BigDecimal.TEN)));
    }

    @Test
    public void testFindAllTicketByEventId()
    {
        List<Ticket> ticket = ticketService.findAllTicketByEventId("6311D552-304F-11E7-93AE-92361F002671");
        Assert.assertNotNull(ticket);
        assertThat(ticket.iterator().next().getStartDate()).isEqualTo(LocalDate.of(2018, Month.FEBRUARY, 7));
    }

    @Test
    public void testFindAllTickets()
    {
        List<Ticket> tickets = ticketService.findAll();
        Assert.assertNotNull(tickets);
        assertThat(tickets.iterator().next().getEndDate()).isEqualTo(LocalDate.of(2018, Month.JULY, 9));
    }

    //todo diğer methodların testleri önemli

}
