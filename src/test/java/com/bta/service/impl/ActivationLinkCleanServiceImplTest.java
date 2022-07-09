package com.bta.service.impl;
import com.bta.model.ActivationLink;
import com.bta.repository.ActivationLinkRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {ActivationLinkCleanServiceImpl.class})
@ExtendWith(MockitoExtension.class)
class ActivationLinkCleanServiceImplTest {
    @Mock
    private ActivationLinkRepository activationLinkRepository;

    @InjectMocks
    private ActivationLinkCleanServiceImpl instanceUnderTest;


    @Test
    public void testIfNoExpiredActivationLinks() {

        when(activationLinkRepository.findByCreatedBefore(any())).thenReturn(Collections.emptyList());

        instanceUnderTest.clean();

        verify(activationLinkRepository, never()).deleteAll(any());
    }

    @Test
    public void testIfExpiredActivationLinks() {
        when(activationLinkRepository.findByCreatedBefore(any()))
                .thenReturn(Collections.singletonList(ActivationLink.builder().build()));


        instanceUnderTest.clean();
        verify(activationLinkRepository, times(1)).deleteAll(any());
    }

    @Test
    public void testIfExpiredActivationLinksAreNull() {
        when(activationLinkRepository.findByCreatedBefore(any())).thenReturn(null);
        instanceUnderTest.clean();


        verify(activationLinkRepository, times(0)).deleteAll(any());
    }


}