package com.dedale.hermes;

import static com.dedale.markdown.MarkdownTags.MARKDOWN_BOLD;
import static com.dedale.markdown.MarkdownTags.MARKDOWN_CODE;

import javax.inject.Inject;

import com.dedale.calculator.DiceRollsOperations;
import com.dedale.calculator.StringCalculator;
import com.dedale.calculator.StringCalculatorInputValidator;
import com.dedale.hermes.HermesDiceResult.HermesResponseCode;

public class HermesDiceQueryHandler {

    private StringCalculator calculator;
    private StringCalculatorInputValidator validator;

    @Inject
    public HermesDiceQueryHandler(StringCalculator calculator) {
        this.calculator = calculator.addOperation(DiceRollsOperations.SUM_DICES);
        this.validator = new StringCalculatorInputValidator(this.calculator);
    }

    public HermesDiceResult handle(HermesCommand message) {
        if (!validator.validate(message.command)) {
            String responseText = "Impossible de lancer la définition de dé suivante : " + MARKDOWN_CODE
                    + message.command + MARKDOWN_CODE;
            return new HermesDiceResult(HermesResponseCode.ERROR, responseText, message.author);
        }
        String diceResult = calculator.calculate(message.command).toString();
        String responseText = message.command + "= " + MARKDOWN_BOLD + diceResult + MARKDOWN_BOLD;
        return new HermesDiceResult(HermesResponseCode.VALID, responseText, message.author);
    }

}
