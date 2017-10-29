package com.inno.db.dao;

import com.inno.db.dto.*;
import com.inno.utils.DateConverter;
import com.inno.utils.DefaultDateConverter;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class FakeTestStatisticDao implements TestStatisticDao {
    private DateConverter dc;

    public FakeTestStatisticDao() {
        this.dc = new DefaultDateConverter();
    }

    @Override
    public List<TestStatisticDto> getTestsStatistic(int userId, LocalDate dateFrom, LocalDate dateTo) {
        List<TestStatisticDto> result = new ArrayList<>(2);

        result.add(new TestStatisticDto(1, "23.05.2017",
                "Иванов Иван Иванович", "Математика", "5a", 4.5f));
        result.add(new TestStatisticDto(2, "14.07.2017",
                "Иванов Иван Иванович", "Биология", "7б", 3.7f));
        result.add(new TestStatisticDto(3, "02.02.2017",
                "Петр Петрович Петров", "Математика", "3a", 4.3f));

        return result.stream()
                .filter(it -> isInDateRange(dc.parseStringToLocalDate(it.getDate()), dateFrom, dateTo))
                .collect(Collectors.toList());
    }

    private boolean isInDateRange(LocalDate date, LocalDate dateFrom, LocalDate dateTo) {
        return (dateFrom == null || date.equals(dateFrom) || date.isAfter(dateFrom)) &&
                (dateTo == null || date.equals(dateTo) || date.isBefore(dateTo));
    }

    @Override
    public TestAndWorksInfoDto getTestAndWorksInfo(int testId) {
        QuestionDto question1 = new QuestionDto(1, "Сколько будет 2 + 2?", "4");
        question1.addAllCriterion(
                Collections.singleton(new CriterionDto(1, "Должен быть указан верный ответ")));

        QuestionDto question2 = new QuestionDto(2, "Решите уравнение 2x + 3 = 9", "x = 3");
        question2.addAllCriterion(
                Collections.singleton(new CriterionDto(2, "Должны быть указаны все этапы решения")));

        Set<WorkDto> workList = new HashSet<>();
        workList.add(new WorkDto(1, "Петр Петрович Сидоров", 4, false));
        workList.add(new WorkDto(2, "Степан Иванович Петров", 3, true));

        TestVariantDto testVariant = new TestVariantDto(1, "Общий вариант");
        testVariant.addAllQuestions(Arrays.asList(question1, question2));

        return new TestAndWorksInfoDto(Collections.singleton(testVariant), workList);
    }
}
