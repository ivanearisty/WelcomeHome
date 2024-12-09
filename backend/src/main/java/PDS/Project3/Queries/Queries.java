package PDS.Project3.Queries;

public class Queries {

    //PERSON QUERIES
    public static final String INSERT_USER = "INSERT INTO Person(userName, password, fname, lname, email, is_non_locked, is_enabled) VALUES (:userName, :password, :firstName, :lastName, :email, :enabled, :nonLocked)";
    public static final String SELECT_USER_BY_USERNAME = "SELECT * FROM Person WHERE userName = :userName";
    public static final String COUNT_USERNAME = "SELECT COUNT(*) FROM Person WHERE userName = :userName";

    //ROLE QUERIES
    public static final String SELECT_ROLE_BY_NAME = "SELECT * FROM Role WHERE roleID = :roleID";

    //ROLE-PERSON QUERIES
    public static final String INSERT_ROLE_TO_USER = "INSERT INTO RolePerson(roleID, userName) VALUES (:roleID, :userName)";
    public static final String SELECT_ROLE_FROM_USERNAME = """
            SELECT r.roleID, r.roleDescription, r.rolePermissions
            FROM Role as r
            INNER JOIN RolePerson as rp ON r.roleID = rp.roleID
            INNER JOIN Person as p ON rp.userName = p.userName
            WHERE p.userName = :userName;
            """;
}
