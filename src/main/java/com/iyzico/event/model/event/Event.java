package com.iyzico.event.model.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Sets;
import com.iyzico.event.model.AbstractEntity;
import com.iyzico.event.model.ticket.SpecialDiscount;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by TCMBAS on 26/04/2017.
 */
@Entity
@Data
@Table(name = "T_EVENT")
@ToString(callSuper = true, of = "")
@DynamicUpdate
@Where(clause = "DELETED = '0'")
public class Event extends AbstractEntity
{
    private String eventName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate eventDate;
    private String addressText;
    private Double addressLatitude;
    private Double addressLongitude;
    @Column(length = 500)
    private String aboutEvent; //information about event
    @Column(length = 500)
    private String aboutHost; //information about organizator

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "event", orphanRemoval = true)
    @Where(clause = "DELETED = '0'")
    private Set<SpecialDiscount> specialDiscounts = Sets.newHashSet();

    public Event()
    {
    }

    public void setAddressLatitude(Double addressLatitude)
    {
        if (addressLatitude != null)
        {
            checkArgument(addressLatitude <= 90.0 && addressLatitude >= -90.0);
        }
        this.addressLatitude = addressLatitude;
    }

    public void setAddressLongitude(Double addressLongitude)
    {
        if (addressLongitude != null)
        {
            checkArgument(addressLongitude <= 180.0 && addressLongitude >= -180.0);
        }
        this.addressLongitude = addressLongitude;
    }

    public Event(String eventName)
    {
        this.eventName = eventName;
        checkArgument(StringUtils.isNotEmpty(eventName), "eventName can not be empty");
    }

    public Event(String eventName, LocalDate eventDate)
    {
        this.eventName = eventName;
        this.eventDate = eventDate;
        checkArgument(StringUtils.isNotEmpty(eventName), "eventName can not be empty");
    }
}
