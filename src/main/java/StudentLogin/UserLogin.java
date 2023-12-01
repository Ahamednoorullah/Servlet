package StudentLogin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class UserLogin extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
		
		PrintWriter pw = resp.getWriter();
		String userName = req.getParameter("userName");
		String password = req.getParameter("userpassword");
		System.out.println(userName);
		try {
			if (useradded(userName,password)) {
				pw.println("User added Successfully");
			} else {
	            pw.println("Something Wrong..");
			}

		} catch (Exception e) {
			pw.println("Something went Wrong..");
			e.printStackTrace();
		}
			}

	private boolean useradded(String userName, String password) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chainsys?user=root&password=irfani");
		Statement stmt = con.createStatement();
		stmt.execute("insert into studentlogin values('"+userName+"','"+password+"');");
		System.out.println(userName + " "+ password);
		int nori = stmt.getUpdateCount();
		return nori == 1;

		
	}

}
