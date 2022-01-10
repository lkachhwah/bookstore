package com.example.bookstore.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {
    @ApiModelProperty(
            value = "firstName of the customer",
            required = false,
            name = "firstName",
            dataType = "String")
    private String firstName;
    @ApiModelProperty(
            value = "middleName of the customer",
            required = false,
            name = "middleName",
            dataType = "String")
    private String middleName;
    @ApiModelProperty(
            value = "lastName of the customer",
            required = false,
            name = "lastName",
            dataType = "String")
    private String lastName;
    @ApiModelProperty(
            value = "emailId of the customer",
            required = true,
            name = "emailId",
            dataType = "String")
    private String emailId;
}
