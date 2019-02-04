package com.otakuy.otakuymusic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class Tag {
    @Id
    private String id;
    private String name;

}
