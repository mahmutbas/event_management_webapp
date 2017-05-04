package com.iyzico.event.model.ticket;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iyzico.event.model.AbstractEntity;
import com.iyzico.event.model.event.Event;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by TCMBAS on 04/05/2017.
 */
@Entity
@Data
@Table(name = "T_SPECIAL_DISCOUNT")
@ToString(callSuper = true, of = "")
@DynamicUpdate
@Where(clause = "DELETED = '0'")
public class SpecialDiscount extends AbstractEntity
{
    private String specialDay;
    private String code;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate specialDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate finishDate;
    private Integer percentage;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "EVENT_ID")
    private Event event;

    public SpecialDiscount()
    {
    }

    public SpecialDiscount(String code, LocalDate specialDate, LocalDate finishDate, Integer percentage)
    {
        this.code = code;
        this.specialDate = specialDate;
        this.finishDate = finishDate;
        this.percentage = checkNotNull(percentage, "Percentage can not be null!");
        checkArgument(percentage <= 100 && percentage >= 0, "Percentage must be between 0 and 100!");
    }

    public void setPercentage(Integer percentage)
    {
        this.percentage = checkNotNull(percentage, "Percentage can not be null!");
        checkArgument(percentage <= 100 && percentage >= 0, "Percentage must be between 0 and 100!");
    }

}
