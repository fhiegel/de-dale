package com.dedale.utils.jersey;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.DeploymentContext;
import org.glassfish.jersey.test.TestProperties;
import org.glassfish.jersey.test.inmemory.InMemoryTestContainerFactory;
import org.glassfish.jersey.test.spi.TestContainer;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

import com.dedale.DeDaleResourceConfig;
import com.dedale.utils.jersey.configuration.BinderConfiguration;
import com.dedale.utils.jersey.configuration.ConfigurationBuilder;

public class ApplicationRule implements MethodRule, ApplicationCycleManager {

    private InMemoryTestContainerFactory testContainerFactory = new InMemoryTestContainerFactory();
    private ConfigurationBuilder<ResourceConfig> configurationBuilder;
    private TestCycleWrapper testCycleWrapper;

    public static ApplicationRule dedale() {
        return app(new DeDaleResourceConfig());
    }

    public static ApplicationRule app(ResourceConfig configuration) {
        return new ApplicationRule(configuration);
    }

    private ApplicationRule(ResourceConfig configuration) {
        this.configurationBuilder = new ConfigurationBuilder<>(configuration);
    }

    @Override
    public Statement apply(Statement base, FrameworkMethod method, Object target) {
        InjectionStatement injectionStatement = new InjectionStatement(base, this::getServiceLocator, target);
        return new ApplicationLifeCycleStatment(injectionStatement, this);
    }

    public void prepareTestCycle() {
        this.testCycleWrapper = createTestCycle();
    }

    private TestCycleWrapper createTestCycle() {
        DeploymentContext context = DeploymentContext.newInstance(configurationBuilder.build());
        TestContainer testContainer = testContainerFactory.create(getDefaultUri(), context);
        return new TestCycleWrapper(testContainer);
    }

    private static URI getDefaultUri() {
        return UriBuilder.fromUri("http://localhost/").port(TestProperties.DEFAULT_CONTAINER_PORT).build();
    }

    public void releaseTestCycle() {
        this.testCycleWrapper.release();
        this.testCycleWrapper = null;
    }

    //
    // Uses of Client
    //

    public final WebTarget target() {
        return client().target(getDefaultUri());
    }

    public final WebTarget target(final String path) {
        return target().path(path);
    }

    public final Client client() {
        return testCycleWrapper.getClient();
    }

    public final ServiceLocator getServiceLocator() {
        return testCycleWrapper.getServiceLocator();
    }

    //
    // Uses of configuration builder
    //

    public <B extends AbstractBinder> ApplicationRule addBinder(B binder) {
        configurationBuilder.addBinder(binder);
        return this;
    }

    public ApplicationRule configureBinding(BinderConfiguration configuration) {
        configurationBuilder.configureBinding(configuration);
        return this;
    }

}
