package ua.kiev.prog;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/getUsers")
public class GetUsersServlet extends HttpServlet {
    private UsersList usersList = UsersList.getInstance();
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        HttpSession session = request.getSession(false);
        if(session!= null){
            String users = usersList.toJSON();
            if(users != null){
                OutputStream os = response.getOutputStream();
                os.write(users.getBytes());
            }
        } else {
            response.setStatus(400);
        }
    }
}
