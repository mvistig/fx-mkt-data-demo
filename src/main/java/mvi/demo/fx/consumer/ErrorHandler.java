package mvi.demo.fx.consumer;


public class ErrorHandler {
    public void handleException(String message, Exception e){
        //nothing to do, just for show
        System.out.println(e.getMessage());
    }
}
