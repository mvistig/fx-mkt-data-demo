package mvi.demo.fx.rest.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@Data
public class PriceDataRequest {
    private String id;
    private String instrumentName;
    private String bid;
    private String ask;
    private String timeStamp;
}
