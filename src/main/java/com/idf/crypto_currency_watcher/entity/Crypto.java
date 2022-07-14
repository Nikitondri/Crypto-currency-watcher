package com.idf.crypto_currency_watcher.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "crypto")
public class Crypto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "cost")
    private Double cost;

    @OneToMany(mappedBy = "crypto", cascade = CascadeType.ALL)
    private Set<UserCrypto> userCryptoSet;


    public Crypto() {
    }

    public Crypto(Integer id, String symbol, Double cost) {
        this.id = id;
        this.symbol = symbol;
        this.cost = cost;
    }

    public Crypto(Integer id, String symbol, Double cost, Set<UserCrypto> userCryptoSet) {
        this.id = id;
        this.symbol = symbol;
        this.cost = cost;
        this.userCryptoSet = userCryptoSet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Set<UserCrypto> getUserCryptoSet() {
        return userCryptoSet;
    }

    public void setUserCryptoSet(Set<UserCrypto> userCryptoSet) {
        this.userCryptoSet = userCryptoSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Crypto crypto = (Crypto) o;
        return Objects.equals(id, crypto.id) && Objects.equals(symbol, crypto.symbol) && Objects.equals(cost, crypto.cost) && Objects.equals(userCryptoSet, crypto.userCryptoSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, symbol, cost, userCryptoSet);
    }

    @Override
    public String toString() {
        return "Crypto{" +
                "id=" + id +
                ", symbol='" + symbol + '\'' +
                ", cost=" + cost +
                ", userCryptoSet=" + userCryptoSet +
                '}';
    }
}
