package mvi.demo.fx.mapper;

import mvi.demo.fx.model.PriceDataDto;
import mvi.demo.fx.rest.model.PriceDataRequest;
import org.mapstruct.Mapper;

@Mapper()
public interface PriceDataRequestMapper {
    PriceDataRequest fromDto(PriceDataDto dto);
}
