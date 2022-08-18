
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Like extends HttpServlet{

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        PrintWriter out = res.getWriter();

        String name = req.getParameter("uname");
        String title = req.getParameter("title");
        //String prop = System.getProperty("user.name");
        res.setContentType("text/html;charset=UTF-8");
        Connection con;
        try {
            
           Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/blogdb", "root", "hero");
            PreparedStatement stmt2 = con.prepareStatement(" select Name from registerdb order by id desc limit 1;");
            ResultSet rs=stmt2.executeQuery();
            rs.next();
            String prop=rs.getString("Name");
           
            
            PreparedStatement stmt = con.prepareStatement("call likep(?,?,?)");
            stmt.setInt(1, 1);
            stmt.setString(2, prop);
            stmt.setString(3, title);
            stmt.executeUpdate();
            
            PreparedStatement stmt1 = con.prepareStatement("insert into data1(votes,l_name,l_title) values(?,?,?)");
            stmt1.setInt(1, 1);
            stmt1.setString(2, prop);
            stmt1.setString(3, title);
            stmt1.executeUpdate();
            
            
            
             req.getRequestDispatcher("/detail").include(req, res);  
        } catch (ClassNotFoundException | SQLException ex) {
            out.println(ex);
            Logger.getLogger(Like.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
