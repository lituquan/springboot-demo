package com.example.mongo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(value = "user")
public class User {
    @Id
    private String id;
    @Field("id")
    @Indexed(unique = true)
    private Integer userId;
    @Field("name")
    private String name;
}

