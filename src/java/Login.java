
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String eemail = request.getParameter("email-id");
        out.println(eemail);
        String ppass = request.getParameter("pwd");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/blogdb", "root", "hero");
            PreparedStatement stmt = (PreparedStatement) con.prepareStatement("select * from registerdb where Email=?");
            stmt.setString(1, eemail);

            ResultSet rs = stmt.executeQuery();
            boolean gh=rs.next();
           
            if (gh) {

                if (ppass.equals(rs.getString("Pass"))) {
                    HttpSession session=request.getSession();  
                    session.setAttribute("email",eemail); 
                    response.sendRedirect(request.getContextPath() + "/index.html");
                            
                } else {
                    out.println("<h4><b>Email-Id/Password Invalid</b></h4>");
                    RequestDispatcher rd = request.getRequestDispatcher("login.html");
                    rd.include(request, response);

                }
            } else {
                out.println("<h4><b>Email-Id Dosent Exist</b></h4>");
                RequestDispatcher rd = request.getRequestDispatcher("login.html");
                rd.include(request, response);

            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            out.print(ex);
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
