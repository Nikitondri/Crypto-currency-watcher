package com.idf.crypto_currency_watcher.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CryptoDTO {

    private Integer id;

    private String symbol;

    @NotBlank
    private Double cost;

}
