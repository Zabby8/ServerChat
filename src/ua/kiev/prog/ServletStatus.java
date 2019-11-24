package ua.kiev.prog;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ServletStatus extends HttpServlet {
    private UsersList usersList = UsersList.getInstance();
    private MessageList msgList = MessageList.getInstance();

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String userName = req.getParameter("userName");
        HttpSession session = req.getSession(false);
        if(session != null){
            User user = usersList.getLogin(userName);
            if(user != null){
                res.setStatus(200);
            } else {
                res.setStatus(202);
            }
        } else {
            res.setStatus(400);
        }
    }
}
