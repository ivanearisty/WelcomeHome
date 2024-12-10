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

    //ITEM QUERIES
    public static final String INSERT_ITEM = """
    INSERT INTO Item (iDescription, photo, color, isNew, hasPieces, material, mainCategory, subCategory)
    VALUES (:iDescription, :photo, :color, :isNew, :hasPieces, :material, :mainCategory, :subCategory);
    """;
    public static final String SELECT_ITEM_BY_ID = "SELECT * FROM Item WHERE itemID = :itemID";
    public static final String SELECT_LOCATION = "SELECT * FROM Location WHERE roomNum = :roomNum AND shelfNum = :shelfNum";

    //PIECE QUERIES
    public static final String SELECT_PIECES_BY_ITEM_ID = "SELECT * FROM Piece WHERE ItemID = :itemID;";
    public static final String INSERT_PIECE =
            "INSERT INTO Piece(ItemID, pieceNum, pDescription, length, width, height, roomNum, shelfNum, pNotes) VALUES (:ItemID, :pieceNum, :pDescription, :length, :width, :height, :roomNum, :shelfNum, :pNotes)";

    //ORDER QUERY
    public static final String SELECT_ORDER_BY_ORDER_ID = """
            SELECT
                i.ItemID AS ItemID,
                i.iDescription AS ItemDescription,
                i.color AS color,
                i.isNew AS isNew,
                i.hasPieces AS hasPieces,
                i.material AS material,
                i.mainCategory AS mainCategory,
                i.subCategory AS subCategory,
                p.pieceNum AS pieceNum,
                p.pDescription AS pDescription,
                p.length AS length,
                p.width AS width,
                p.height AS height,
                p.roomNum AS roomNum,
                p.shelfNum AS shelfNum,
                p.pNotes AS pNotes
            FROM ItemIn ii
            JOIN Item i ON ii.ItemID = i.ItemID
            LEFT JOIN Piece p ON i.ItemID = p.ItemID
            WHERE ii.orderID = :orderID
            ORDER BY i.ItemID, p.pieceNum
            """;

    public static final String INSERT_ORDER = "INSERT INTO Ordered(orderDate, orderNotes, supervisor, client) VALUES (:date, :notes, :supervisor, :client)";
}
