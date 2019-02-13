package com.otakuy.otakuymusic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("star")
@AllArgsConstructor
public class Star {
    @Id
    private String id;
    private String starFrom;//star来源
    private String starTo;//被star者
    private String starAt;//专辑
    private Integer num;//数量
}
