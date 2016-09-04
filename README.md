# Super simple stock

My Java implementation of simple stock market.

## Basic outline of the project
##### Entities (data models)
- `Exchange`
- `Stock`
- `Trade`

##### Services (interaction with data)
- `ExchangeService`
- `StockService`
- `TradeService`

##### Calculators (calculations based on stocks and trades)
- `ExchangeCalculator`
- `StockCalculator`

## Usage
There's no CLI or anything, just tests, so any additional testing requires editing the source code. The main entry point is through the `App` object and `AppIntegrationTest` suite.

##### To run the tests:
- `mvn test` - run unit tests
- `mvn verify` - run unit + integration tests