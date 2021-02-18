package com.hankmew.benchmark.spring.archetype.service.result;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserResult {
    private Long uid;
    private String name;
    private String phone;
}
