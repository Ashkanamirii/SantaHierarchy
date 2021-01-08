import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Created by Ashkan Amiri
 * Date:  2021-01-07
 * Time:  15:44
 * Project: inlämningsUppgift_1
 * Copyright: MIT
 */
public class DataBaseConfig {

    public Connection databaseLink;

    public Connection getConnection() {
        String databaseName = "tomte_recursve_db";
        String databaseUsername = "Ashkan";
        String databasePassword = "Ashkan1885A!";
        String url = "jdbc:mysql://localhost:3306/" + databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUsername, databasePassword);

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return databaseLink;
    }

    public void insertToDb() {
        DataBaseConfig connection = new DataBaseConfig();
        Connection connectionDB = connection.getConnection();
        String customerInsert = "INSERT INTO tomte(bosses, sub_bosses) VALUES('Myran','Bladlusen' )";
        try {
            Statement statement = connectionDB.createStatement();
            statement.executeUpdate(customerInsert);

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
// Just for test the insert to data base
//    public static void main(String[] args) {
//        DataBaseConfig dataBaseConfig = new DataBaseConfig();
//        dataBaseConfig.insertToDb();
//    }
}
