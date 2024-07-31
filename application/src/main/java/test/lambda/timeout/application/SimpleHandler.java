package test.lambda.timeout.application;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.crac.Core;
import org.crac.Resource;

public class SimpleHandler implements Resource, RequestHandler<Map<String, Object>, String> {
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private SimpleService simpleService;

    public SimpleHandler() {
        Core.getGlobalContext().register(this);
    }

    @Override
    public String handleRequest(Map<String, Object> input, Context context) {
        if(simpleService == null){
            setter();
        }
        logger.log(Level.INFO, "is simple service null? " + (simpleService == null));
        var type = input.get("httpMethod");
        if (type.equals("GET")) {
            simpleService.handleGet();
        } else {
            simpleService.handlePost();
        }
        return "OK";
    }

    private void setter(){
        this.simpleService = new SimpleService();
    }

    @Override
    public void beforeCheckpoint(org.crac.Context<? extends Resource> context)
            throws Exception {
        simpleService = new SimpleService();
        System.out.println("Before checkpoint");
    }

    @Override
    public void afterRestore(org.crac.Context<? extends Resource> context)
            throws Exception {
        System.out.println("After restore");
    }
}
