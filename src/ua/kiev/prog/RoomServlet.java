package ua.kiev.prog;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class RoomServlet extends HttpServlet {
    private static List<Room> roomList = new ArrayList<Room>();
    private UsersList usersList = UsersList.getInstance();
    private MessageList msgList = MessageList.getInstance();

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        HttpSession session = req.getSession(false);
        BufferedReader bf = new BufferedReader(new InputStreamReader(req.getInputStream()));
        Message msg = Message.fromJSON(bf.readLine());

        if((session!=null)&&(msg!=null)){
            String r = msg.getTo().substring(0,3);
            if(r.equals("new")){
                Room room = new Room();
                room.setName(msg.getTo().substring(4));
                String[] listLogins = msg.getText().split(" ");
                for(int i = 0; i<listLogins.length; i++){
                    User user = usersList.getLogin(listLogins[i]);
                    if(user!=null){
                        room.usersList.add(user);
                    }
                }
                roomList.add(room);
            } else {
                for (Room room : roomList){
                    if(room.getName().equals(msg.getTo())){
                        if(room.findUser(msg.getFrom())){
                            for(User user : room.usersList){
                                Message newMsg = new Message();
                                newMsg.setPrivate(true);
                                newMsg.setTo(user.getLogin());
                                newMsg.setFrom(msg.getFrom());
                                newMsg.getText();
                                msgList.add(newMsg);
                            }
                        }
                    }
                }
            }
        }
    }

}
