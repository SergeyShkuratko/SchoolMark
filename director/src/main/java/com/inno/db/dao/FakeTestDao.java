package com.inno.db.dao;

import com.inno.db.dto.TestStatisticDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FakeTestDao implements TestDao {
    @Override
    public List<TestStatisticDto> getTestsStatistic() {
        List<TestStatisticDto> result = new ArrayList<>(2);

        result.add(new TestStatisticDto(LocalDate.of(2017, 5, 23),
                "Иванов Иван Иванович", "Математика", "5a", 4.5f));
        result.add(new TestStatisticDto(LocalDate.of(2017, 7, 14),
                "Иванов Иван Иванович", "Биология", "7б", 3.7f));
        result.add(new TestStatisticDto(LocalDate.of(2017, 2, 2),
                "Петр Петрович Петров", "Математика", "3a", 4.3f));

        return result;
    }
}
