package mvi.demo.fx.consumer.pricing;

import lombok.RequiredArgsConstructor;
import mvi.demo.fx.model.PriceDataDto;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class FxFeesStrategy {
    //This should come from some config (DB, properties file etc)
    private final BigDecimal fee;

    public PriceDataDto applyFees(PriceDataDto dto){
        //this code assumes order book lines can be one sided (bid or ask could be null)
        if (dto.getBid() != null){
            dto.setBid(dto.getBid().subtract(fee));
        }
        if (dto.getAsk() != null){
            dto.setAsk(dto.getAsk().add(fee));
        }
        return dto;
    }
}
