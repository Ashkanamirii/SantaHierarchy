import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Ashkan Amiri
 * Date:  2021-01-07
 * Time:  14:29
 * Project: inlämningsUppgift_1
 * Copyright: MIT
 */
public class SantaClausHierarchy {
    private DataBaseConfig connection = new DataBaseConfig();
    private Connection connectionDB = connection.getConnection();
    private List<String> children = new ArrayList<>();
    private List<String> bosses = new ArrayList<>();
    private boolean run = true;
    private final ImageIcon tomtenImage = new ImageIcon("D:\\New folder\\E&F\\fuktionalProgramming" +
            "\\inlämningsUppgift_1\\tomte_chefs hierarki\\src\\main\\resources\\unnamed.jpg");
    String[] choice = {"Bosses", "Sub bosses"};

    // recursive methode
    public List<String> upRoot(String name) {
        String s = "select bosses from tomte t where t.sub_bosses ='" + name + "'";
        try {
            Statement statement = connectionDB.createStatement();
            ResultSet queryResult = statement.executeQuery(s);
            while (queryResult.next()) {
                String root = queryResult.getString("bosses");
                bosses.add(root);
                upRoot(root);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return bosses;
    }

    public List<String> subRoot(String name) {
        String s = "select sub_bosses from tomte t where t.bosses ='" + name + "'";
        try {
            Statement statement = connectionDB.createStatement();
            ResultSet queryResult = statement.executeQuery(s);
            while (queryResult.next()) {
                children.add(queryResult.getString("sub_bosses"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return children;
    }


    public String showInputDialog(String title) {
        String name = JOptionPane.showInputDialog(null,
                "Enter the name, to get the " + title + ": ",
                title, JOptionPane.INFORMATION_MESSAGE);
        if (name != null && !name.isBlank() && !name.isEmpty()) {
            name= name.trim();
            return name.substring(0, 1).toUpperCase() + name.substring(1);
        }else{
            showMessageDialog(Collections.singletonList("GOOD BYE"), "BYE");
            System.exit(0);
            return null;
        }
    }

    public void showMessageDialog(List<String> userChoice, String title) {
        StringBuilder output = new StringBuilder();
        for (String s : userChoice) {
            output.append(s).append("\n");
        }
        JOptionPane.showMessageDialog(null, output.toString(), title, JOptionPane.INFORMATION_MESSAGE);
    }

    public void userChoice() {
        while (run) {
            String userInput = (String) JOptionPane.showInputDialog(null,
                    "Chose one of them that you want to get information about:",
                    "SantaClausHierarchy", JOptionPane.QUESTION_MESSAGE, tomtenImage, choice, choice[0]);
            if (userInput == null) {
                showMessageDialog(Collections.singletonList("GOOD BYE"), "BYE");
                System.exit(0);
            }
            if (userInput.equals("Bosses")) {
                List<String> result = upRoot(showInputDialog("BOSSES"));
                if (result.size() == 0)
                    showMessageDialog(Collections.singletonList("""
                            Either you have entered incorrectly
                            or it has no information at our place,\s
                            try again"""), "ERROR");
                else {
                    showMessageDialog(result, "BOSSES");
                    run = false;
                }
            } else if (userInput.equals("Sub bosses")) {
                List<String> result = subRoot((showInputDialog("SUB BOSSES")));
                if (result.size() == 0)
                    showMessageDialog(Collections.singletonList("""
                            Either you have entered incorrectly
                            or it has no information at our place,\s
                            try again"""), "ERROR");
                else {
                    showMessageDialog(result, "SUB BOSSES");
                    run = false;
                }
            } else {
                System.exit(0);
                run = false;
            }
        }
    }

    public static void main(String[] args) {
        SantaClausHierarchy t = new SantaClausHierarchy();
        t.userChoice();
    }
}
