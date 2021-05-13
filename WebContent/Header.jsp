<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.Member" %>
<jsp:useBean id="member"
			scope="session"
			class="vo.Member"/>

  <!-- ======= Header ======= -->
  <header id="header" class="fixed-top">
    <div class="container d-flex align-items-center">

      <h1 class="logo mr-auto"><a href="index.jsp">BOOGIE</a></h1>
      <!-- Uncomment below if you prefer to use an image logo -->
      <!-- <a href="index.html" class="logo mr-auto"><img src="assets/img/logo.png" alt="" class="img-fluid"></a>-->

      <nav class="nav-menu d-none d-lg-block">
        <ul>
          <li class="active"><a href="index.jsp">Home</a></li>
          <li><a href="portfolio-details.jsp">자유게시판(지금은 댓글)</a></li>
          <li><a href="list2">영화리뷰</a></li>
          <li class="drop-down"><a href="">펼치기</a>
            <ul>
            <%if(request.getSession(false).getAttribute("loginChecker")==null){ %>
              <li><a href="./auth/login">로그인</a></li>
              <li><a href="./member/add">회원가입</a></li>
            <% } %>  
              <li><a href="./auth/logout">로그아웃</a></li>
              <li class="drop-down"><a href="mypage.jsp">마이페이지</a>
                <ul>
                  <li><a href="./member/update">회원정보 수정</a></li>
                  <li><a href="#">스크랩(좋아요)</a></li>
                  <li><a href="#">내가 쓴 글</a></li>
                </ul>
              </li>
            </ul>
          </li>

        </ul>
      </nav><!-- .nav-menu -->

      <a href="write.jsp" class="get-started-btn">글쓰기</a>

    </div>
  </header><!-- End Header -->
