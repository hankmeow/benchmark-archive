package com.hankmew.benchmark.spring.archetype.service.param;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public interface UserParam {
    @Min(1)
    @NotNull
    Long getUid();
}
