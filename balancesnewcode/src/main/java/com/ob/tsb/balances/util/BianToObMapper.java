package com.ob.tsb.balances.util;

import com.ob.tsb.balances.model.bian.BianReadBalance;
import com.ob.tsb.balances.model.constants.Currency;
import com.ob.tsb.balances.model.ob.OBReadBalance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.DateTimeException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Mapper
public interface BianToObMapper {
    BianToObMapper INSTANCE = Mappers.getMapper(BianToObMapper.class);

    @Mapping(source = "accountBalance.balanceAmount.amountValue.value", target = "Amount.Amount")
    @Mapping(source = "accountBalance.balanceAmount.amountCurrency", target = "Amount.Currency")
    @Mapping(source = "accountBalance.balanceIndicator", target = "CreditDebitIndicator")
    @Mapping(source = "accountBalance.balanceType", target = "Type")
    @Mapping(source = "accountBalance.balanceDate", target = "DateTime",  qualifiedByName = "mapFormatOffsetDatetime")
    @Mapping(source = "creditLine", target = "CreditLine", qualifiedByName = "mapCreditLine")
    @Mapping(source = "accountId", target = "AccountId")
    OBReadBalance.Balance accountBalance(BianReadBalance bianReadBalance);

    @Named("mapCreditLine")
    default List<OBReadBalance.CreditLine> mapCreditLine(List<BianReadBalance.CreditLine> bianCreditLine){
        List<OBReadBalance.CreditLine> obCreditLine = new ArrayList<>();
        for (BianReadBalance.CreditLine bCreditLine : bianCreditLine) {
            obCreditLine.add(new OBReadBalance.CreditLine(bCreditLine.Included(), new OBReadBalance.Amount(bCreditLine.Amount().AmountValue().value(), (bCreditLine.Amount().AmountCurrency())), bCreditLine.Type()));
        }
        return obCreditLine;
    }

    @Named("mapFormatOffsetDatetime")
    default String mapFormatOffsetDatetime(OffsetDateTime bianDateTime){
        try{
            return bianDateTime.format(DateTimeFormatter.ISO_DATE_TIME);
        }catch (DateTimeException e){

        }

        return null;
    }

  /* @AfterMapping
    default  void validateData(BianReadBalance bianReadBalance){
        List<String> validationResults = new ArrayList<>();
        validAmmount(bianReadBalance.accountBalance().balanceAmount().amountValue().value(), validationResults);
        validCurrency(bianReadBalance.accountBalance().balanceAmount().amountCurrency().currencycode(), validationResults);
    }

    default void validAmmount(String amount, List<String> validationResults){
        if(!amount.matches("^\\{1,13}$|^\\d{1,13}\\.\\d{1,5}$")){
            validationResults.add("Invalid amount Format");
        }
    }
    default void validCurrency(String currency, List<String> validationResults ){
        if(!currency.matches("^[A-Z]{3,3}$")){
            validationResults.add("Invalid currency Format");
        }

    }
*/

}
