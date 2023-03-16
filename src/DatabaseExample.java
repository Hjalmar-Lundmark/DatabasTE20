import javax.swing.*;
import java.sql.*;

public class DatabaseExample {
    public static void main(String[] args) {
        Connection conn = null;
        String user = "magnus";
        //String user = "te20";
        /*JPasswordField pf = new JPasswordField();
        JOptionPane.showConfirmDialog(null, pf, "password?", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        String password = new String(pf.getPassword());*/
        //String password = "6Xcwh5DQYLAe";
        String password = "test";


        try {
            //conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bookshop? "+
            //        "allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",user,password);
            //conn = DriverManager.getConnection("jdbc:mysql://db.umea-ntig.se:3306/te20? "+
            //        "allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",user,password);
            conn = DriverManager.getConnection("jdbc:mysql://10.70.45.159:3306/bookshop? "+
                    "allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",user,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            Statement stmt = conn.createStatement();
            String SQLQuery = "SELECT * FROM hl21forum";
            ResultSet result = stmt.executeQuery(SQLQuery);

            ResultSetMetaData metadata = result.getMetaData();

            int numCols = metadata.getColumnCount();
            for (int i = 1 ; i <= numCols ; i++) {
                System.out.println(metadata.getColumnClassName(i));
            }

            while (result.next()) {
                String output = "";
                output += result.getInt("id") + ", " +
                        result.getString("title") + ", " +
                        result.getString("author") + ", " +
                        result.getDouble("price") + ", " +
                        result.getInt("quantity");
                System.out.println(output);
            }

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
