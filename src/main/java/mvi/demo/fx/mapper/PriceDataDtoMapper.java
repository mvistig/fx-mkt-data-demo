package mvi.demo.fx.mapper;

import mvi.demo.fx.exception.InputValidationException;
import mvi.demo.fx.model.PriceDataDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PriceDataDtoMapper {

    private static final int NUMBER_OF_FIELDS = 5;
    private static final int I_ID = 0;
    private static final int I_INSTRUMENT_NAME = 1;
    private static final int I_BID = 2;
    private static final int I_ASK = 3;
    private static final int I_TIMESTAMP = 4;
    private final Pattern csvPattern = Pattern.compile(",");

    //In the absence of a framework that provides an error handler, I prefer to use checked exceptions when
    // handling the message in the onMessage() thread. Otherwise, runtime exceptions make for prettier code.
    public PriceDataDto fromCsv(String message) throws InputValidationException {
        if (message == null || message.isBlank()) {
            throw new InputValidationException("Couldn't parse correctly received message, message is empty");
        }
        final var tokens = csvPattern.splitAsStream(message).map(String::trim).collect(Collectors.toList());
        if (!validate(tokens)) {
            throw new InputValidationException("Couldn't parse correctly received message: " + message);
        }
        return PriceDataDto.builder()
                .id(tokens.get(I_ID))
                .instrumentName(tokens.get(I_INSTRUMENT_NAME))
                .bid(toBigDecimal(tokens.get(I_BID)))
                .ask(toBigDecimal(tokens.get(I_ASK)))
                .timeStamp(tokens.get(I_TIMESTAMP))
                .build();
    }

    private boolean validate(List<String> tokens) {
        return tokens.size() >= NUMBER_OF_FIELDS;
    }

    private BigDecimal toBigDecimal(String s) throws InputValidationException {
        if (s != null && !s.isBlank()) {
            try {
                return new BigDecimal(s);
            } catch (NumberFormatException e) {
                //didn't use a logger for lack of time
                System.out.println("Couldn't parse to BigDecimal, not a number " + e.getMessage());
                throw new InputValidationException("Couldn't parse " + s + " as number");
            }
        } else {
            return null;
        }
    }

}
