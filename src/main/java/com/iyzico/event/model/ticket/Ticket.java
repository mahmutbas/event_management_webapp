package com.iyzico.event.model.ticket;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.Sets;
import com.iyzico.event.model.AbstractEntity;
import com.iyzico.event.model.event.Event;
import com.iyzico.event.model.ticket.enums.PeriodType;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by TCMBAS on 03/05/2017.
 */
@Entity
@Data
@Table(name = "T_TICKET")
@ToString(callSuper = true, of = "")
@DynamicUpdate
@Where(clause = "DELETED = '0'")
public class Ticket extends AbstractEntity
{
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private PeriodType periodType;

    @Column(scale = 2)
    private BigDecimal price = BigDecimal.ZERO;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "EVENT_ID")
    private Event event;

    public Ticket()
    {
    }

    public Ticket(LocalDate startDate, LocalDate endDate, PeriodType periodType, BigDecimal price)
    {
        this.startDate = checkNotNull(startDate, "startDate can not be null!");
        this.endDate = checkNotNull(endDate, "endDate can not be null!");
        this.periodType = checkNotNull(periodType, "Period type can not be null!");
        this.price = checkNotNull(price, "Price can not be null");
        checkArgument(price.signum() != -1, "Price can not be negative");
        checkArgument(startDate.isBefore(endDate), "Start date can not be after then end date");
    }

    public void setStartDate(LocalDate startDate)
    {
        checkNotNull(startDate, "startDate can not be null!");
        checkArgument(endDate != null && startDate != null ? startDate.isBefore(endDate) : true, "Start date can not be after then end date");
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate)
    {
        checkNotNull(endDate, "endDate can not be null!");
        checkArgument(endDate != null && startDate != null ? startDate.isBefore(endDate) : true, "Start date can not be after then end date");
        this.endDate = endDate;
    }

    public void setPeriodType(PeriodType periodType)
    {
        checkNotNull(periodType, "Period type can not be null!");
        this.periodType = periodType;
    }

    public void setPrice(BigDecimal price)
    {
        checkNotNull(price, "Price can not be null");
        checkArgument(price.signum() != -1, "Price can not be negative");
        this.price = price;
    }
}
