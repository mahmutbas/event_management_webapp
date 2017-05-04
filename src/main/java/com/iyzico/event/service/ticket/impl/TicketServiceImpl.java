package com.iyzico.event.service.ticket.impl;

import com.google.common.collect.ImmutableList;
import com.iyzico.event.dao.ticket.SpecialDiscountRepository;
import com.iyzico.event.dao.ticket.TicketRepository;
import com.iyzico.event.model.dto.BinNumberDTO;
import com.iyzico.event.model.dto.CheckoutResultDTO;
import com.iyzico.event.model.dto.enums.Result;
import com.iyzico.event.model.event.Event;
import com.iyzico.event.model.ticket.SpecialDiscount;
import com.iyzico.event.model.ticket.Ticket;
import com.iyzico.event.service.BaseService;
import com.iyzico.event.service.ticket.TicketService;
import com.iyzico.event.service.ticket.enums.BankCard;
import com.iyzico.event.webservice.exception.WebServiceException;
import com.iyzico.event.webservice.iyzipay.InquireBinResultWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.iyzico.event.service.ticket.enums.BankCard.AKBANK;
import static com.iyzico.event.service.ticket.enums.BankCard.FINANSBANK;
import static com.iyzico.event.service.ticket.enums.BankCard.GARANTI_BANKASI;
import static com.iyzico.event.service.ticket.enums.BankCard.HALK_BANK;
import static com.iyzico.event.service.ticket.enums.BankCard.IS_BANKASI;

/**
 * Created by TCMBAS on 04/05/2017.
 */
@Service("ticketService")
public class TicketServiceImpl extends BaseService implements TicketService
{
    private static final String DEBIT_CARD = "DEBIT_CARD";
    public static final List<BankCard> VALID_BANKS_FOR_SALE = ImmutableList.of(GARANTI_BANKASI, IS_BANKASI, AKBANK, FINANSBANK);
    private final TicketRepository ticketRepository;
    private final InquireBinResultWebService inquireBinResultWebService;
    private final SpecialDiscountRepository specialDiscountRepository;

    @Autowired
    public TicketServiceImpl(@Qualifier("ticketRepository") TicketRepository ticketRepository,
                             InquireBinResultWebService inquireBinResultWebService,
                             @Qualifier("specialDiscountRepository") SpecialDiscountRepository specialDiscountRepository)
    {
        this.ticketRepository = ticketRepository;
        this.inquireBinResultWebService = inquireBinResultWebService;
        this.specialDiscountRepository = specialDiscountRepository;
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
    public CheckoutResultDTO checkForValidCard(String cardNumber)
    {
        try
        {
            BinNumberDTO binNumberDTO = inquireBinResultWebService.retrieveBinNumber(cardNumber);
            if (binNumberDTO == null)
            {
                logger.info(Result.FAILURE.toString() + " {} Card number invalid!", cardNumber);
                return new CheckoutResultDTO(Result.FAILURE, "Card number invalid!");
            }
            if (DEBIT_CARD.equalsIgnoreCase(binNumberDTO.getCardType()) && binNumberDTO.getBackCode() != HALK_BANK.getBankCode())
            {
                logger.info(Result.FAILURE.toString() + " {} Can not use debit card!", cardNumber);
                return new CheckoutResultDTO(Result.FAILURE, "Can not use debit card!");
            }
            if (!DEBIT_CARD.equalsIgnoreCase(binNumberDTO.getCardType()) &&
                    !VALID_BANKS_FOR_SALE.stream().filter(bankCard -> bankCard.getBankCode() == binNumberDTO.getBackCode()).iterator().hasNext())
            {
                logger.info(Result.FAILURE.toString() + " {} Card can not use!", cardNumber);
                return new CheckoutResultDTO(Result.FAILURE, "Card can not use!");
            }
        }
        catch (WebServiceException e)
        {
            return new CheckoutResultDTO(Result.FAILURE, e.getMessage());
        }
        return new CheckoutResultDTO(Result.SUCCESS, "Başarılı");
    }

    @Override
    public BigDecimal adjustFinalPrice(Event event, String discountCode, LocalDate registrationDate) throws Exception
    {
        Ticket ticket = ticketRepository.findTicketByEventIdAndDatePeriod(event.getId(), registrationDate);
        checkArgument(ticket != null && ticket.getPrice() != null, "Ticket information and price can not be null");
        SpecialDiscount specialDiscount = specialDiscountRepository.findByDiscountCodeAndDate(discountCode, registrationDate);
        //also specialDiscount can get from ticket with stream

        return specialDiscount == null ? ticket.getPrice()
                : ticket.getPrice().multiply(BigDecimal.valueOf((100 - specialDiscount.getPercentage()) * 0.01));
    }
}
