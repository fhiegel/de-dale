package com.dedale.hermes;

import com.dedale.calculator.StringCalculator;

public class HermesEngine {

    public HermesResponse dispatch(HermesTokenizer message) {
        HermesResponse defaultResponse = new HermesResponse();
        
        HermesDiceQueryHandler defaultHandler = new HermesDiceQueryHandler(
                StringCalculator.arithmeticOperations()
                );
        HermesDiceResult item = defaultHandler.handle(message.nextCommand());
        defaultResponse.addResponse(item);
        return defaultResponse;
    }

}
