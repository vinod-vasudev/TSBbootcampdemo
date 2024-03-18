package com.ob.tsb.balances.service.impl;

import com.ob.tsb.balances.setup.BaseSchemaValidationMockedTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

 class ValidationServiceImplTest extends BaseSchemaValidationMockedTest {
    @Test
    @DisplayName("Success - Valid response API")
    void testValidResponse() {

        Errors error= validationService.validateObBalance(balancesList);

        assertNotNull(error.getAllErrors());
        assertFalse(error.hasErrors());



    }

    @Test
    @DisplayName("Success - Account id should not blank or null")
    void testBalancesNullInvalidBlankAccountId(){
        Errors errors=validationService.validateObBalance(balancesNullInvalidBlankAccountId);
        assertNotNull(errors.getAllErrors());
        assertEquals(2,errors.getErrorCount());
        assertTrue(errors.hasErrors());
    }


    @Test
    @DisplayName("Success - Account id should be Min 1 and max 40")
    void testBalancesInvalidSizeAccountId(){
        Errors errors=validationService.validateObBalance(balancesNullInvalidBlankAccountId);
        assertNotNull(errors.getAllErrors());
        assertEquals(2,errors.getErrorCount());
        assertTrue(errors.hasErrors());
    }

    @Test
    @DisplayName("Success - Currency should not blank or Null and Currency not is specified")
    void testNullInvalidBlankCurrency(){
        Errors errors=validationService.validateObBalance(balancesNullInvalidBlankCurrency);

        assertNotNull(errors.getAllErrors());
        assertEquals(2,errors.getErrorCount());
        assertTrue(errors.hasErrors());
    }

    @Test
    @DisplayName("Success - Currency not is specified")
    void testInvalidNotSpecificFormatCurrency(){
        Errors errors=validationService.validateObBalance(balancesInvalidNotSpecificFormatCurrency);

        assertNotNull(errors.getAllErrors());
        assertEquals(1,errors.getErrorCount());
        assertTrue(errors.hasErrors());
    }
    @Test
    @DisplayName("Success - Amount not in specified format")
    void testBalancesInvalidAmount(){
        Errors errors=validationService.validateObBalance(balancesInvalidAmount);
        assertNotNull(errors.getAllErrors());
        assertEquals("Amount not in specified format",
                Objects.requireNonNull(errors.getFieldError()).getDefaultMessage());

        assertTrue(errors.hasErrors());
    }

    @Test
    @DisplayName("Success - Amount should not blank or null")
    void testBalancesNullInvalidBlankAmount(){
        Errors errors=validationService.validateObBalance(balancesNullInvalidBlankAmount);

        assertNotNull(errors.getAllErrors());
        assertEquals(2,errors.getErrorCount());
        assertTrue(errors.hasErrors());
    }


}
