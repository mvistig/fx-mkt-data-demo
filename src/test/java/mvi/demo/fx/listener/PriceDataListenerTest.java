package mvi.demo.fx.listener;

import lombok.SneakyThrows;
import mvi.demo.fx.consumer.ErrorHandler;
import mvi.demo.fx.consumer.PriceDataConsumer;
import mvi.demo.fx.exception.InputValidationException;
import mvi.demo.fx.exception.PriceDataRestClientException;
import mvi.demo.fx.mapper.PriceDataDtoMapper;
import mvi.demo.fx.model.PriceDataDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class PriceDataListenerTest {
    @Mock
    private PriceDataDtoMapper priceDataDtoMapper;
    @Mock
    private PriceDataConsumer priceDataConsumer;
    @Mock
    private ErrorHandler errorHandler;
    @InjectMocks
    private PriceDataListener listener;

    @Test
    @DisplayName("Calling onMessage with input validation exception will call errorHandler")
    @SneakyThrows
    void test_onMessage_inputExceptionCallsHandler() {
        Mockito.when(priceDataDtoMapper.fromCsv("test")).thenThrow(new InputValidationException("Test"));
        listener.onMessage("test");
        Mockito.verify(errorHandler).handleException(ArgumentMatchers.anyString(), any());
    }

    @Test
    @DisplayName("Calling onMessage with rest client exception will call errorHandler")
    @SneakyThrows
    void test_onMessage_restExceptionCallsHandler() {
        Mockito.when(priceDataDtoMapper.fromCsv("test")).thenReturn(PriceDataDto.builder().build());
        Mockito.doThrow(new PriceDataRestClientException("toto")).when(priceDataConsumer).consumePriceMessage(any());
        listener.onMessage("test");
        Mockito.verify(errorHandler).handleException(ArgumentMatchers.anyString(), any());
    }

    @Test
    @DisplayName("Calling onMessage with rest client exception will call errorHandler")
    @SneakyThrows
    void test_onMessage_callsConsumer() {
        PriceDataDto priceDataDto = PriceDataDto.builder().id("1").build();
        Mockito.when(priceDataDtoMapper.fromCsv("test")).thenReturn(priceDataDto);
        listener.onMessage("test");
        Mockito.verify(errorHandler, Mockito.times(0)).handleException(ArgumentMatchers.anyString(), any());
        Mockito.verify(priceDataConsumer).consumePriceMessage(priceDataDto);
    }

}