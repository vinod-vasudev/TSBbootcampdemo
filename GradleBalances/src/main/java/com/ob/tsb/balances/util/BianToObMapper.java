package com.ob.tsb.balances.util;

import com.ob.tsb.balances.exception.CustomException;
import com.ob.tsb.balances.model.bian.BianReadBalance;
import com.ob.tsb.balances.model.ob.OBReadBalance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.DateTimeException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
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
        try {
            return bianDateTime.format(new DateTimeFormatterBuilder().append(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                    .appendPattern("xxx")
                    .toFormatter());
        } catch (DateTimeException ignored){
            throw new CustomException("Bian date time is not in ISO_DATE_TIME format");
        }

    }

}
