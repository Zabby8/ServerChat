package ua.kiev.prog;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogOut extends HttpServlet {
    private UsersList usersList = UsersList.getInstance();
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        HttpSession session = req.getSession(false);
        if(session !=null){
            String userCooke = String.valueOf(session.getAttribute("userLogin"));
            for(User user: usersList.getUsersList()){
                if (userCooke.equals(user.getLogin())){
                    usersList.getUsersList().remove(user);
                    res.setStatus(200);
                }
            }
            session.invalidate();
        }
    }
}
