package interfaces.dao;

import classes.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface TestDAO {

    //TODO или лучше получать спиоск контрольных учителя вместе с учителем в TeacherDAO ??
    List<Test> getTestsByTeacher(Teacher teacher);

    /**
     * Метод возвращает список всех работ учеников по контрольной
     * @return список работ учеников по контрольной
     */
    List<Work> getWorks();

    /**
     * Метод возвращает список вопросов/ответов/критериев по контрольной работе
     * @return список вопросов
     */
    List<Question> getQuestions();

    /**
     * Метод добавляет список вопросов для контрольной работы
     * @param questions - коллекция вопросов
     * @return результат выполнения операции
     */
    boolean addQuestions(Collection<Question> questions);

    /**
     * Метод назначает контрольную на конкретный класс
     * @param schoolClass - класс, где будет проводиться контрольная
     * @return результат выполнения операции
     */
    boolean assignToClass(SchoolClass schoolClass);

    /**
     * Метод устанавливает для контрольной дату проведения
     * @param date - дата проведения контрольной
     * @return результат выполнения операции
     */
    boolean assignDate(LocalDate date);

    /**
     * Метод позволяет добавить ответы ученика к контрольной
     * @param test - объект, содержащий ответы ученика
     * @return результат выполнения операции
     */
    boolean addWork(Work test);

    /**
     * Метод позволяет отметить ученика как отсутствующего
     * @param student - ученик для которого устанавливается признак
     * @return результат выполнения операции
     */
    boolean markAbsent(Student student);

    /**
     * Метод позволяет определить все ли ученики загрузили контрольные работы
     * @return возвращает true, если для всех учеников есть загруженные работы/
     * проставлен флаг отсутствия
     */
    boolean isFullyLoaded();

    /**
     * Метод сохраняет контрольную работу как шаблон
     * @return результат выполнения операции
     */
    boolean saveAsTemplate(Test event);
}
