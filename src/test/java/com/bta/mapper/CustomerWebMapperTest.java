package com.bta.mapper;

import com.bta.dto.CustomerDTO;
import com.bta.model.Customer;
import org.checkerframework.checker.index.qual.PolyUpperBound;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

import static com.bta.mapper.CustomerWebMapperHelper.getCustomer;
import static com.bta.mapper.CustomerWebMapperHelper.getCustomerDto;
import static org.junit.jupiter.api.Assertions.*;

class CustomerWebMapperTest {

    private final CustomerWebMapper instanceUnderTest = new CustomerWebMapper();

    @Test
    public void testMapNullEntityToDto() {
        final Customer entity = null;

        final CustomerDTO actualResult = instanceUnderTest.toDTO(entity);
        assertEquals(null, actualResult);
    }

    @Test
    public void testMapNotNullEntityToDto() {
        getCustomer();

        final CustomerDTO actualResult = instanceUnderTest.toDTO(getCustomer());

        assertEquals("+3727778899", actualResult.getTelephone());
        assertEquals("test@test.com", actualResult.getEmail());
        assertEquals("FirstName", actualResult.getFirstName());
        assertEquals("LastName", actualResult.getLastName());
        assertEquals((BigInteger.valueOf(300l)), actualResult.getRegistrationCode());
    }

    @Test
    public void testMapNullColectionToDtos() {

        final List<Customer> customer = null;
        final List<CustomerDTO> actualResult = instanceUnderTest.toDtos(customer);
        assertEquals(null, actualResult);
    }

    @Test
    public void testMapColectionEntityToDtos() {

        final List<Customer> customer = Collections.singletonList(getCustomer());

        final List<CustomerDTO> actualResult = instanceUnderTest.toDtos(customer);

        assertEquals(1, actualResult.size());
    }

    @Test
    public void testMapNullDtoToEntity() {

        final CustomerDTO dto = null;

        final Customer actualResult = instanceUnderTest.toEntity(dto);

        assertEquals(null, actualResult);
    }

    @Test
    public void testMapNotNullDtoToEntity() {

        getCustomerDto();

        final Customer actualResult = instanceUnderTest.toEntity(getCustomerDto());

        assertEquals("+3727778899", actualResult.getTelephone());
        assertEquals("test@test.com", actualResult.getEmail());
        assertEquals("FirstName", actualResult.getFirstName());
        assertEquals("LastName", actualResult.getLastName());
        assertEquals((BigInteger.valueOf(300l)), actualResult.getRegistrationCode());
    }


}

