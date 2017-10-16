package calendar.dao;

import calendar.dao.exceptions.TestDAOException;
import classes.Teacher;
import calendar.dto.TestDTO;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface TestDAO {
    /**
     * Получить работы за период с начала дня begin по конец дня end,
     * в которых учитель является организатором
     * @param teacher - ОРГАНИЗАТОР контрольной работы
     * @param begin - дата начала
     * @param end - дата окончания (включительно по конец дня)
     * @return - список контрольных работ
     */
    List<TestDTO> getTestsDTOByTeacherDuringPeriod(Teacher teacher, LocalDate begin, LocalDate end)throws TestDAOException;

}
