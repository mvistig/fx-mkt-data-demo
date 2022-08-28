package mvi.demo.fx.listener;

import mvi.demo.fx.consumer.ErrorHandler;
import mvi.demo.fx.consumer.PriceDataConsumer;
import mvi.demo.fx.consumer.pricing.FxFeesStrategy;
import mvi.demo.fx.mapper.PriceDataDtoMapper;
import mvi.demo.fx.mapper.PriceDataRequestMapperImpl;
import mvi.demo.fx.rest.client.RestClientForPriceData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EndToEndPriceDataListenerTest {

    private static final List<String> messages = List.of("106, EUR/USD, 1.1000,1.2000,01-06-2020 12:01:01:001",
           "107, EUR/JPY, 119.60,119.90,01-06-2020 12:01:02:002",
            "108, GBP/USD, 1.2500,1.2560,01-06-2020 12:01:02:002",
            "109, GBP/USD, 1.2499,1.2561,01-06-2020 12:01:02:100",
            "voluntary erroneous message",
            "110, EUR/JPY, 119.61,119.91,01-06-2020 12:01:02:110",
            "112, EUR/CHF, zzzzz,119.91,01-06-2020 12:01:02:110");

    @Test
    @DisplayName("End to End test that prints the messages as received by the REST Client. Fees = 0.1")
    void test_onMessage() {
        PriceDataListener priceDataListener = new PriceDataListener(new PriceDataDtoMapper(),
                new PriceDataConsumer(
                        new FxFeesStrategy(BigDecimal.valueOf(0.1)),
                        new PriceDataRequestMapperImpl(),
                        new RestClientForPriceData()),
                new ErrorHandler());

        messages.forEach(priceDataListener::onMessage);

    }
}