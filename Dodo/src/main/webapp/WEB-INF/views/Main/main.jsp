<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Dodo 커뮤니티 사이트</title>
<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
	rel="stylesheet">
<meta name="description"
	content="A Bootstrap 4 admin dashboard theme that will get you started. The sidebar 
    toggles off-canvas on smaller screens. This example also include large stat blocks, modal and cards. 
    The top navbar is controlled by a separate hamburger toggle button." />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="generator" content="Codeply">
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" />
<link rel="stylesheet" href="../../resources/css/styles.css" />
<link rel="stylesheet" href="../../resources/css/main.css" />
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<style>
body p {
	overflow: hidden;
	text-overflow: ellipsis; /*말 줄임표*/
	white-space: nowrap;
	width: 510px;
	height: 20px;
}

.card-text1 {
	overflow: hidden;
	text-overflow: ellipsis; /*말 줄임표*/
	white-space: nowrap;
	width: 330px;
	height: 20px;
}

h2 {
	text-align: center;
}
</style>
</head>

<body>
<!-- nav -->
	<nav
		class="navbar navbar-fixed-top navbar-toggleable-sm navbar-inverse bg-primary mb-3">
		<button class="navbar-toggler navbar-toggler-right" type="button"
			data-toggle="collapse" data-target="#collapsingNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="flex-row d-flex">
			<a class="navbar-brand mb-1" href="main" style="font-size: 40px;">Dodo</a>
			<button type="button" class="hidden-md-up navbar-toggler"
				data-toggle="offcanvas" title="Toggle responsive left sidebar">
				<span class="navbar-toggler-icon"></span>
			</button>
		</div>


			<!-- 게스트 아이콘 표시 -->
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link" href=""
					style="color: Black;"><sec:authorize
							access="!isAuthenticated()">게스트</sec:authorize></a></li>
				<li class="nav-item"><sec:authorize access="!isAuthenticated()">
						<a class="nav-link" href="../User/loginform"> <i
							class="fa fa-sign-in fa-3x"
							style="color: white; margin-right: 15px;" aria-hidden="true"></i></a>
					</sec:authorize></li>
				<li class="nav-item"><sec:authorize access="!isAuthenticated()">
						<a class="nav-link" href="../User/userjoin"> <i
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
	</nav>
<!-- nav -->


	<!-- 사이드바 시작 -->
	<!--  container -->
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
					<!-- /사이드바 검색기능 -->
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
								href="../../bc/main/boardList?cat=Sprots&page=1">스포츠</a></li>
							<li class="nav-item"><a class="nav-link"
								href="../../bc/main/boardList?cat=Music&page=1">음악</a></li>
							<li class="nav-item"><a class="nav-link"
								href="../../bc/main/boardList?cat=Game&page=1">게임</a></li>
						</ul></li>
			</div>


			<div class="col-md-9 col-lg-10 main">

				<div class="row mb-3">
					<div class="col-lg-6" style="padding-right: 5px;">
						<div class="card card-default card-block">
							<ul id="tabsJustified" class="nav nav-tabs nav-justified">
								<li class="nav-item"><a class="nav-link active" href=""
									data-target="#tab1" data-toggle="tab">공지사항</a></li>
								<li class="nav-item"><a class="nav-link" href=""
									data-target="#tab2" data-toggle="tab">소개</a></li>
							</ul>
							<!--/tabs-->
							<br>
							<div id="tabsJustifiedContent" class="tab-content">
								<div class="tab-pane active" id="tab1">
									<div class="list-group">
										<c:forEach var="nList" items="${nList}" end="3">
											<a href="" class="list-group-item">
												<table id="tabl1">
													<tr>
														<td id="td1" style="width: 500px; text-align: left;">
														<a href="../../bc/main/nread?anum=${nList.anum}">＊${nList.atitle}</a></td>
														<td style="width: 100px; text-align: right; color: black;">★${nList.gm}</td>
													</tr>
												</table>
											</a>
										</c:forEach>
										<a href="../../bc/main/notice?page=1"
											class="btn btn-info btn-block">Read More</a>
									</div>
								</div>


								<div class="tab-pane" id="tab2">
									<div class="row">
										<div class="col-sm-7">
											<h4>Profile Section</h4>
											<p>Imagine creating this simple user profile inside a tab
												card.</p>
										</div>
										<div class="col-sm-5">
											<img src="//placehold.it/170"
												class="float-right img-responsive img-rounded">
										</div>
									</div>
									<hr>
									<a href="javascript:;" class="btn btn-info btn-block">Read
										More Profiles</a>
									<div class="spacer5"></div>
								</div>


								<div class="tab-pane" id="tab3">
								</div>
							</div>
							<!--/tabs content-->
						</div>
						<!--/card-->
					</div>
					<!--/col-->

					<div class="card"
						style="margin-right: 5px; margin-left: 0px; width: 610px;">
						<div class="card-block">
							<h4 class="card-title">추천 Best</h4>
							<p class="card-text">This card has supporting text below as a
								natural lead-in to additional content.</p>
							<p class="card-text">
								<small class="text-muted">Last updated 3 mins ago</small>
							</p>
						</div>
					</div>
				</div>
				<br>
				<hr>
				<br>
				<!-- 경계 -->

				<h2>추천 글 목록</h2>
				<br>
				<div class="card-columns mb-3">
					<%--    <c:set var="flist" value="${flist}" /> --%>
					<c:forEach var="glist" items="${glist}" varStatus="status">
						<a
							href="../../bc/main/read?num=${glist.num}&readcnt=${glist.readcnt}"
							class="a_board">
							<div class="card">
								<div class="imgsacle">
									<img class="card-img-top img-responsive" id="imglong"
										src="../../resources/images/${flist[status.count]}"
										alt="Card image cap">
								</div>
								<div class="card-block">
									<h4 class="card-title">${glist.title}</h4>
									<p class="card-text1">11${glist.contents}</p>
									<p class="card-text">
										<small class="text-muted"><span
											style="margin-right: 110px;">${glist.bdate}</span><span>추천수
												: ${glist.goodcnt}</span></small>
									</p>
								</div>
							</div>
						</a>
					</c:forEach>

				</div>
				<!--/card-columns-->

			</div>
			<!--/row-->
		</div>
	</div>
	<!--/container-->

	<BR>
	<hr>


	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	<script
		src="//cdnjs.cloudflare.com/ajax/libs/tether/1.2.0/js/tether.min.js"></script>
	<script
		src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>
	<script src="../../resources/js/scripts.js"></script>

</body>
</html>