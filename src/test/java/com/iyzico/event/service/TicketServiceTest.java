package com.iyzico.event.service;

import com.google.common.collect.Lists;
import com.iyzico.event.dao.ticket.TicketRepository;
import com.iyzico.event.model.dto.BinNumberDTO;
import com.iyzico.event.model.dto.CheckoutResultDTO;
import com.iyzico.event.model.dto.enums.Result;
import com.iyzico.event.model.event.Event;
import com.iyzico.event.model.ticket.Ticket;
import com.iyzico.event.model.ticket.enums.PeriodType;
import com.iyzico.event.service.ticket.TicketService;
import com.iyzico.event.webservice.exception.WebServiceException;
import com.iyzico.event.webservice.iyzipay.InquireBinResultWebService;
import org.junit.Assert;
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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static com.iyzico.event.service.ticket.enums.BankCard.GARANTI_BANKASI;
import static com.iyzico.event.service.ticket.enums.BankCard.HALK_BANK;
import static com.iyzico.event.service.ticket.enums.BankCard.HSBC;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * Created by TCMBAS on 04/05/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketServiceTest
{
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @MockBean
    private TicketRepository ticketRepositoryMock;

    @MockBean
    public InquireBinResultWebService inquireBinResultWebServiceMock;

    @Autowired
    @Qualifier("ticketService")
    public TicketService ticketService;

    CheckoutResultDTO checkoutResult;

    @Before
    public void setUp() throws WebServiceException
    {
        given(this.ticketRepositoryMock.findAllTicketByEventId("6311D552-304F-11E7-93AE-92361F002671")).willReturn(Lists.newArrayList(new Ticket(LocalDate.of(2018, Month.FEBRUARY, 7), LocalDate.of(2018, Month.AUGUST, 6), PeriodType.LAGGARD, BigDecimal.TEN)));
        given(this.ticketRepositoryMock.findAll()).willReturn(Lists.newArrayList(new Ticket(LocalDate.of(2018, Month.FEBRUARY, 7), LocalDate.of(2018, Month.JULY, 9), PeriodType.LAGGARD, BigDecimal.TEN)));
        given(this.inquireBinResultWebServiceMock.retrieveBinNumber("145794")).willReturn(new BinNumberDTO(62L, "CREDIT_CARD"));
        given(this.inquireBinResultWebServiceMock.retrieveBinNumber("123456")).willReturn(new BinNumberDTO(72L, "DEBIT_CARD"));
        given(this.inquireBinResultWebServiceMock.retrieveBinNumber("123457")).willReturn(new BinNumberDTO(HALK_BANK.getBankCode(), "DEBIT_CARD"));
        given(this.inquireBinResultWebServiceMock.retrieveBinNumber("123458")).willReturn(new BinNumberDTO(GARANTI_BANKASI.getBankCode(), "CREDIT_CARD"));
        given(this.inquireBinResultWebServiceMock.retrieveBinNumber("123459")).willReturn(new BinNumberDTO(HSBC.getBankCode(), "CREDIT_CARD"));
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

    @Test
    public void testCheckForValidCardIfCardCouldNotFind()
    {
        checkoutResult = ticketService.checkForValidCard("4545444");
        assertThat(checkoutResult.getResult() == Result.FAILURE);
        assertThat(checkoutResult.getResultDescription().equals("Card number invalid!"));
    }

    @Test
    public void testCheckForValidCardCardDebit()
    {
        checkoutResult = ticketService.checkForValidCard("123456");
        assertThat(checkoutResult.getResult() == Result.FAILURE);
        assertThat(checkoutResult.getResultDescription().equals("Can not use debit card!"));
    }

    @Test
    public void testCheckForValidCardCardDebitAndHalkBank()
    {
        checkoutResult = ticketService.checkForValidCard("123457");
        assertThat(checkoutResult.getResult() == Result.SUCCESS);
        assertThat(checkoutResult.getResultDescription().equals("Success"));
    }

    @Test
    public void testCheckForValidCardCreditCardBankInList()
    {
        checkoutResult = ticketService.checkForValidCard("123458");
        assertThat(checkoutResult.getResult() == Result.SUCCESS);
        assertThat(checkoutResult.getResultDescription().equals("Success"));
    }

    @Test
    public void testCheckForValidCardCreditCardBankNoInList()
    {
        checkoutResult = ticketService.checkForValidCard("123459");
        assertThat(checkoutResult.getResult() == Result.FAILURE);
        assertThat(checkoutResult.getResultDescription().equals("Card can not use!"));
    }

    @Test
    public void testCheckForValidCardCardNumberIsNull()
    {
        checkoutResult = ticketService.checkForValidCard(null);
        assertThat(checkoutResult.getResult() == Result.FAILURE);
        assertThat(checkoutResult.getResultDescription().equals("Card number invalid!"));
    }


    @Test
    public void adjustFinalPriceTicketIsNull() throws Exception
    {
        Event event = new Event("JavaDay");
        event.setId("dasdsdasd");
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Ticket information and price can not be null");
        ticketService.adjustFinalPrice(event, "Coddeee", LocalDate.now());

    }

}
