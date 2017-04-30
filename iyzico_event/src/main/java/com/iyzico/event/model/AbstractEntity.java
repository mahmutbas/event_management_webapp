package com.iyzico.event.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Created by TCMBAS on 25/04/2017.
 */
@Data
@MappedSuperclass
public abstract class AbstractEntity implements Serializable
{
    public static String DEFAULT_DELETED_VALUE = "0";

    @Transient
    @JsonIgnore
    private String internalId;

    public AbstractEntity()
    {
        this.internalId = UUID.randomUUID().toString();
    }

    /**
     * globally unique identifier for the entity
     */
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "com.iyzico.event.util.UpperCaseUUIDGenerator")
    @Access(AccessType.PROPERTY)
    private String id;

    @Column(columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    /**
     * defines record deleted or not if value is zero then record is not deleted all other values denotes deleted record.
     */
    private String deleted = DEFAULT_DELETED_VALUE;
}
