package com.inno.db.dao;

import com.inno.db.dto.TestDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FakeTestDao implements TestDao {
    @Override
    public List<TestDto> findAll() {
        List<TestDto> result = new ArrayList<>(2);

        result.add(new TestDto(LocalDate.of(2017, 5, 23), "Иванов Иван Иванович",
                "Математика", "5a", 4.5f));
        result.add(new TestDto(LocalDate.of(2017, 7, 14), "Петр Петрович Петров",
                "Биология", "7б", 3.7f));

        return result;
    }
}
