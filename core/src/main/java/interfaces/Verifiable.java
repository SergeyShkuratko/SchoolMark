package interfaces;

import classes.Teacher;

public interface Verifiable {

    /**
     * Метод предназначен для отправки на проверку/перепроверку
     * @return результат выполнения операции
     */
    boolean verify();

    /**
     * Метод назначает проверяющего для объекта
     * @param verifier - проверяющай, на которого будет назначен объект
     * @return результат выполнения операции
     */
    boolean assignToVerifier(Teacher verifier);
}
