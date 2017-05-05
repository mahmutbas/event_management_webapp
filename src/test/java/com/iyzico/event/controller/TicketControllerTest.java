package com.iyzico.event.controller;

import com.iyzico.event.controller.ticket.TicketController;
import com.iyzico.event.model.dto.CheckoutResultDTO;
import com.iyzico.event.model.dto.enums.Result;
import com.iyzico.event.model.event.Event;
import com.iyzico.event.service.ticket.TicketService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * Created by TCMBAS on 05/05/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketControllerTest
{
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @MockBean(name = "ticketService")
    private TicketService ticketServiceMock;

    @Autowired
    @Qualifier("ticketController")
    public TicketController ticketController;

    @Before
    public void setUp()
    {
        given(this.ticketServiceMock.checkForValidCard("123456")).willReturn(new CheckoutResultDTO(Result.SUCCESS, "Success"));
        given(this.ticketServiceMock.checkForValidCard("123457")).willReturn(new CheckoutResultDTO(Result.FAILURE, "Can not use debit card!"));
    }

    @Test
    public void testRegisterForTicketCardvalidationFail()
    {
        ticketController.setCardNumber("123457");
        thrown.expect(NullPointerException.class);
        ticketController.registerForTicket(new Event());
    }

    @Test
    public void testControllerAcccessToFields()
    {
        ticketController.setCardNumber("123456");
        ticketController.setDiscountCode("DECA");
        ticketController.setRegistrationDate(new Date());
       assertThat("123456".equals(ticketController.getCardNumber()));
       assertThat("DECA".equals(ticketController.getDiscountCode()));
    }

}
