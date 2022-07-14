package com.idf.crypto_currency_watcher.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCryptoKey implements Serializable {

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "crypto_id")
    private Integer cryptoId;


}
