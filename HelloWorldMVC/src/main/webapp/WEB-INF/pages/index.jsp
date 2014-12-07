<html>
	<header>
		<title>My First MVC Spring website</title>
		<link href="${pageContext.request.contextPath}/resources/style.css" rel="stylesheet" />
	</header>
	<body>
		<div class="content">
			<%@include file="header.jsp"%>
			<%@include file="new.jsp"%>

			<% int option = (Integer)request.getAttribute("option");
				if( option!=0){ %>
			<%@include file="menu.jsp"%>
			<%} %>

			<%@include file="left.jsp"%>
			<%@include file="right.jsp"%>
			<div class="closeFloat"></div>
			<%@include file="footer.jsp"%>
		</div>
	</body>
</html>