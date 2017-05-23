<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri = "http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> Test </title>
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.0.0/css/font-awesome.min.css">
<meta name="description"
	content="A Bootstrap 4 admin dashboard theme that will get you started. The sidebar toggles off-canvas on smaller screens. This example also include large stat blocks, modal and cards. The top navbar is controlled by a separate hamburger toggle button." />
<meta name="generator" content="Codeply">
<meta name="viewport" content="width=device-width, initial-scale=1">

<script src="<c:url value="/resources/jquery-3.1.1.min.js" />"></script>
<script src="<c:url value="/resources/jquery.bootpag.min.js" />"></script>
<link
	href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css"
	rel="stylesheet">
<script
	src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" />
<link rel="stylesheet" href="../../resources/css/styles.css" />
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="../../resources/css/main.css" />
<script> </script>
<style>
th {
	text-align: center;
}

.td {
	text-align: center;
}
.conteiner {
	width: 1250px;
	margin: 0px auto;
	text-align: center;
}

</style>
</head>
<body>
<nav
		class="navbar navbar-fixed-top navbar-toggleable-sm navbar-inverse bg-primary mb-3">
		<button class="navbar-toggler navbar-toggler-right" type="button"
			data-toggle="collapse" data-target="#collapsingNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="flex-row d-flex">
			<a class="navbar-brand mb-1" href="../../uc/Main/main"
				style="font-size: 40px;">Dodo</a>
			<button type="button" class="hidden-md-up navbar-toggler"
				data-toggle="offcanvas" title="Toggle responsive left sidebar">
				<span class="navbar-toggler-icon"></span>
			</button>
		</div>
		<div class="navbar-collapse collapse" id="collapsingNavbar">


			<!-- 게스트 전용 아이콘 표시 -->
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link" href=""
					style="color: Black;"><sec:authorize
							access="!isAuthenticated()">게스트</sec:authorize></a></li>
				<li class="nav-item"><sec:authorize access="!isAuthenticated()">
						<a class="nav-link" href="../../uc/User/loginform"> <i
							class="fa fa-sign-in fa-3x"
							style="color: white; margin-right: 15px;" aria-hidden="true"></i></a>
					</sec:authorize></li>
				<li class="nav-item"><sec:authorize access="!isAuthenticated()">
						<a class="nav-link" href="../../uc/User/userjoin"> <i
							class="fa fa-user fa-3x"
							style="color: white; margin-right: 15px;" aria-hidden="true"></i></a>
					</sec:authorize></li>


				<!-- 유저전용 아이콘 표시 -->
				<sec:authorize access="hasAuthority('USER')">
					<li class="nav-item"><a class="nav-link" href=""
						style="color: white;"><sec:authentication property="name" />님</a>
						<%-- <a href="<c:url value='/BP/User/logout' />">로그아웃</a> --%></li>
					<li class="nav-item"><a class="nav-link"
						href="../../bc/main/write"> <i class="fa fa-pencil fa-3x"
							aria-hidden="true" style="color: white; margin-right: 15px;"></i></a>
					</li>
					<li class="nav-item"><a class="nav-link" href=""
						data-toggle="dropdown"> <i id="pop"
							class="fa fa-list-alt fa-3x" aria-hidden="true"
							style="color: white; margin-right: 15px;"></i>
					</a>
						<ul class="dropdown-menu dropdown-user" style="left: 1270px;">
							<c:forEach var="c" items="${cList}">
								<li><a href="../../bc/main/boardList?page=1&cat=${c.hobby}">${c.hobby}</a></li>
							</c:forEach>
							<li class="divider"></li>
							<li><a href="/Dodo/bc/main/writeList?id=${USERID}">내가 쓴 글</a></li>
						</ul>
					<li class="nav-item"><a class="nav-link"
						href="../../mc/Main/setup"> <i class="fa fa-cogs fa-3x"
							style="color: white; margin-right: 15px;" aria-hidden="true"></i></a>
					</li>
					<li class="nav-item"><a class="nav-link"
						href="../../uc/User/logout"> <i class="fa fa-sign-out fa-3x"
							style="color: white; margin-right: 15px;" aria-hidden="true"></i></a>
					</li>
				</sec:authorize>
			</ul>
		</div>
	</nav>
