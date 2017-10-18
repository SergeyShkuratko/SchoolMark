package com.inno.db.dao;

import com.inno.db.dto.TestAndWorksInfoDto;
import com.inno.db.dto.TestStatisticDto;

import java.time.LocalDate;
import java.util.List;

/**
 * Предоставляет методы для доступа к хранилищу данных для получения статистики по тестам
 */
public interface TestStatisticDao {
    /**
     * Получение информации по контрольным за указанный период
     * @param dateFrom дата с которой начинается выборка (включая), null для игнорирования параметра
     * @param dateTo на какой дате заканчивается выборка (включая), null для игнорирования параметра
     * @return список TestStatisticDto содержащий краткую инфрмацию о тесте
     */
    List<TestStatisticDto> getTestsStatistic(LocalDate dateFrom, LocalDate dateTo);

    /**
     * Получение более подробной информации о контрольной включая статистику о сданных работах учеников
     * @param testId идентификатор контрольной работы
     * @return Описание контрольной работы и статистика по сданных учениками работах
     */
    TestAndWorksInfoDto getTestAndWorksInfo(int testId);
}
