<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 
	데이터URI 스킴(data URI Scheme) 이란?
	- 외부 자원(이미지 등)을 문서(HTML, CSS, SVG 등)에 인라인(inline) 방식의 데이터로 포함시킬 수
	    있는 방법을 제공하기 위한 통합 자원 식별자(URI) 스킴(표현법)
	    
	형식) data:[<media type>][;base64],<data>


 --%>
 
<%

	String base64Img = (String) request.getAttribute("base64Img");

%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Base64 인코딩된 이미지 데이터 사용하기</title>
</head>
<body>
<img alt="Ground" src="data:image/jpg;base64,<%=base64Img %>">
</body>
</html>