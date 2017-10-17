package service;


import classes.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WorkService {
    public static List<Work> works;
    public static List<String> fileList = new ArrayList<>();

    /**
     * Возвращает список контрольных работ
     * @param student
     * @return
     */
    public static List<Work> getAllWork(Student student) {
        //Метод возвращает все работы ученика и помещает их в Маp
        //Ключ это ID теста

        //запрашиваем из БД все работы ученика и помещаем их в arrayList
        works = new ArrayList<>();
        Subject subject = new Subject(1, "Биология");
        TestTemplate testTemplate1 = new TestTemplate(1, "Органы человека", "Описание темы", 5, subject);
        TestTemplate testTemplate2 = new TestTemplate(2, "Извлечение тел из жидкости", "Описание темы", 5, subject);
        TestTemplate testTemplate3 = new TestTemplate(3, "Продвинутые методы сложения", "Описание темы", 5, subject);
        List<Subject> subjects = new ArrayList<>();
        subjects.add(subject);

        Teacher teacher = new Teacher(1, 1, "login", LocalDate.now(), "Мария", "Голубец", "Петровна", student.getSchool(), new ArrayList<>(), 5, 10, "80 левел");
        teacher.getSubject().add(new SubjectTeacherConnector(1, teacher, subject));

        Status newStatus = new Status(1, "Новая");
        Status verifiedStatus = new Status(1, "Проверена");
        Status onVerifiedStatus = new Status(1, "На проверке");
        Test test1 = new Test(1, testTemplate1, teacher, student.getSchoolClass());
        test1.setStatus(newStatus);
        test1.setStartDate(LocalDate.of(2017, 10, 14));
        Test test2 = new Test(2, testTemplate2, teacher, student.getSchoolClass());
        test2.setStatus(onVerifiedStatus);
        test2.setStartDate(LocalDate.of(2017, 10, 14));
        Test test3 = new Test(3, testTemplate3, teacher, student.getSchoolClass());
        test3.setStatus(verifiedStatus);
        test3.setStartDate(LocalDate.of(2017, 10, 14));
        List<Test> tests = new ArrayList<>();
        tests.add(test1);
        tests.add(test2);
        tests.add(test3);
        Work tWork1 = new Work(1, test1, student);
        tWork1.setStatus(newStatus);
        Work tWork2 = new Work(2, test2, student);
        tWork2.setStatus(onVerifiedStatus);
        Work tWork3 = new Work(3, test3, student);
        tWork3.setStatus(verifiedStatus);
        tWork3.setMark(3);
        works.add(tWork1);
        works.add(tWork2);
        works.add(tWork3);
        return works;
    }

    /**
     * Возвращает контрольную работу по ID
     * @param id
     * @return
     */
    public static Work getWorkById(int id) {
        return works.get(id);
    }

    /**
     * Возвращает список файлов ученика прикрепленных к работе ученика
     * @param id
     * @return
     */
    public static List<String> getStudentFilesByWorkId(int id) {
//
//        for (int i = 0; i < 5; i++) {
//            fileList.add("img/expo-vogue"+i+".jpg");
//        }
        return fileList;
    }

    /**
     * Возвращает файлы учителя по id контрольной работы
     * @param id
     * @return
     */
    public static List<String> getTeacherFilesByWorkId(int id) {
        return fileList;
    }

    /**
     * Возвращает список вопросов по Id контрольной работы ученика
     * @param id
     * @return
     */
    public static List<String> getQuestionListByWorkId(int id) {
        List<String> questionList = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            questionList.add("Вопрос "+i+". лдодлодлоыв тидловяа длоичвдл оиыдвлои ыдвоилдывмл оидлоыидломы июдлмтюоИмб ордлл орплорпло рплори");
        }
        return questionList;
    }
}
