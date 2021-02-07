package com.hankmew.benchmark.spring.jpa.dao;

import com.hankmew.benchmark.spring.jpa.entity.AccountEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDao  extends CrudRepository<AccountEntity, String> {
}
