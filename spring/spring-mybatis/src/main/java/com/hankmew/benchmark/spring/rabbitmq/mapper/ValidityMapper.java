package com.hankmew.benchmark.spring.rabbitmq.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ValidityMapper {
    String valid();
}
