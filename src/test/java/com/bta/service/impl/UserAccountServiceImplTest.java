package com.bta.service.impl;

import com.bta.model.ActivationLink;
import com.bta.model.UserAccount;
import com.bta.repository.ActivationLinkRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserAccountServiceImplTest {

    @InjectMocks
    private UserAccountServiceImpl instanceUnderTest = new UserAccountServiceImpl();

    @Mock
    private ActivationLinkRepository activationLinkRepository;

    @Test()
    public void testNullCode() {

        final String code = null;
        final RuntimeException actualResult = assertThrows(RuntimeException.class, () -> instanceUnderTest.activate(code));
        assertEquals("Activation code must not be null or empty!", actualResult.getMessage());
    }

    @Test
    public void testEmptyCode() {
        final String code = "";
        final RuntimeException actualResult = assertThrows(RuntimeException.class, () -> instanceUnderTest.activate(code));

        assertEquals("Activation code must not be null or empty!", actualResult.getMessage());
    }

    @Test
    public void testNotNullCode() {

        final String code = "Test_Code";
        ActivationLink activationLink = null;

        when(activationLinkRepository.findByCode(code))
                .thenReturn(activationLink);


        final RuntimeException actualResult = assertThrows(RuntimeException.class, () -> instanceUnderTest.activate(code));


        assertEquals("Invalid code on activation link: Test_Code", actualResult.getMessage());
    }

    @Test
    public void testNotNullValidCode() {

        final String code = "Test_Code";
        final ActivationLink activationLink = ActivationLink.builder()
                .code(code)
                .created(ZonedDateTime.now().minusDays(1))
                .build();

        when(activationLinkRepository.findByCode(code))
                .thenReturn(activationLink);

        final RuntimeException actualResult = assertThrows(RuntimeException.class, () -> instanceUnderTest.activate(code));

        assertEquals("Invalid code on activation link Test_Code already expired", actualResult.getMessage());
    }

    @Test
    public void testNotNullNotExpired() {
        final String code = "Test_Code";
        final UserAccount userAccount = UserAccount.builder()
                .active(false)
                .build();

        final ActivationLink activationLink = ActivationLink.builder()
                .code(code)
                .created(ZonedDateTime.now())
                .userAccount(userAccount)
                .build();

        when(activationLinkRepository.findByCode(code))
                .thenReturn(activationLink);

        instanceUnderTest.activate(code);

        assertTrue(userAccount.isActive());
        assertEquals(true, activationLink.getUserAccount().isActive());

        verify(activationLinkRepository).delete(any());
        verify(activationLinkRepository, times(1)).delete(activationLink);
    }


}