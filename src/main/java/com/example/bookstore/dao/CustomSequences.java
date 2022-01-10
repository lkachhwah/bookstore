package com.example.bookstore.dao;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customSequences")
@Data
@Builder
public class CustomSequences {
    @Id
    private String id;
    private int seq;
}
