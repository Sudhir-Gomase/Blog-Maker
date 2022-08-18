
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Detail extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        String varname = req.getParameter("uname");

        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/blogdb", "root", "hero");
            PreparedStatement stmt = (PreparedStatement) con.prepareStatement("select * from blogtb where Name=?");
            stmt.setString(1, varname);

            ResultSet resultset = stmt.executeQuery();
            resultset.next();
            String Name = resultset.getString("Name");
            String Title = resultset.getString("Title");

            String Description = resultset.getString("Description");
            String dsc = Description;
            String Category = resultset.getString("Category");

            PreparedStatement stmt3 = con.prepareStatement("select count(*) from data1 where l_title=?");
            stmt3.setString(1, Title);

            ResultSet rs = stmt3.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            out.print("<html>  \n"
                    + "    <head>  \n"
                    + "        <title>Body Tag</title>  \n"
                    + "        <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC\" crossorigin=\"anonymous\">\n"
                    + "        <script src=\"https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js\" integrity=\"sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p\" crossorigin=\"anonymous\"></script>\n"
                    + "        <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js\" integrity=\"sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF\" crossorigin=\"anonymous\"></script>\n"
                    + "        <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM\" crossorigin=\"anonymous\"></script>\n"
                    + "        <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css\">\n"
                    + "    </head>  \n"
                    + "    <body >  \n"
                    + "        <div class=\" bg-info text-center pb-2 pt-2\">\n"
                    + "            <h3 class=\"text-center\" style=\"text-align: center\"> You Can Read Blog Here..</h3>\n"
                    + "        </div>\n"
                    + "\n"
                    + "        <center> <div class=\"pt-5  \">\n"
                    + "            <div class=\"card border-success mb-3 \" style=\"max-width: 40rem;\">\n"
                    + "                <div class=\"card-header  text-primary  bg-dark text-white border-success fw-bolder h3\">" + Title + "</div>\n"
                    + "                <div class=\"card-body text-success\">\n"
                    + "                    <div class=\"row\">\n"
                    + "             <div class=\"col-md-1 h4 \">\n"
                    + "             <a href=\"\"><i class=\"fa fa-facebook-official \" style=\"color:blue;\" ></i></a>\n"
                    + "             </div>\n"
                    + "                  <div class=\"col-md-10\">\n"
                    + "                   <h5 class=\"card-title \">" + Name + "</h5>\n"
                    + "                    <p class=\"card-title text-dark\">last updated on 2 min ago<p>\n"
                    + "                    </div>\n"
                    + "                        <div class=\"col-md-1 h4 px-0\"   >\n"
                    + "                            <a href=\"\"><i class=\"fa fa-instagram\" style=\"color: darkorchid\" aria-hidden=\"true\"></i></a>\n"
                    + "\n"
                    + "                        </div>\n"
                    + "                    </div>\n"
                    + "                </div>\n"
                    + "                <div class=\"text-center m-3\">\n"
                    + "                    <img src=\"n.jpg\" class=\"img-fluid text-center\" alt=\"...\">\n"
                    + "                </div>\n"
                    + "\n"
                    + "                <p class=\"card-text m-2 fw-bolder  \" style=\"text-align: justify\">" + Description + "</p>\n"
                    + "\n"
                    + "               <a href=\"Like?uname=" + Name + "&title=" + Title + " \"><button class=\"btn btn-outline-danger m-2 \">" + count + "</button></a><i class=\"fa fa-thumbs-up text-success\" style=\"font-size: 30;\" aria-hidden=\"true\"></i>"
                    + "            </div>    \n"
                    + "        </div> </center> \n"
                    + "\n"
                    + "\n"
                    + "    </body>  \n"
                    + "</html>  \n"
                    + "<head>");

        } catch (Exception ex) {
            //out.println(ex);
            Logger.getLogger(Blogmgmt.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
//
