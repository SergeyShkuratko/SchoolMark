package com.inno.db.dao;

import com.inno.db.dto.TestDto;

import java.util.List;

public interface TestDao {
    List<TestDto> findAll();
}
