package ua.kiev.prog;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/login")
public class Login extends HttpServlet {
    static Map<String, String> users = new HashMap<String, String>();
    private UsersList usersList = UsersList.getInstance();

    public void doPost(HttpServletResponse res, HttpServletRequest req) throws ServletException, IOException{
        users.put("admin", "123456");
        users.put("user1", "qwerty");
        users.put("user2", "zxcvbn");

        try(BufferedReader bf = new BufferedReader(new InputStreamReader(req.getInputStream()))){
            User user = User.fromJSON(bf.readLine());
            boolean inList = usersList.getUsersList().contains(user);
            for(Map.Entry<String,String> entry: users.entrySet()){
                if(entry.getKey().equals(user.getLogin())&& entry.getValue().equals(user.getPassword())){
                    user.setStatus("online");
                    HttpSession session = req.getSession();
                    session.setAttribute("userLogin", user.getLogin());
                    session.setMaxInactiveInterval(60);
                    res.setStatus(200);
                    if(!inList){
                        usersList.add(user);
                        break;
                    }
                } else {
                    res.setStatus(400);
                }
            }

        }
    }
}
