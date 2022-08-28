package mvi.demo.fx.mapper;

import lombok.SneakyThrows;
import mvi.demo.fx.exception.InputValidationException;
import mvi.demo.fx.model.PriceDataDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PriceDataDtoMapperTest {

    private final PriceDataDtoMapper mapper = new PriceDataDtoMapper();

    @Test
    @DisplayName("Calling fromCsv should throw exception when message is null")
    void test_fromCsv_messageNullThrowsException() {
        assertThrows(InputValidationException.class, () -> mapper.fromCsv(null));
    }

    @Test
    @DisplayName("Calling fromCsv should throw exception when message is blank")
    void test_fromCsv_messageBlankTrowsException() {
        assertThrows(InputValidationException.class, () -> mapper.fromCsv("   "));
    }

    @Test
    @DisplayName("Calling fromCsv should throw exception when message is too short")
    void test_fromCsv_messageShortTrowsException() {
        assertThrows(InputValidationException.class, () -> mapper.fromCsv("106, EUR/USD, 1.1000,1.2000"));
    }

    @Test
    @DisplayName("Calling fromCsv should throw exception when bid is not a number")
    void test_fromCsv_bidNotANumberTrowsException() {
        assertThrows(InputValidationException.class,
                () -> mapper.fromCsv("106, EUR/USD, eza.1000,1.2000,01-06-2020 12:01:01:001"));
    }

    @Test
    @DisplayName("Calling fromCsv should throw exception when ask is not a number")
    void test_fromCsv_askNotANumberTrowsException() {
        assertThrows(InputValidationException.class,
                () -> mapper.fromCsv("106, EUR/USD, 1.1000,1.aa2000,01-06-2020 12:01:01:001"));
    }

    @Test
    @DisplayName("Calling fromCsv should map all fields")
    @SneakyThrows
    void test_fromCsv_mapsAllFields() {
        final var actual = mapper.fromCsv("108, GBP/USD, 1.2500,1.2560,01-06-2020 12:01:02:002");
        assertEquals("108", actual.getId());
        assertEquals("GBP/USD", actual.getInstrumentName());
        assertEquals(BigDecimal.valueOf(12500L,  4), actual.getBid());
        assertEquals(BigDecimal.valueOf(12560L, 4), actual.getAsk());
        assertEquals("01-06-2020 12:01:02:002", actual.getTimeStamp());
    }


}