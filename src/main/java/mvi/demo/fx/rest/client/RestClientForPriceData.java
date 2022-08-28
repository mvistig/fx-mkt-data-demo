package mvi.demo.fx.rest.client;

import mvi.demo.fx.rest.model.PriceDataRequest;

public class RestClientForPriceData {
    public void postPriceData(PriceDataRequest request){
        System.out.println("Sending POST" + request);
    }
}
