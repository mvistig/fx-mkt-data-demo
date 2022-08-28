package mvi.demo.fx.consumer;

import lombok.RequiredArgsConstructor;
import mvi.demo.fx.consumer.pricing.FxFeesStrategy;
import mvi.demo.fx.exception.PriceDataRestClientException;
import mvi.demo.fx.model.PriceDataDto;
import mvi.demo.fx.mapper.PriceDataRequestMapper;
import mvi.demo.fx.rest.client.RestClientForPriceData;

@RequiredArgsConstructor
public class PriceDataConsumer {

    private final FxFeesStrategy feesStrategy;
    private final PriceDataRequestMapper priceDataRequestMapper;
    private final RestClientForPriceData restClientForPriceData;

    //I'm using checked exception to emphasize the lack of error handling related to the messaging framework and the lack of info about the exceptions of the rest client.
    // Usually it's better to use runtime exceptions
    public void consumePriceMessage(PriceDataDto dto) throws PriceDataRestClientException {
        restClientForPriceData.postPriceData(priceDataRequestMapper.fromDto(feesStrategy.applyFees(dto)));
    }
}
