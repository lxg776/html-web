<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<%@include file="/common/taglibs.jsp"%>
<%
	response.setStatus(200);
%>
<!doctype html>
<html lang="en">
<head>
<title>401 - <spring:message code="core.401.failed" text="认证失败" /></title>
<style type="text/css">
#targetContentDiv {
	background-color: #b8b8b8;
	padding: 70px 0 80px;
	text-align: center;
}

#targetContentDiv h1 {
	font-size: 120px;
	letter-spacing: -2px;
	line-height: 1;
}

#targetContentDiv p {
	font-size: 40px;
	font-weight: 200;
	line-height: 1.25;
	font-weight: bold;
	padding: 10px;
}

#targetContentDiv li {
	display: inline;
	list-style: none outside none;
}
</style>
</head>

<body>

	<div id="targetContentDiv">
		<div class="container">
			<h1>401</h1>
			<p>
				<spring:message code="core.401.failed" text="认证失败" />
			</p>
			<ul>
				没有操作权限
				<li><a class="btn btn-primary" href="javascript:void(0);"
					onclick="history.back()">返回</a></li>
			</ul>
		</div>
	</div>

</body>

</html>
