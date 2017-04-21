package com.dedale.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class DiceResourceTest extends JerseyTest {
    
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    
    @Mock 
    private ResourceValidator validator;
    
    @Override
    protected Application configure() {
        return new ResourceConfig(DiceResource.class)
                .register(new AbstractBinder() {
            @Override
            protected void configure() {
                when(validator.validate(anyString())).thenReturn(true);
                bind(validator).to(ResourceValidator.class);
            }
        });
    }
    
    
    @Test
    public void should_return_200_for_well_formed_dice_query() throws Exception {
        // Given
        String wellFormedQuery = "wellFormedQuery";
        
        // When
        Response responseMsg = target("dices").request().post(Entity.text(wellFormedQuery));
        
        // Then
        assertThat(responseMsg.getStatus()).isEqualTo(200);
    }
    
    @Test
    public void should_return_appropriate_result_for_well_formed_dice_query() throws Exception {
        // Given
        String wellFormedQuery = "wellFormedQuery";
        
        // When
        Response responseMsg = target("dices").request().post(Entity.text(wellFormedQuery));
        
        // Then
        assertThat(responseMsg.readEntity(String.class)).isEqualTo("wellFormedQuery");
    }
    
    @Test
    public void should_return_400_for_malformed_dice_query() throws Exception {
        // Given
        String malFormedQuery = "malFormedQuery";
        when(validator.validate(ArgumentMatchers.eq(malFormedQuery))).thenReturn(false);
        
        // When
        Response responseMsg = target("dices").request().post(Entity.text(malFormedQuery));
        
        // Then
        assertThat(responseMsg.getStatus()).isEqualTo(400);
    }

}
