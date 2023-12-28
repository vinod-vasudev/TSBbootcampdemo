package com.tsb.banking.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.tsb.banking.model.BalancesResponseDataBalanceInner;
import com.tsb.banking.model.BalancesResponseDataBalanceInnerCreditLineInner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CustomMapper {

    CustomMapper INSTANCE = Mappers.getMapper(CustomMapper.class);

    @Mapping(target = "accountId", expression = "java(sourceJson.at(\"Data.CurrentAccountNumber.AccountIdentification.IdentifierValue.Value\").asText())")
//    @Mapping(target = "amount.amount", expression = "java(sourceJson.at(\"Data.Balance[0].ClientAmount.Cash\").asText())")
//    @Mapping(target = "amount.currency", expression = "java(sourceJson.at(\"Data.Balance[0].ClientAmount.CurrencyType\").asText())")
//    @Mapping(target = "creditDebitIndicator", expression = "java(sourceJson.at(\"Data.Balance[0].CDI\").asText())")
//    @Mapping(target = "type", expression = "java(sourceJson.at(\"Data.Balance[0].Catagory\").asText())")
//    @Mapping(target = "dateTime", expression = "java(sourceJson.at(\"Data.Balance[0].DateTimeStamp\").asText())")
//    @Mapping(target = "creditLine", expression = "java(mapCreditList(sourceJson.get(\"Data.Balance[0].CreditList\")))")
    BalancesResponseDataBalanceInner mapToTargetBalance(JsonNode sourceJson);

    List<BalancesResponseDataBalanceInner> mapToBalancesResponseDataBalanceInner(List<JsonNode> balanceList);

//    default List<BalancesResponseDataBalanceInnerCreditLineInner> mapCreditList(JsonNode creditList) {
//        // Implement logic to map CreditList to List<CreditLine>
//        // You can use a loop or other logic based on your specific requirements
//        return null;
//    }


}
