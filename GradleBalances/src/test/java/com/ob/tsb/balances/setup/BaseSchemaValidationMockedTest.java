package com.ob.tsb.balances.setup;


import com.ob.tsb.balances.model.constants.Currency;
import com.ob.tsb.balances.model.ob.OBReadBalance;
import com.ob.tsb.balances.service.impl.ValidationServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.Errors;


import java.util.List;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(initializers = ConfigDataApplicationContextInitializer.class)
@TestPropertySource(locations = "classpath:application-local.yml")
public class BaseSchemaValidationMockedTest {


    @InjectMocks
    protected ValidationServiceImpl validationService;
    protected  Errors errors;
    protected List<OBReadBalance.Balance> balancesList;
    protected List<OBReadBalance.Balance> balancesNullInvalidBlankAccountId;
    protected List<OBReadBalance.Balance> balancesInvalidSizeAccountId;
    protected List<OBReadBalance.Balance> balancesNullInvalidBlankCurrency;
    protected List<OBReadBalance.Balance> balancesInvalidCurrency;
    protected List<OBReadBalance.Balance> balancesInvalidNotSpecificFormatCurrency;

    protected List<OBReadBalance.Balance> balancesInvalidAmount;
    protected List<OBReadBalance.Balance> balancesNullInvalidBlankAmount;
    protected List<OBReadBalance.Balance> balancesNullInvalidBlankCreditDebitIndicator;


    @BeforeEach
    public void setUp() {

        balancesNullInvalidBlankAccountId=List.of(new OBReadBalance.Balance("",
                new OBReadBalance.Amount("123.45","GBP"),
                "CREDIT", "Openingbalance", "2024-01-15T13:40:25Z",
                List.of(new OBReadBalance.CreditLine(true, (new OBReadBalance.Amount("5000.00","GBP")),"Available"))));

        balancesInvalidSizeAccountId=List.of(new OBReadBalance.Balance("15869758469826587954875698578DFRU785478",
                new OBReadBalance.Amount("123.45","GBP"),
                "CREDIT", "Openingbalance", "2024-01-15T13:40:25Z",
                List.of(new OBReadBalance.CreditLine(true, (new OBReadBalance.Amount("5000.00","GBP")),"Available"))));


        balancesList= List.of(new OBReadBalance.Balance("1221",
                new OBReadBalance.Amount("123.45","GBP"),
                "CREDIT", "Openingbalance", "2024-01-15T13:40:25Z",
                List.of(new OBReadBalance.CreditLine(true, (new OBReadBalance.Amount("5000.00","GBP")),"Available"))));

        balancesInvalidCurrency=List.of(new OBReadBalance.Balance("1221",
                        new OBReadBalance.Amount("123.45","INR"),
                        "CREDIT", "Openingbalance", "2024-01-15T13:40:25Z",
                        List.of(new OBReadBalance.CreditLine(true, (new OBReadBalance.Amount("5000.00","GBP")),"Available"))));

        balancesNullInvalidBlankCurrency= List.of(new OBReadBalance.Balance("1221",
                new OBReadBalance.Amount("123.45",""),
                "CREDIT", "Openingbalance", "2024-01-15T13:40:25Z",
                List.of(new OBReadBalance.CreditLine(true, (new OBReadBalance.Amount("5000.00","GBP")),"Available"))));
        balancesInvalidNotSpecificFormatCurrency=List.of(new OBReadBalance.Balance("1221",
                new OBReadBalance.Amount("123.45","INRS"),
                "CREDIT", "Openingbalance", "2024-01-15T13:40:25Z",
                List.of(new OBReadBalance.CreditLine(true, (new OBReadBalance.Amount("5000.00","GBP")),"Available"))));
        balancesInvalidAmount=List.of(new OBReadBalance.Balance("1221",
                new OBReadBalance.Amount("123.45Q","GBP"),
                "CREDIT", "Openingbalance", "2024-01-15T13:40:25Z",
                List.of(new OBReadBalance.CreditLine(true, (new OBReadBalance.Amount("5000.00","GBP")),"Available"))));

        balancesNullInvalidBlankAmount=List.of(new OBReadBalance.Balance("1221",
                new OBReadBalance.Amount("","GBP"),
                "CREDIT", "Openingbalance", "2024-01-15T13:40:25Z",
                List.of(new OBReadBalance.CreditLine(true, (new OBReadBalance.Amount("5000.00","GBP")),"Available"))));

        balancesNullInvalidBlankCreditDebitIndicator=List.of(new OBReadBalance.Balance("1221",
                new OBReadBalance.Amount("12.55","GBP"),
                "ABC", "Openingbalance", "2024-01-15T13:40:25Z",
                List.of(new OBReadBalance.CreditLine(true, (new OBReadBalance.Amount("5000.00","GBP")),"Available"))));


    }

    @AfterEach
    public void free() {


    }


}
