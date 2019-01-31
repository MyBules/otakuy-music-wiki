package com.otakuy.otakuymusic.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "test")
@Data
public class Person {
    @Id
    private String id;
    private String username;
    private String password;
    private Integer age;
}
