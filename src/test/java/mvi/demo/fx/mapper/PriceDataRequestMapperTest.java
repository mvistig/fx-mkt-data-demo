package mvi.demo.fx.mapper;

import mvi.demo.fx.model.PriceDataDto;
import mvi.demo.fx.rest.model.PriceDataRequest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PriceDataRequestMapperTest {

    @Test
    void fromDto_shouldMapAllFields() {
        PriceDataDto dto = PriceDataDto.builder()
                .id("id")
                .instrumentName("name")
                .bid(BigDecimal.TEN)
                .ask(new BigDecimal("10.20"))
                .timeStamp("timestamp")
                .build();
        PriceDataRequest actual = new PriceDataRequestMapperImpl().fromDto(dto);
        assertEquals(dto.getId(), actual.getId());
        assertEquals(dto.getBid().toString(), actual.getBid());
        assertEquals(dto.getAsk().toString(), actual.getAsk());
        assertEquals(dto.getTimeStamp(), actual.getTimeStamp());
    }
}