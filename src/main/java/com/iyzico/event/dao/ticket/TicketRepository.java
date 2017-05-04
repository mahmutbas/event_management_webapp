package com.iyzico.event.dao.ticket;

import com.iyzico.event.model.ticket.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by TCMBAS on 03/05/2017.
 */
@Repository("ticketRepository")
public interface TicketRepository extends JpaRepository<Ticket, String>
{
    List<Ticket> findAll();

    @Query(value = "select ticket from Ticket ticket where ticket.event.id=:eventId and :currentDate between ticket.startDate and ticket.endDate")
    Ticket findTicketByEventIdAndDatePeriod(@Param("eventId") String eventId, @Param("currentDate") LocalDate currentDate);

    @Query(value = "select ticket from Ticket ticket where ticket.event.id=:eventId")
    List<Ticket> findAllTicketByEventId(@Param("eventId") String eventId);
}
