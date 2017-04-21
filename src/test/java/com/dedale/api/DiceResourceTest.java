package com.dedale.api;

import static org.assertj.core.api.Assertions.assertThat;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import com.dedale.calculator.StringCalculator;
import com.dedale.calculator.StringCalculatorInputValidator;

public class DiceResourceTest extends JerseyTest {
    
    @Override
    protected Application configure() {
        return new ResourceConfig(DiceResource.class).register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(StringCalculator.class).to(StringCalculator.class);
                bind(StringCalculatorInputValidator.class).to(StringCalculatorInputValidator.class);
            }
        });
    }
    
    @Test
    public void should_return_200_for_well_formed_dice_query() throws Exception {
        // Given
        String wellFormedQuery = "1+1";
        
        // When
        Response responseMsg = target("dices").request().post(Entity.text(wellFormedQuery));
        
        // Then
        assertThat(responseMsg.getStatus()).isEqualTo(200);
    }
    
    @Test
    public void should_return_appropriate_result_for_well_formed_dice_query() throws Exception {
        // Given
        String wellFormedQuery = "1+1";
        
        // When
        Response responseMsg = target("dices").request().post(Entity.text(wellFormedQuery));
        
        // Then
        assertThat(responseMsg.readEntity(String.class)).isEqualTo("2");
    }
    
    @Test
    public void should_return_400_for_malformed_dice_query() throws Exception {
        // Given
        String malFormedQuery = "malFormedQuery";
        
        // When
        Response responseMsg = target("dices").request().post(Entity.text(malFormedQuery));
        
        // Then
        assertThat(responseMsg.getStatus()).isEqualTo(400);
    }
    
}
