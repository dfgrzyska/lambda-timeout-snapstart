package test.lambda.timeout.application;

import java.util.concurrent.TimeUnit;

public class SimpleService {
    public void handleGet(){

    }

    public void handlePost(){
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
