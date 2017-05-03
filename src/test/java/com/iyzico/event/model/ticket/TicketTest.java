package com.iyzico.event.model.ticket;

import com.iyzico.event.model.ticket.enums.PeriodType;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

/**
 * Created by TCMBAS on 03/05/2017.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class TicketTest
{
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Ticket ticket;

    private static Ticket createNewTicket()
    {
        return new Ticket(LocalDate.of(2018, Month.FEBRUARY, 7), LocalDate.of(2018, Month.AUGUST, 6), PeriodType.LAGGARD, BigDecimal.TEN);
    }

    @Test
    public void testPeriodCreateNewObject() throws Exception
    {
        createNewTicket();
    }

    @Test
    public void testStartDateConstructorNullThrowsException() throws Exception
    {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("startDate can not be null!");
        ticket = new Ticket(null, LocalDate.of(2018, Month.AUGUST, 6), PeriodType.LAGGARD, BigDecimal.TEN);
    }

    @Test
    public void testOnConstructorStartDateAfterEndDateThrowsException() throws Exception
    {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Start date can not be after then end date");
        ticket = new Ticket(LocalDate.of(2019, Month.FEBRUARY, 7), LocalDate.of(2018, Month.AUGUST, 6), PeriodType.LAGGARD, BigDecimal.TEN);
    }

    @Test
    public void testStartDateSetterNullThrowsException() throws Exception
    {
        ticket = createNewTicket();
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("startDate can not be null!");
        ticket.setStartDate(null);
    }

    @Test
    public void testOnStartDateSetterStartDateAfterEndDateThrowsException() throws Exception
    {
        ticket = createNewTicket();
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Start date can not be after then end date");
        ticket.setStartDate(LocalDate.of(2019, Month.FEBRUARY, 7));
    }

    @Test
    public void testEndDateConstructorNullThrowsException() throws Exception
    {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("endDate can not be null!");
        ticket = new Ticket(LocalDate.of(2018, Month.AUGUST, 6), null, PeriodType.LAGGARD, BigDecimal.TEN);
    }

    @Test
    public void testEndDateSetterNullThrowsException() throws Exception
    {
        ticket = createNewTicket();
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("endDate can not be null!");
        ticket.setEndDate(null);
    }

    @Test
    public void testOnEndDateSetterStartDateAfterEndDateThrowsException() throws Exception
    {
        ticket = createNewTicket();
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Start date can not be after then end date");
        ticket.setEndDate(LocalDate.of(2017, Month.FEBRUARY, 7));
    }

    @Test
    public void testPeriodTypeConstructorNullThrowsException() throws Exception
    {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Period type can not be null!");
        ticket = new Ticket(LocalDate.of(2018, Month.FEBRUARY, 7), LocalDate.of(2018, Month.AUGUST, 6), null, BigDecimal.TEN);
    }

    @Test
    public void testPeriodTypeSetterNullThrowsException() throws Exception
    {
        ticket = createNewTicket();
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Period type can not be null!");
        ticket.setPeriodType(null);
    }

    @Test
    public void testPriceConstructorNullThrowsException() throws Exception
    {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Price can not be null");
        ticket = new Ticket(LocalDate.of(2018, Month.FEBRUARY, 7), LocalDate.of(2018, Month.AUGUST, 6), PeriodType.LAGGARD, null);
    }

    @Test
    public void testPriceConstructorNegativeThrowsException() throws Exception
    {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Price can not be negative");
        ticket = new Ticket(LocalDate.of(2018, Month.FEBRUARY, 7), LocalDate.of(2018, Month.AUGUST, 6), PeriodType.LAGGARD, BigDecimal.valueOf(-1L));
    }

    @Test
    public void testPriceSetterNullThrowsException() throws Exception
    {
        ticket = createNewTicket();
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Price can not be null");
        ticket.setPrice(null);
    }

    @Test
    public void testPriceSetterNegativeThrowsException() throws Exception
    {
        ticket = createNewTicket();
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Price can not be negative");
        ticket.setPrice(BigDecimal.valueOf(-1L));
    }

}
