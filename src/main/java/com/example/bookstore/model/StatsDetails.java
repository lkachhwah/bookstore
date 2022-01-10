package com.example.bookstore.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class StatsDetails {
    private List<StatsDetail> statsDetailList= new ArrayList<>();
}
