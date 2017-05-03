package com.iyzico.event.api.ticket;

import com.google.common.collect.Lists;
import com.iyzico.event.model.ticket.Ticket;
import com.iyzico.event.model.ticket.enums.PeriodType;
import com.iyzico.event.service.ticket.TicketService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * Created by TCMBAS on 04/05/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TicketRestTest
{
    @Autowired
    private TestRestTemplate template;

    @MockBean(name = "ticketService")
    private TicketService ticketService;

    @Test
    public void testFindAllTicketByEventId() throws Exception
    {
        given(ticketService.findAllTicketByEventId("6D499BD5-2825-4A10-976E-8850D23CA36C")).willReturn(Lists.newArrayList(new Ticket(LocalDate.of(2018, Month.FEBRUARY, 7), LocalDate.of(2018, Month.AUGUST, 6), PeriodType.LAGGARD, BigDecimal.TEN)));
        ResponseEntity<String> fact = template.getForEntity("/api/ticket/byeventid?eventId={eventId}", String.class, "6D499BD5-2825-4A10-976E-8850D23CA36C");
        assertThat(fact.getBody()).contains(PeriodType.LAGGARD.toString());
    }
}
