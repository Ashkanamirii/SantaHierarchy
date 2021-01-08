import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

        String customerInsert = "INSERT INTO tomte(bosses, sub_busses) VALUES('Myran','Bladlusen' )";
        try {
            Statement statement = connectionDB.createStatement();
            statement.executeUpdate(customerInsert);

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }


    }


    public List<String> subSelect(String name) {
        List<String> subBosses = new ArrayList<>();
        return subBosses;
    }

//    public static void main(String[] args) {
//        DataBaseConfig dataBaseConfig = new DataBaseConfig();
//        dataBaseConfig.insertToDb();
//    }
}
