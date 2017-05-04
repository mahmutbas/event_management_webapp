package com.iyzico.event.service.ticket;

import com.iyzico.event.model.dto.CheckoutResultDTO;
import com.iyzico.event.model.event.Event;
import com.iyzico.event.model.ticket.Ticket;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by TCMBAS on 04/05/2017.
 */
public interface TicketService
{
    List<Ticket> findAll();

    List<Ticket> findAllTicketByEventId(String eventId);

    CheckoutResultDTO checkForValidCard(String cardNumber);

    BigDecimal adjustFinalPrice(Event event, String discountCode, LocalDate registrationDate) throws Exception;

}
