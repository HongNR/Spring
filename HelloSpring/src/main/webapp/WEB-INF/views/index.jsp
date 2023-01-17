<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<!-- 
	헤더파일불러오기
	title값을 전달해서 출력해야함-> MainPage가 출력 
-->
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="MainPage"/>
</jsp:include>

<section id="content">
	<h2>Hello Spring</h2>
	<img src="${path}/resources/images/logo-spring.png"	id="center-image" alt="스프링로고">
	<button onclick="getMembers();">회원조회</button>
	<div id="membercontainer"></div>
	<button onclick="jsonTest();">회원가입</button>
	
	<script>
		function jsonTest(){
		//비동기식통신을 할 때 js가 기본제공하는 함수이용하기
		//fetch()함수를 제공함, 동기식 통신을 위해서 promise객체로 반환을 함
		//fetch(url주소, 옵션(전송)).then(response=>response.json()).then(data=>{로직});
		fetch("${path}/member/ajax/insert",{
			method:"post",//전송방법
			headers:{
				"Content-Type":"application/json"
			},//요청헤더설정
			body:JSON.stringify({userId:"test22",password:"1234",userName:"test22"})
			//전송데이터
		})
			.then(response=>{console.log(response); return response.json();})
			.then(data=>{//success callback
					console.log(data);
			});
		};
	
	
		const getMembers=()=>{
			$.get("${path}/member/memberList.do",data=>{
				console.log(data);
				const table=$("<table>");
				const header=$("<tr>");
				header.append("<th>아이디</th>")
					.append("<th>이름</th>")
					.append("<th>나이</th>")
					.append("<th>성별</th>")
					.append("<th>전화번호</th>")
					.append("<th>이메일</th>")
					.append("<th>주소</th>")
					.append("<th>취미</th>")
					.append("<th>가입일</th>");
				table.append(header);
				
				data.forEach(e=>{
					let tr=$("<tr>");
					let userId=$("<td>").text(e.userId);
					let name=$("<td>").text(e.name);
					let age=$("<td>").text(e.age);
					let gender=$("<td>").text(e.gender);
					let phone=$("<td>").text(e.phone);
					let email=$("<td>").text(e.email);
					let address=$("<td>").text(e.address);
					let hobby=$("<td>").text(e.hobby.toString());
					let enrolldate=$("<td>").text(new Date(e.enrollDate));
					
					tr.append(userId).append(name).append(age).append(gender)
					.append(phone).append(email).append(address)
					.append(hobby).append(enrolldate);
					
					table.append(tr);
				});//테이블에 값 넣는 반복문
				$("#membercontainer").html(table);//div에 테이블 넣기
			});//ajax
		}
	</script>
	<h1>jpa테스트하기</h1>
	<h2><a href="${path }/jpa/insert">jpa회원 저장하기</a></h2>
		<form action="${path }/jpa/insert">
			<input type="text" name="userId">
			<input type="submit" value="가입">
		</form>
	
	<h2><a href="${path }/jpa/members">jpa전체회원 조회하기</a></h2>
	<h2><a href="${path }/jpa/member?id=1">jpa회원 조회하기</a></h2>
	<h2><a href="${path }/jpa/member/search?height=180.5">jpa회원 조건 조회하기</a></h2>
	<h2><a href="${path }/jpa/update?no=1&age=20&height=190.5&intro=새해 복 많이 받으세요">
		jpa회원 수정하기</a></h2>
	<h2><a href="${path }/jpa/delete?no=1">jpa회원 삭제하기</a></h2>
	<h2><a href="${path }/jpa/insertMember">jpa회원 다대일 클래스 저장</a></h2>
	<h2><a href="${path }/jpa/major?no=1">jpa 학과 조회하기</a></h2>
	<h1>다대다관계</h1>
	<h2><a href="${path }/jpa/insertStudentClub">jpa 다대다 저장하기</a></h2>
	<h2><a href="${path }/jpa/selectStudent?no=6">jpa 다대다 학생 조회하기</a></h2>
	<h2><a href="${path }/jpa/selectClub?no=4">jpa 다대다 학과 조회하기</a></h2>
	
</section>

<!-- 푸터파일 불러오기 -->
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>