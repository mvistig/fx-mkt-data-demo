package mvi.demo.fx.consumer.pricing;

import mvi.demo.fx.model.PriceDataDto;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class FxFeesStrategyTest {

    @Test
    void applyFees_shouldWorkWithNullBid() {
        final var actual = new FxFeesStrategy(BigDecimal.ONE).applyFees(PriceDataDto.builder().ask(BigDecimal.TEN).build());
        assertEquals(BigDecimal.valueOf(11), actual.getAsk());
        assertNull(actual.getBid());
    }

    @Test
    void applyFees_shouldWorkWithNullAsk() {
        final var actual = new FxFeesStrategy(BigDecimal.ONE).applyFees(PriceDataDto.builder().bid(BigDecimal.TEN).build());
        assertEquals(BigDecimal.valueOf(9), actual.getBid());
        assertNull(actual.getAsk());
    }
}