package com.iyzico.event.service.ticket.impl;

import com.iyzico.event.dao.ticket.TicketRepository;
import com.iyzico.event.model.dto.BinNumberDTO;
import com.iyzico.event.model.dto.CheckoutResultDTO;
import com.iyzico.event.model.dto.enums.Result;
import com.iyzico.event.model.event.Event;
import com.iyzico.event.model.ticket.Ticket;
import com.iyzico.event.service.BaseService;
import com.iyzico.event.service.ticket.TicketService;
import com.iyzico.event.webservice.exception.IyzipayWebServiceException;
import com.iyzico.event.webservice.iyzipay.InquireBinResultWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by TCMBAS on 04/05/2017.
 */
@Service("ticketService")
public class TicketServiceImpl extends BaseService implements TicketService
{
    private final TicketRepository ticketRepository;
    private final InquireBinResultWebService inquireBinResultWebService;

    @Autowired
    public TicketServiceImpl(@Qualifier("ticketRepository") TicketRepository ticketRepository, InquireBinResultWebService inquireBinResultWebService)
    {
        this.ticketRepository = ticketRepository;
        this.inquireBinResultWebService = inquireBinResultWebService;
    }

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

    @Override
    public CheckoutResultDTO register(Event event, Ticket ticket, String cardNumber, String discountCode, LocalDate registrationDate)
    {
        //TODO event - ticket - registrationDate tutarlı mı??
        try
        {
            BinNumberDTO binNumberDTO = inquireBinResultWebService.retrieveBinNumber(cardNumber);
            if (binNumberDTO == null)
            {
                return new CheckoutResultDTO(Result.FAILURE, "Card number invalid!");
            }
            binNumberDTO.getBackCode();
            binNumberDTO.getCardType();
        }
        catch (IyzipayWebServiceException e)
        {
            return new CheckoutResultDTO(Result.FAILURE, e.getMessage());
        }

        //TODO girilen cardNumber ve bankalar valid mi?
        //TODO registrationDate ve discountCode a göre indirim uygula
        logger.info("Checkout completed");
        return new CheckoutResultDTO(Result.SUCCESS, "Başarılı", ticket.getPrice());
    }
}
