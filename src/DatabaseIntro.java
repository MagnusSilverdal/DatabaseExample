import javax.swing.*;
import java.sql.*;

/**
 * This is a class
 * Created 2020-11-24
 *
 * @author Magnus Silverdal
 */
public class DatabaseIntro {
    public static void main(String[] args) {
        JPasswordField pf = new JPasswordField();
        JOptionPane.showConfirmDialog(null, pf, "Enter Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        String password = new String(pf.getPassword());

        try {
            // Set up connection to database
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bookshop? " +
                            "allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                    "magnus", password);

            // Setup statement
            Statement stmt = conn.createStatement();

            // Create query and execute
            String SQLQuery = "select * from book";
            ResultSet rset = stmt.executeQuery(SQLQuery);

            // Loop through the result set and print
            // Need to know the table-structure
            while (rset.next()) {
                System.out.println(
                        rset.getInt("id") + ", " +
                        rset.getString("title") + ", " +
                        rset.getString("author") + ", " +
                        rset.getDouble("price") + ", " +
                        rset.getInt("quantity"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
