
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterPage extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        String user_name = req.getParameter("username");
        String pass1 = req.getParameter("pass1");
        String pass2 = req.getParameter("pass2");
        String email = req.getParameter("email");
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/blogdb", "root", "hero");

            //String passx = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
            //String sql = "";
            //PreparedStatement prepStmt = con.prepareStatement(sql);
            //ResultSet rs = prepStmt.executeQuery();
            if (pass1.matches(pass2)) {
                
                PreparedStatement stmt = (PreparedStatement) con.prepareStatement("insert into registerdb(Name,Email,Pass) values(?,?,?)");
                stmt.setString(1, user_name);
                stmt.setString(2, email);
                stmt.setString(3, pass1);

                int row = stmt.executeUpdate();

                if (row == 1) {

                    //out.println("Registerd Successfully");
                    RequestDispatcher rd = req.getRequestDispatcher("login.html");
                    rd.include(req, res);

                } else {
                    out.println("Fatal.....");

                }
            } else {
                out.println("Sorry password not match ");
            }
            RequestDispatcher rd = req.getRequestDispatcher("register.html");
            rd.include(req, res);
        } catch (ClassNotFoundException | NullPointerException ex) {
            out.println(ex);
            Logger.getLogger(RegisterPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            out.println(ex);
            Logger.getLogger(RegisterPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
