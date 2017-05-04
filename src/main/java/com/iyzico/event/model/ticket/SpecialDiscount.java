package com.iyzico.event.model.ticket;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.iyzico.event.model.AbstractEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
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
