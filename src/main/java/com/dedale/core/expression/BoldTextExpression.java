package com.dedale.core.expression;

public class BoldTextExpression extends TextExpression {

    public static final BoldTextExpression EMPTY = new BoldTextExpression(null);

    private Expression text;

    private BoldTextExpression(Expression text) {
        super("*");
        this.text = text;
    }

    @Override
    public TextExpression evaluate() {
        ValuedExpression<String> evaluate = (ValuedExpression<String>) text.evaluate();
        return new TextExpression(value() + evaluate.value() + value());
    }

    @Override
    protected ExpressionVisitor configure(ExpressionVisitor dispatcher) {
        return super.configure(dispatcher)
                .when(TextExpression.class, this::wrapText);
    }

    TextExpression wrapText(Expression text) {
        Expression effectiveText = this.text == null ? text : (TextExpression) this.text.then(text);
        return new BoldTextExpression(effectiveText);
    }

}
