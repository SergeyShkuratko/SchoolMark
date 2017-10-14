package service;

import DAOImplementation.TestDAOImpl;
import classes.Work;

import java.util.List;

public class TestService {

    public List<Work> getWorks() {

        return new TestDAOImpl().getWorks();
    }
}