<!-- /nav -->

<!-- 사이드바 시작 -->
<!-- main container -->
	<div class="container-fluid" id="main">
		<div class="row row-offcanvas row-offcanvas-left">
			<div class="col-md-3 col-lg-2 sidebar-offcanvas" id="sidebar"
				role="navigation">
				<ul class="nav flex-column pl-1">
					<!-- 사이드바 검색기능 -->
					<li class="nav-item">
						<form action="../../bc/main/ssearch" method="post" id="frm">
							<input type="hidden" class="email" name="${_csrf.parameterName }"
								value="${_csrf.token }"> <input type="search"
								id="search" name="searchI"> <input type="hidden"
								name="searchS" value="title"> <input type="hidden"
								name="page" value="1"> <a href="#"
								onclick="$('#frm').submit();"> <i class="fa fa-search fa-2x"
								style="color: #014c8c; margin-left: 10px;" aria-hidden="true"></i>
							</a>
						</form>
					</li>
					<!-- 사이드바 검색기능 -->
					<li class="nav-item"><a class="nav-link" href="#submenu1"
						data-toggle="collapse" data-target="#submenu1">게시판 리스트▾</a>
						<ul class="list-unstyled flex-column pl-3 collapse" id="submenu1"
							aria-expanded="false">
							<li class="nav-item"><a class="nav-link"
								href="../../bc/main/boardList?cat=Travel&page=1">여행</a></li>
							<li class="nav-item"><a class="nav-link"
								href="../../bc/main/boardList?cat=Movie&page=1">영화</a></li>
							<li class="nav-item"><a class="nav-link"
								href="../../bc/main/boardList?cat=Fashion&page=1">패션</a></li>
							<li class="nav-item"><a class="nav-link"
								href="../../bc/main/boardList?cat=Love&page=1">연애</a></li>
							<li class="nav-item"><a class="nav-link"
								href="../../bc/main/boardList?cat=IT&page=1">IT</a></li>
							<li class="nav-item"><a class="nav-link"
								href="../../bc/main/boardList?cat=Sports&page=1">스포츠</a></li>
							<li class="nav-item"><a class="nav-link"
								href="../../bc/main/boardList?cat=Music&page=1">음악</a></li>
							<li class="nav-item"><a class="nav-link"
								href="../../bc/main/boardList?cat=Game&page=1">게임</a></li>
						</ul></li>
				</ul>
			</div>


			<div class="col-md-9 col-lg-10 main">
				<div class="card"
					style="margin-right: 1px; margin-left: 0px; height: 200px;">
					<div class="card-block">그림</div>
				</div>
				<div class="conteiner">
						<hr>
						<br>
				<h3 style ="color:red;">
				   	최신 글 20개만 출력 합니다. 			
				</h3>
				<br>
						<table id="table" class="table table-condensed">
							<thead>
								<tr>
									<th class="th1">글번호</th>
									<th class="th1">카테고리</th>
									<th class="th1">작성자</th>
									<th class="titleList">글 제 목</th>
									<th class="th1">추천</th>
									<th class="th1">조회</th>
									<th class="th1">첨부</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="list" items="${wList}">
									<tr>
										<td>${list.num}</td>
										<td>${list.cat}</td>

										<td>${list.author}</td>

										<!-- 댓글 -->
										<td class="td"><a
											href="read?num=${list.num}&readcnt=${list.readcnt}">${list.title}</a>
											<c:forEach var="comment" items="${comment}">
												<c:if test="${comment.ref == list.num}">
													<span class="badge"
														style="margin-left: 5px; background-color: gray;">${comment.cnt}</span>
												</c:if>
											</c:forEach></td>

										<td>${list.goodcnt}</td>
										<td>${list.readcnt}</td>
										<td><c:if test="${list.fenabled==1}">
												<i class="material-icons" style="font-size: 15px;">attachment</i>
											</c:if></td>
									</tr>
								</c:forEach>
						</table>
						</tbody>
					</div>
				</div>
			</div>
		</div>
		<hr>
<!-- main container -->
		<script
			src="//cdnjs.cloudflare.com/ajax/libs/tether/1.2.0/js/tether.min.js"></script>
		<script
			src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>
		<script src="../../resources/js/scripts.js"></script>
</body>
</html>
