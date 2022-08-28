package mvi.demo.fx.listener;

import lombok.RequiredArgsConstructor;
import mvi.demo.fx.consumer.ErrorHandler;
import mvi.demo.fx.consumer.PriceDataConsumer;
import mvi.demo.fx.exception.InputValidationException;
import mvi.demo.fx.exception.PriceDataRestClientException;
import mvi.demo.fx.mapper.PriceDataDtoMapper;

@RequiredArgsConstructor
public class PriceDataListener {
    private final PriceDataDtoMapper priceDataDtoMapper;
    private final PriceDataConsumer priceDataConsumer;
    private final ErrorHandler errorHandler;

    public void onMessage(String message){
        try {
            priceDataConsumer.consumePriceMessage(priceDataDtoMapper.fromCsv(message));
        } catch (InputValidationException e) {
            handleInputError(message, e);
        }  catch (PriceDataRestClientException e) {
            handleRestError(message, e);
        }

    }

    private void handleInputError(String message, InputValidationException e) {
        errorHandler.handleException(message, e);
        //TODO here: implement the error handling according to business logic: push to DLQ, retry, ignore, notify monitoring tools etc etc
    }

    private void handleRestError(String message, PriceDataRestClientException e) {
        errorHandler.handleException(message, e);
    }
}
