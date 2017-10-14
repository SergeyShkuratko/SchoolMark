package service;

import classes.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WorkService {
    private static List<Work> works;


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

        Teacher teacher = new Teacher(1, 1, "Мария", "Голубец", "Петровна", student.getSchool(), subjects, 5, 10, "80 левел");


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
        int i=0;
        for (Test test : tests) {
            Work tWork = new Work(i++, test, student);
            tWork.setStatus(verifiedStatus);
            tWork.setMark(5);
            works.add(tWork);
        }
        return works;
    }

    public static Work getWorkById(int id) {
        return works.get(id);
    }

    public static List<String> getFilesByWorkId(int id) {
        List<String> fileList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            fileList.add("img/expo-vogue"+i+".jpg");
        }
        return fileList;
    }

    public static List<String> getQuestionListByWorkId(int id) {
        List<String> fileList = new ArrayList<>();
        for (int i = 1; i < 20; i++) {
            fileList.add("Вопрос "+i+". лдодлодлоыв тидловяа длоичвдл оиыдвлои ыдвоилдывмл оидлоыидломы июдлмтюоИмб ордлл орплорпло рплори");
        }
        return fileList;
    }
}
