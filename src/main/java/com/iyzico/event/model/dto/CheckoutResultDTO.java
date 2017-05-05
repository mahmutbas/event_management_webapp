package com.iyzico.event.model.dto;

import com.iyzico.event.model.dto.enums.Result;
import lombok.Data;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by TCMBAS on 04/05/2017.
 */
@Data
public class CheckoutResultDTO
{
    private Result result;
    private String resultDescription;

    public CheckoutResultDTO(Result result, String resultDescription)
    {
        this.result = checkNotNull(result);
        this.resultDescription = resultDescription;
    }
}
