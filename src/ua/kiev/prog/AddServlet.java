package ua.kiev.prog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add")
public class AddServlet extends HttpServlet {

	private MessageList msgList = MessageList.getInstance();
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(req.getInputStream()));
		Message msg = Message.fromJSON(bf.readLine());
		if(msg!=null){
			msgList.add(msg);
		} else {
			res.setStatus(400);
		}
	}
}
