package com.example.bookstore.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatsDateDetails {
    @ApiModelProperty(
            value = "startDate to get data and format \"dd-MM-yyyy\"",
            required = true,
            name = "startDate",
            dataType = "Date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date startDate;


    @ApiModelProperty(
            value = "endDate to get data and format \"dd-MM-yyyy\"",
            required = true,
            name = "code",
            dataType = "Date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date endDate;
}

