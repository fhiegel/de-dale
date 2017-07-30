package com.dedale.api;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dedale.calculator.StringCalculator;
import com.dedale.calculator.StringCalculatorInputValidator;

@Path("dices")
public class DiceResource {

    @Inject
    private StringCalculator calculator;

    @Inject
    private StringCalculatorInputValidator validator;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String defaultRoll(String diceSentence) {
        if (!validator.validate(diceSentence)) {
            throw new WebApplicationException("Cannot parse dice sentence. sentence:" + diceSentence,
                    Response.Status.BAD_REQUEST);
        }
        return calculator.calculate(diceSentence).toString();
    }

}
