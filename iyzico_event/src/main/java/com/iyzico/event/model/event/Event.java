package com.iyzico.event.model.event;

import com.iyzico.event.model.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Created by TCMBAS on 26/04/2017.
 */
@Entity
@Data
@Table(name = "T_EVENT")
@DynamicUpdate
@EqualsAndHashCode(callSuper = true, of = "")
@Where(clause = "DELETED = '0'")
public class Event extends AbstractEntity
{
    @NotNull
    private String eventName;

    public Event()
    {
    }

    public Event(String eventName)
    {
        this.eventName = eventName;
        Assert.hasLength(eventName, "eventName can not be empty");
        Assert.notNull(eventName, "eventName must not be null");
    }
}
