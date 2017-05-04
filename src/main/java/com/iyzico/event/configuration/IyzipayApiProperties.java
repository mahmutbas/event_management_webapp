package com.iyzico.event.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 */

@Data
@ConfigurationProperties(prefix = "iyzipayapi")
public class IyzipayApiProperties
{
    private String apiKey;
    private String apiSecret;
    private String baseUrl;
}
