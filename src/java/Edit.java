
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Edit extends HttpServlet {

    protected void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        String varname = req.getParameter("uname");

        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/blogdb", "root", "hero");
            PreparedStatement stmt = (PreparedStatement) con.prepareStatement("select * from blogtb where Name=?");
            stmt.setString(1, varname);
            ResultSet resultset = stmt.executeQuery();
            resultset.next();
            {
                String Name = resultset.getString("Name");
                String Title = resultset.getString("Title");
                String Description = resultset.getString("Description");
                String dsc = Description;
                int num_Id = resultset.getInt("b_id");
                String Category = resultset.getString("Category");
                out.print("<html><head>\n"
                        + "        <title>TODO supply a title</title>\n"
                        + "        <meta charset=\"UTF-8\">\n"
                        + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                        + "        <link rel=\"stylesheet\" href=\"https://unpkg.com/aos@next/dist/aos.css\">\n"
                        + "        <script src=\"https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js\" integrity=\"sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p\" crossorigin=\"anonymous\"></script>\n"
                        + "        <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js\" integrity=\"sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF\" crossorigin=\"anonymous\"></script>\n"
                        + "        <link rel=\"canonical\" href=\"https://getbootstrap.com/docs/5.0/examples/navbar-fixed/\">\n"
                        + "        <link href=\"/docs/5.0/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC\" crossorigin=\"anonymous\">\n"
                        + "        <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC\" crossorigin=\"anonymous\">\n"
                        + "\n"
                        + "    </head>\n"
                        + "\n"
                        + "    <body class=\"\"   style=\"  background-image: linear-gradient(to right, rgba(255,0,0,0), rgba(255,0,0,0.7));>\n"
                        + "        <div class=\"bg-image \" \n"
                        + "           \n"
                        + "             background-repeat:no-repeat"
                        + "height: 100vh  pt-5 pb-5\">\n"
                        + "\n"
                        + "\n"
                        + "             <center><div class=\"pt-5 text-center text-success  w-50\">\n"
                        + "                <h1>You Can change Your Blog Here </h1>\n"
                        + "            </div>\n"
                        + "           \n"
                        + "                <div class=\"modal-body  w-50 pt-5 m-5 pb-5 py-5 border border-dark \">\n"
                        + "                    <form action=\"Stored?bid="+num_Id+"\" method=\"post\">\n"
                        + "\n"
                        + "                        <label class=\"fw-bolder\"> Write Your  name</label><br> <input type=\"text\"  name=\"uname\" disabled=\"\" class=\"required fw-bolder  m-2 text-center\" value=\" "+ Name +"\"><br>\n"
                        + "                        <label class=\"fw-bolder\">Write Blog  Title</label><br><input type=\"text\" name=\"title1\" class=\" required fw-bolder  m-2 text-center\" disabled=\"\" value=\" "+ Title +"\"><br>\n"
                        + "\n"
                        + "                        <label class=\"fw-bolder\"> Write Blog </label><br>\n"
                        + "                        <textarea class=\"\" name=\"desc1\" style=\"width: 380px;\" \">"+ Description+"</textarea><br>\n"
                        + "                        <br>\n"
                        + "                        <label class=\"fw-bolder \">  Select Category<br><select id=\"cars\" disabled=\"\" name=\"cat\" class=\"fw-bolder  m-2 required\">\n"
                        + "                                <option value=\"Science\">Science</option>\n"
                        + "                                <option value=\"Technology\">Technology</option>\n"
                        + "                                <option value=\"Cartoon\">Cartoon</option>\n"
                        + "                                <option value=\"cars\">cars</option>\n"
                        + "                                <option value=\"others\">others</option>\n"
                        + "                            </select></label><br>\n"
                        + "                        <hr>\n"
                        + "\n"
                        + "                        <div class=\" text-center\">\n"
                        + "                            <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Close</button>\n"
                   
                        + "                            <button type=\"submit\" class=\"btn btn-warning\" data-bs-dismiss=\"modal\">UPDATE</button>\n"
                        + "                        </div>\n"
                        + "\n"
                        + "                    </form>                </div>\n"
                        + "            </center>\n"
                        + "\n"
                        + "        </div>\n"
                        + "\n"
                        + "\n"
                        + "    </body></html>");
            }

        } catch (Exception ex) {
            out.print(ex);
            Logger.getLogger(Edit.class.getName()).log(Level.SEVERE, null, ex);
//           System.out.println(ex+"name should be unique");
        }

    }
}
