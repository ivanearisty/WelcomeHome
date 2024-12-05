package PDS.Project3.Queries;

public class Queries {

    //PERSON QUERIES
    public static final String INSERT_USER = "INSERT INTO Person(userName, password, fname, lname, email, is_non_locked, is_enabled) VALUES (:userName, :password, :firstName, :lastName, :email, :enabled, :nonLocked)";

    //ROLE QUERIES
    public static final String SELECT_ROLE_BY_NAME = "SELECT * FROM Role WHERE roleID = :roleID";

    //ROLE-PERSON QUERIES
    public static final String INSERT_ROLE_TO_USER = "INSERT INTO RolePerson(roleID, userName) VALUES (:roleID, :userName)";

}
