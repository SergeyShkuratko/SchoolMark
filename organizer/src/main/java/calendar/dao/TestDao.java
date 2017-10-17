package calendar.dao;

import calendar.dao.exceptions.TestDAOException;
import calendar.dto.TestDto;

import java.time.LocalDate;
import java.util.List;

public interface TestDao {
    /**
     * Получить работы за период с начала дня begin по конец дня end,
     * в которых учитель является организатором
     * @param user_id - идентификатор пользователя, ОРГАНИЗАТОРА контрольной работы
     * @param begin - дата начала
     * @param end - дата окончания (включительно по конец дня)
     * @return - список контрольных работ
     */
    List<TestDto> getTestsByUserIdDescOrder(int user_id, LocalDate begin, LocalDate end)throws TestDAOException;

}

