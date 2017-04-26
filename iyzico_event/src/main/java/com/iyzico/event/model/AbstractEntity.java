package com.iyzico.event.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.util.Date;
import java.util.UUID;

/**
 * Created by TCMBAS on 25/04/2017.
 */

@Data
@MappedSuperclass
@ToString(of = {"id"})
@EqualsAndHashCode(of = {"internalId", "id"})
public abstract class AbstractEntity
{
    public static String DEFAULT_DELETED_VALUE = "0";

    @Transient
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

    public void setId(String id)
    {
        this.id = id;
        this.internalId = id;
    }

    private Date createTime;

    /**
     * defines record deleted or not if value is zero then record is not deleted all other values denotes deleted record.
     */
    private String deleted = DEFAULT_DELETED_VALUE;

    @Transient
    public boolean isRecordDeleted()
    {
        return !DEFAULT_DELETED_VALUE.equals(getDeleted());
    }
}
