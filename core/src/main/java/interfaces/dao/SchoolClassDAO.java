package interfaces.dao;

import classes.dto.SchoolClassDTO;
import exceptions.SchoolClassDAOException;

public interface SchoolClassDAO {

    boolean add(SchoolClassDTO schoolClass) throws SchoolClassDAOException;
}
