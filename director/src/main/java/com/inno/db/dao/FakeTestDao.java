package com.inno.db.dao;

import com.inno.db.dto.QuestionDto;
import com.inno.db.dto.TestAndWorksInfoDto;
import com.inno.db.dto.TestStatisticDto;
import com.inno.db.dto.WorkDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FakeTestDao implements TestDao {
    @Override
    public List<TestStatisticDto> getTestsStatistic() {
        List<TestStatisticDto> result = new ArrayList<>(2);

        result.add(new TestStatisticDto(1, LocalDate.of(2017, 5, 23),
                "Иванов Иван Иванович", "Математика", "5a", 4.5f));
        result.add(new TestStatisticDto(2, LocalDate.of(2017, 7, 14),
                "Иванов Иван Иванович", "Биология", "7б", 3.7f));
        result.add(new TestStatisticDto(3, LocalDate.of(2017, 2, 2),
                "Петр Петрович Петров", "Математика", "3a", 4.3f));

        return result;
    }

    @Override
    public TestAndWorksInfoDto getTestAndWorksInfo(int testId) {
        Set<QuestionDto> questions = new HashSet<>();
        questions.add(
                new QuestionDto(1, "Сколько будет 2 + 2?", "4",
                        "Должен быть указан верный ответ"));
        questions.add(
                new QuestionDto(2, "Решите уравнение 2x + 3 = 9", "x = 3",
                        "Должны быть указаны все этапы решения"));

        Set<WorkDto> workList = new HashSet<>();
        workList.add(new WorkDto(1, "Петр Петрович Сидоров", 4, false));
        workList.add(new WorkDto(2, "Степан Иванович Петров", 3, true));

        return new TestAndWorksInfoDto(questions, workList);
    }
}
