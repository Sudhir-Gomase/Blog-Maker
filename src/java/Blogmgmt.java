import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Blogmgmt extends HttpServlet {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String var = req.getParameter("name");
        String title = req.getParameter("title");
        String desc = req.getParameter("desc");
        String cat = req.getParameter("cat");
        PrintWriter out = res.getWriter();
        res.setContentType("text/html;charset=UTF-8");

        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/blogdb", "root", "hero");
            PreparedStatement stmt = (PreparedStatement) con.prepareStatement("insert into blogtb(Name,Title,Description,Category) values(?,?,?,?)");
            stmt.setString(1, var);
            stmt.setString(2, title);
            stmt.setString(3, desc);
            stmt.setString(4, cat);
            stmt.executeUpdate();
            Statement statement = con.createStatement();
            ResultSet resultset = statement.executeQuery("select * from blogtb");

            req.getRequestDispatcher("index.html").include(req, res);

            while (resultset.next()) {
                String Name = resultset.getString("Name");
                
                String Title = resultset.getString("Title");
                String Descript = resultset.getString("Description");
                String dsc = Descript.substring(0, 90);
                
                String Category = resultset.getString("Category");
                out.print("<head>\n"
                        + "        <title>TODO supply a title</title>\n"
                        + "        <meta charset=\"UTF-8\">\n"
                        + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                        + "        <!--<plugins> -->\n"
                        + "  <link rel=\"stylesheet\" href=\"https://unpkg.com/aos@next/dist/aos.css\" />"
                        + "        <script src=\"https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js\" integrity=\"sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p\" crossorigin=\"anonymous\"></script>\n"
                        + "        <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js\" integrity=\"sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF\" crossorigin=\"anonymous\"></script>\n"
                        + "        <link rel=\"canonical\" href=\"https://getbootstrap.com/docs/5.0/examples/navbar-fixed/\">\n"
                        + "        <link href=\"/docs/5.0/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC\" crossorigin=\"anonymous\">\n"
                        + "        <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC\" crossorigin=\"anonymous\">\n"
                        + "   \n"
                        + "    </head>"
                        + "<center>"
                        + " <div class=\"shadow-lg card  border border-dark\" style=\"max-width: 900px;\" data-aos=\"zoom-in\" >\n"
                        + "                        <div class=\"row g-0\" style=\"background-color:azure  \">\n"
                        + "                            <div class=\"col-md-4\">\n"
                        + "                                <img src=\"Blog-ppt.gif\" class=\"img-fluid rounded-start h-100 w-100\" alt=\"...\">\n"
                        + "                            </div>\n"
                        + "                            <div class=\"col-md-8\">\n"
                        + "                                <div class=\"card-body border  border-5\">\n"
                        + "                                    <h5 class=\"card-title text-primary fw-bolder  \" >" + Name  + "</h5><hr class=\"text-primary \">\n"
                           + "<p class=\"card-text h3 fw-bolder\" style=\"text-align: left;color:orange\" >" + Title  + "</p>\n"
                                + "<h5 class=\" \" style=\"text-align: left\">" + Category + "</h5><br>"
                        + "                                    <p class=\"card-text h6\"  style=\"text-align: left\">" + dsc + "<a href=\"detail?uname=" + Name + "\" ><p class=\"  \"  style=\"text-align: left\";\">Read More</p></a>" + "</p>\n"
                        + "<p class=\"card-text\"  style=\"text-align: left\"><small class=\"text-muted\"  style=\"text-align: left\">Last updated 3 mins ago</small></p>\n"
                        
                        + "<a href=\"edit?uname=" + Name + "\"><button class=\"btn btn-outline-danger m-2\">EDIT</button></a>"
                        
                        + "<a href=\"delete?uname=" + Name + "\" ><button class=\"btn btn-outline-warning m-2\"> Delete</button></a>"
                     
                        + "                                </div>\n"
                        + "                            </div>\n"
                        + "                        </div>\n"
                        + "                    </div>\n <script>\n"
                        + "        AOS.init({\n"
                        + "            offset: 300,\n"
                        + "            duration: 1000,\n"
                        + "        });\n"
                        + "        </script>"
                        + "                </center>  <hr class=\"text-success\"><hr class=\"text-primary\"><hr class=\"text-danger\">"
                        + "<br>"
                        + "<br>"
                );
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Blogmgmt.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
}



