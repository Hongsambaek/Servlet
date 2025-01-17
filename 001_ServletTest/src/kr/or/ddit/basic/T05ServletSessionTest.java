package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class T05ServletSessionTest extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	/*
	 	세션(httpSession) 객체에 대하여...
	 	
	 		1. 세션 객체를 이용하여 사용자(웹브라우저)별로 구분하여 정보를 관리할 수 있다.(세션ID 이용)
	 		2. 쿠키를 사용 할 때보다 보안이 향상된다.(정보가 서버에 저장되기 때문에...)
	 		
	 		3. 세션객체를 가져오는(생성하는) 방법
	 		   HttpSession session = req.getSession(boolean값);
	 		   boolean값 : true인 경우 → 세션객체가 존재하지 않으면 새로 생성하여 반환한다.
	 				   	   false 인 경우 → 세션객체가 존재하지 않으면 null을 리턴한다.
	 		
	 		4. 세션객체를 처리 하는 방법
	 		   1) invalidate() 메소드 이용하기
	 	 	   2) setMaxInactiveInterval(int interval) 메소드 이용하기 (초단위)
	 		   3) web.xml 파일에 <session-config> 설정 이용하기 (분단위)
	 */
		
				// 세션객체를 가져오는데 없으면 새로 생성한다.
				HttpSession httpSession = req.getSession(true);
				
				// 세션 생성시간 가져오기
				Date createTime = new Date(httpSession.getCreationTime());
				
				// 세션에 마지막 접근한 시간 가져오기
				Date lastAccessTime = new Date(httpSession.getLastAccessedTime());
				
				String title = "재방문을 환영합니다.";
				int visitCnt = 0; // 방문 횟수
				String userId = "pc27"; // 사용자 ID
				
				if(httpSession.isNew()) { // 새로 만들어진 세션객체인지 확인...
					title = "처음 방문을 환영합니다.";
					httpSession.setAttribute("userId", userId);
				}else {
					visitCnt = (Integer) httpSession.getAttribute("visitCnt");
					visitCnt++;
					userId = (String) httpSession.getAttribute("userId");
				}
				
				httpSession.setAttribute("visitCnt", visitCnt);
				
//				httpSession.invalidate(); // session 무효화
				
				httpSession.setMaxInactiveInterval(30); // 초 단위
				
				////////////////////////////////////////////////////////////////////
				
				resp.setCharacterEncoding("UTF-8");
				resp.setContentType("text/html");
				
				PrintWriter out = resp.getWriter();
				
				out.print("<!DOC_TYPE html><html><head><title>" + title + "</title></head>"
						+ "<body><h1 align=\"center\">" + title + "<h1>"
						+ "<h2 align=\"center\">세션정보</h2>"
						+ "<table border=\"1\" align=\"center\">"
						+ "<tr bgcolor=\"orange\"><th>구분</th><th>값</th></tr>"
						+ "<tr><td>세션ID</td><td>" + httpSession.getId() + "</td></tr>"
						+ "<tr><td>생성시간</td><td>" + createTime + "</td></tr>"
						+ "<tr><td>마지막 접근시간</td><td>" + lastAccessTime + "</td></tr>"
						+ "<tr><td>사용자ID</td><td>" + userId + "</td></tr>"
						+ "<tr><td>방문횟수</td><td>" + visitCnt + "</td></tr>"
						+ "</table></body></html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doGet(req,resp);
	}	
}
