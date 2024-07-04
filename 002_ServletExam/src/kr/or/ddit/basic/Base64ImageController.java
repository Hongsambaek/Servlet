package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/base64Img.do")
public class Base64ImageController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream("D:\\A_TeachingMaterial\\03_HighJava\\workspace\\002_ServletExam\\WebContent\\images\\모코코.png"));
		
		int data = 0;
		
		while((data = bis.read()) != -1) {
			baos.write(data);
		}
		
		bis.close(); // 자원반납
		
		String base64Img = Base64.getEncoder().encodeToString(baos.toByteArray());
		
		req.setAttribute("base64Img", base64Img);
		
		req.getRequestDispatcher("/base64_img.jsp").forward(req, resp);
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doGet(req,resp);
		
	}
	
}
