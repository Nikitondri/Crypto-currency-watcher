package com.idf.crypto_currency_watcher.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "crypto_user")
public class UserCrypto {

    @EmbeddedId
    private UserCryptoKey userCryptoKey;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("cryptoId")
    @JoinColumn(name = "crypto_id")
    private Crypto crypto;

    @Column(name = "current_cost")
    private Double currentCost;

    public UserCrypto() {
    }

    public UserCrypto(UserCryptoKey userCryptoKey, User user, Crypto crypto, Double currentCost) {
        this.userCryptoKey = userCryptoKey;
        this.user = user;
        this.crypto = crypto;
        this.currentCost = currentCost;
    }

    public UserCryptoKey getUserCryptoKey() {
        return userCryptoKey;
    }

    public void setUserCryptoKey(UserCryptoKey userCryptoKey) {
        this.userCryptoKey = userCryptoKey;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Crypto getCrypto() {
        return crypto;
    }

    public void setCrypto(Crypto crypto) {
        this.crypto = crypto;
    }

    public Double getCurrentCost() {
        return currentCost;
    }

    public void setCurrentCost(Double currentCost) {
        this.currentCost = currentCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCrypto that = (UserCrypto) o;
        return Objects.equals(userCryptoKey, that.userCryptoKey) && Objects.equals(user, that.user) && Objects.equals(crypto, that.crypto) && Objects.equals(currentCost, that.currentCost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userCryptoKey, user, crypto, currentCost);
    }

    @Override
    public String toString() {
        return "UserCrypto{" +
                "userCryptoKey=" + userCryptoKey +
                ", user=" + user +
                ", crypto=" + crypto +
                ", currentCost=" + currentCost +
                '}';
    }
}
