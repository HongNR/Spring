<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value=" "/>
</jsp:include>
<section id="board-container" class="container">
	<div id="board-container">
		<form name="boardFrm" action=""  >
		    <input type="text" class="form-control" placeholder="제목" name="boardTitle" id="boardTitle" required>
		    <input type="text" class="form-control" placeholder="아이디 (4글자이상)" name="boardWriter" value="${memberLoggedIn.userId}" readonly required>
		    <div class="input-group mb-3" style="padding:0px;">
		        <div class="input-group-prepend" style="padding:0px;">
		            <span class="input-group-text">첨부파일1</span>
		        </div>
		        <div class="custom-file">
		            <input type="file" class="custom-file-input" name="upFile" id="upFile1">
		            <label class="custom-file-label" for="upFile1">파일을 선택하세요</label>
		        </div>
		    </div>
		    <textarea class="form-control" name="boardContent" placeholder="내용" required></textarea>
		    <br />
		    <input type="submit" class="btn btn-outline-success" value="저장" >
		</form>
	</div>
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>