package com.otakuy.otakuymusic.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
@Data
public class Tag {
    @Id
    private int id;
    private String name;

}
