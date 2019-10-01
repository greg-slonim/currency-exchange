# converter

Notes
-----
* Requires Java 8 (not 11). There is some weirdness in dependencies that I did not have time to resolve.
* I used HTTP POST for this API because I felt it was cleaner to send payloads to the API, more extensible.

How to start the currency converter app
---------------------------------------

1. Run `mvn clean install` to build the currency exchange app
1. Start the app with `java -jar target/currency-converter-1.0-SNAPSHOT.jar server config.yml`

To hit and endpoint and get exchange rate and recommendation for USD, do
```
curl 'http://localhost:8080/exchange' -H 'content-type: application/json' -H 'accept: application/json' --data-binary '{"base": "USD", "symbol": "EUR"}'
```
To hit and endpoint and get exchange rate and recommendation for GBP, do
```
curl 'http://localhost:8080/exchange' -H 'content-type: application/json' -H 'accept: application/json' --data-binary '{"base": "GBP", "symbol": "EUR"}'
```
