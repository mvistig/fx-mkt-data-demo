package mvi.demo.fx.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class PriceDataDto {
    private String id;
    private String instrumentName;
    private BigDecimal bid;
    private BigDecimal ask;
    private String timeStamp;
}
