package com.hankmew.benchmark.spring.tomcat;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public interface UserParam {
    @Length(min = 1, max = 3)
    String getName();
    @Min(0)
    @Max(3)
    Long getUid();
}
