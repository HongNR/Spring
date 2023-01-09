<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value=" "/>
</jsp:include>
<style>
    div#memo-container{width:60%; margin:0 auto;}
</style>
        
<div id="memo-container">
    <form action="${path }/memo/insertMemo.do" class="form-inline" method="post">
        <input type="text" class="form-control col-sm-6" name="memo" placeholder="메모" required/>&nbsp;
        <input type="password" class="form-control col-sm-2" name="password" maxlength="4" placeholder="비밀번호" required/>&nbsp;
        <button class="btn btn-outline-success" type="submit" >저장</button>
    </form>
    <br />
        <!-- 메모목록 -->
  	<table class="table">
	    <tr>
	        <th scope="col">번호</th>
	        <th scope="col">메모</th>
	        <th scope="col">날짜</th>
	        <th scope="col">삭제</th>
	    </tr>
		<c:if test="${not empty memolist }">
	    	<c:forEach var="memo" items="${memolist }">
		 		<tr>
		     		<td><c:out value="${memo.memoNo }"/></td>
		     		<td><c:out value="${memo.memo }"/></td>
		     		<td><c:out value="${memo.memoDate }"/></td>
		     		<td><button class="btn btn-outline-danger">삭제</button></td>
		     	</tr>
	    	</c:forEach>
	    </c:if>
	   	<c:if test="${empty memolist }">
	    	<tr>
	    		<td colspan="4">작성된 메모가 없습니다.</td>
	    	</tr>
	 	</c:if>
	</table>
	<div id="pageBar">
		${pageBar }
	</div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>