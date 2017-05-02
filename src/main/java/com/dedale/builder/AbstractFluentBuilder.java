package com.dedale.builder;

import java.util.function.Consumer;

/**
 * @param <T>
 * @param <NB>
 *            nextBuilder
 * @param <CB>
 *            currentBuilder
 */
public abstract class AbstractFluentBuilder<T, NB, CB extends AbstractFluentBuilder<T, NB, CB>> {

    protected T bean;
    protected Consumer<? super T> beanConsumer;
    protected NB nextBuilder;

    @SuppressWarnings("unchecked")
    protected AbstractFluentBuilder(T bean, Consumer<? super T> beanConsumer, NB nextBuilder) {
        this.bean = bean;
        this.beanConsumer = beanConsumer;
        this.nextBuilder = nextBuilder != null ? nextBuilder : (NB) bean;
    }

    protected AbstractFluentBuilder(T bean) {
        this(bean, null, null);
    }

    // Build in methods

    protected void internalBuild() {
        consumeBean();
    }

    protected final void consumeBean() {
        if (beanConsumer == null) {
            return;
        }
        beanConsumer.accept(bean);
        beanConsumer = null; // Consume only once
    }

    protected NB internalEndBean() {
        internalBuild();
        return nextBuilder;
    }

    protected T internalBuildBean() {
        internalBuild();
        return bean;
    }
}