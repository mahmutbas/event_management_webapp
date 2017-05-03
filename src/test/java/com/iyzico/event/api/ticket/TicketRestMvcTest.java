package com.iyzico.event.api.ticket;

import com.google.common.collect.Lists;
import com.iyzico.event.api.ticket.impl.TicketRestControllerImpl;
import com.iyzico.event.model.ticket.Ticket;
import com.iyzico.event.model.ticket.enums.PeriodType;
import com.iyzico.event.service.ticket.TicketService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by TCMBAS on 04/05/2017.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(TicketRestControllerImpl.class)
public class TicketRestMvcTest
{
    @Autowired
    private MockMvc mvc;

    @MockBean(name = "ticketService")
    private TicketService ticketService;

    @Test
    public void testFindAllTicketByEventIdCheckExpectedJsonFromApi() throws Exception
    {
        given(this.ticketService.findAllTicketByEventId("FFFE7232-2D23-11E7-93AE-92361F002671")).willReturn(Lists.newArrayList(new Ticket(LocalDate.of(2018, Month.FEBRUARY, 7), LocalDate.of(2018, Month.AUGUST, 6), PeriodType.LAGGARD, BigDecimal.TEN)));
        this.mvc.perform(get("/api/ticket/byeventid?eventId={eventId}", "FFFE7232-2D23-11E7-93AE-92361F002671").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":null,\"createTime\":null,\"deleted\":\"0\",\"startDate\":\"2018-02-07\",\"endDate\":\"2018-08-06\",\"periodType\":\"LAGGARD\",\"price\":10,\"event\":null}]"));
    }
}
