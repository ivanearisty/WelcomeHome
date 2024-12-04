package PDS.Project3.Queries;

public class UserQueries {
    public static final String INSERT_NEW_USER = "";
    public static final String INSERT_USER_QUERY = "INSERT INTO Person(userName, password, fname, lname, email, is_non_locked, is_enabled) VALUES (:userName, :password, :firstName, :lastName, :email, :enabled, :nonLocked)";
}
