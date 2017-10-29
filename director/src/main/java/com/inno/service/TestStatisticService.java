package com.inno.service;

import com.inno.db.dto.TestAndWorksInfoDto;
import com.inno.db.dto.TestStatisticDto;
import com.inno.db.dto.TestStatisticWithoutOrganizerDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Сервис класс предназначенный для получения информации по проведенным тестам
 */
public interface TestStatisticService {
    /**
     * Получение информации по контрольным за указанный период
     * @param userId Id пользователя под которым зашел директор. Выборка ограничиться школой.
     * @param dateFrom дата с которой начинается выборка (включая)
     * @param dateTo на какой дате заканчивается выборка (включая)
     * @return список TestStatisticDto содержащий краткую инфрмацию о тесте
     */
    List<TestStatisticDto> getTestsStatisticByUserId(int userId, LocalDate dateFrom, LocalDate dateTo);

    /**
     * Получение информации по тестам за указанный период с группировкой по учителю, проводившему контрольную
     * @param userId Id пользователя под которым зашел директор. Выборка ограничиться школой.
     * @param dateFrom дата с которой начинается выборка (включая)
     * @param dateTo на какой дате заканчивается выборка (включая)
     * @return Map, в котором ключ это имя учителя, проводившего контрольную,
     *  а значение это список TestStatisticDto содержащий краткую инфрмацию о тесте
     */
    Map<String, List<TestStatisticWithoutOrganizerDto>> getTestsStatisticGroupedByOwner(int userId,
                                                                                        LocalDate dateFrom,
                                                                                        LocalDate dateTo);

    /**
     * Получение более подробной информации о контрольной включая статистику о сданных работах учеников
     * @param testId идентификатор контрольной работы
     * @return Описание контрольной работы и статистика по сданных учениками работах
     */
    TestAndWorksInfoDto getTestAndWorksInfo(int testId);
}
