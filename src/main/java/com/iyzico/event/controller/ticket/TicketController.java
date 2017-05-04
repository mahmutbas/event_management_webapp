package com.iyzico.event.controller.ticket;

import com.google.common.base.Preconditions;
import com.iyzico.event.controller.BaseController;
import com.iyzico.event.model.dto.CheckoutResultDTO;
import com.iyzico.event.model.dto.enums.Result;
import com.iyzico.event.model.event.Event;
import com.iyzico.event.service.ticket.TicketService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.Date;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by TCMBAS on 04/05/2017.
 */
@Controller(value = "ticketController")
@RequestScoped
public class TicketController extends BaseController
{
    @Setter
    @Getter
    private String cardNumber;
    @Setter
    @Getter
    private String discountCode;
    @Setter
    @Getter
    private Date registrationDate = new Date();


    private final TicketService ticketService;

    @Autowired
    public TicketController(@Qualifier("ticketService") TicketService ticketService)
    {
        this.ticketService = ticketService;
    }

    public void registerForTicket(Event event)
    {
        try
        {
            CheckoutResultDTO checkoutResult = checkNotNull(ticketService.checkForValidCard(cardNumber));
            Preconditions.checkArgument(Result.SUCCESS.equals(checkoutResult.getResult()), checkoutResult.getResultDescription());
            BigDecimal finalPrice = ticketService.adjustFinalPrice(event, discountCode, registrationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            jsfMessageUtil.addMessage(FacesMessage.SEVERITY_INFO, "Ticket registration successfull!", finalPrice + " TRY");
            logger.info(Result.SUCCESS + " {} price is {} TRY", cardNumber, finalPrice);
        }
        catch (Exception e)
        {
            logger.error("Ticket registeration error :", e);
            jsfMessageUtil.addMessage(FacesMessage.SEVERITY_ERROR, "Ticket registration error!", e.getMessage());
        }

    }

}
