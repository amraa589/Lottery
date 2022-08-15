package mn.edu.num.lotteryProject.utils;

import java.sql.*;

public class LotteryWinnerAlgorithm {

    String myDriver = "org.gjt.mm.mysql.Driver";
    String myUrl = "jdbc:mysql://localhost:3306/projectLottery\n";
    Connection conn = DriverManager.getConnection(myUrl, "root", "Bichamdhairtai2002");

    String query = "SELECT * FROM users";

    // create the java statement
    Statement st = conn.createStatement();

    // execute the query, and get a java resultset
    ResultSet rs = st.executeQuery(query);

    public LotteryWinnerAlgorithm() throws SQLException {
    }
}
