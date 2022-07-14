# **CryptoСurrency watcher**

_Api for monitoring cryptocurrencies_

## Features
____
- Viewing the list of available cryptocurrencies
- Viewing the current price for the specified cryptocurrency
- Logging messages about changes in the value of cryptocurrencies

## Endpoints
____
- **GET** ```/api/cryptos``` - viewing the list of available cryptocurrencies
- **GET** ```/api/cryptos/cost/{symbol}``` - viewing the current price for the specified cryptocurrency
- **POST** ```/api/notify``` - user subscription to cryptocurrency


## Database scheme
____
![Image text](https://github.com/Nikitondri/Crypto-urrency-watcher/blob/main/db.png)
