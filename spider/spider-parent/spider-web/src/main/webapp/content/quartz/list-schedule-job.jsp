<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>

<!-- start: Meta -->
<%@include file="/common/meta.jsp"%>
<!-- end: Mobile Specific -->
<title>管理后台</title>
<%@include file="/common/css-link01.jsp"%>
<!-- start: CSS -->



<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
	  	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<link id="ie-style" href="css/ie.css" rel="stylesheet">
	<![endif]-->

<!--[if IE 9]>
		<link id="ie9style" href="css/ie9.css" rel="stylesheet">
	<![endif]-->

<!-- start: Favicon -->

<!-- end: Favicon -->

<style type="text/css">
.jumbotron-margin-top {
	margin-top: 95px;
}

.table-margin-top {
	margin-top: 15px;
}

.status-color {
	color: red;
	font-weight: bold;
}
</style>


</head>

<body>
	<!-- start: Header -->
	<%@ include file="/content/ui/m-navbar.jsp"%>
	<!-- start: Header -->

	<div class="container-fluid-full">
		<div class="row-fluid">

			<!-- start: Main Menu -->
			<%@ include file="/content/ui/m-left.jsp"%>
			<!-- end: Main Menu -->
			<noscript>
				<div class="alert alert-block span10">
					<h4 class="alert-heading">Warning!</h4>
					<p>
						You need to have <a href="" target="_blank">JavaScript</a> enabled
						to use this site.
					</p>
				</div>
			</noscript>

			<!-- start: Content -->
			<div id="content" class="span10">

				<ul class="breadcrumb">
					<li><i class="icon-home"></i> <a href="index.html">首页</a> <i
						class="icon-angle-right"></i></li>
					<li><a href="#">定时任务</a></li>
					<i class="icon-angle-right"></i>
					</li>
					<li><a href="#">列表</a></li>
				</ul>

				<!-- 任务列表 -->
				<a href="input-schedule-job" class="btn btn-success"
					role="button">添加任务</a>
				<table class="table table-bordered table-margin-top">
					<tr>
						<td>任务名称</td>
						<td>任务别名</td>
						<td>任务分组</td>
						<td>触发器</td>
						<td>任务状态</td>
						<td>时间表达式</td>
						<td>执行者</td>
						<td>任务执行url</td>
						<td>任务描述</td>
						<td>操作</td>
					</tr>
					<c:forEach var="item" items="${scheduleJobVoList}">

						<tr>
							<td>${item.jobName}</td>
							<td>${item.aliasName}</td>
							<td>${item.jobGroup}</td>
							<td>${item.jobTrigger}</td>
							<c:if test="${item.status=='PAUSED'}">
								<td class="status-color">${item.status}</td>
							</c:if>
							<c:if test="${item.status!='PAUSED'}">
								<td>${item.status}</td>
							</c:if>

							<td>${item.cronExpression}</td>
							<td>${item.executor}</td>
						
							<td>${item.url}</td>
							<td>${item.description}</td>
							<td>
								<!--<a href="input-schedule-job?scheduleJobId=${item.scheduleJobId}">修改</a>-->
								<a class="btn btn-danger" role="button"
								href="pause-schedule-job?scheduleJobId=${item.scheduleJobId}"><i
									class="fa fa-pause-circle-o" aria-hidden="true"></i>暂停</a> <a
								class="btn btn-danger" role="button"
								href="resume-schedule-job?scheduleJobId=${item.scheduleJobId}"><i
									class="fa fa-reply" aria-hidden="true"></i>恢复</a> <a
								class="btn btn-danger" role="button"
								href="run-once-schedule-job?scheduleJobId=${item.scheduleJobId}"><i
									class="fa fa-play" aria-hidden="true"></i>运行一次</a> <a
								class="btn btn-danger" role="button"
								href="input-schedule-job?scheduleJobId=${item.scheduleJobId}&keywords=delUpdate"><i
									class="fa fa-pencil" aria-hidden="true"></i>修改</a> <a
								class="btn btn-danger" role="button"
								href="delete-schedule-job?scheduleJobId=${item.scheduleJobId}"><i
									class="fa fa-trash" aria-hidden="true"></i>删除</a>
							</td>
						</tr>
					</c:forEach>

				</table>
				<p class="lead">运行中的任务</p>
				<table class="table table-bordered">
					<tr>
						<td>任务名称</td>
						<td>任务别名</td>
						<td>任务分组</td>
						<td>触发器</td>
						<td>任务状态</td>
						<td>时间表达式</td>
						<td>执行者</td>
						<td>任务执行url</td>
						<td>任务描述</td>
					</tr>
					<c:forEach var="item" items="${executingJobList}">
						<tr>
							<td>${item.jobName}</td>
							<td>${item.aliasName}</td>
							<td>${item.jobGroup}</td>
							<td>${item.jobTrigger}</td>
							<td>${item.status}</td>
							<td>${item.cronExpression}</td>
							<td>${item.executor}</td>
							<td>${item.url}</td>
							<td>${item.description}</td>
						</tr>
					</c:forEach>
				</table>

			</div>
		</div>
	</div>





	<div class="common-modal modal fade" id="common-Modal1" tabindex="-1"
		role="dialog" aria-hidden="true">
		<div class="modal-content">
			<ul class="list-inline item-details">
				<li><a href="http://sc.chinaz.com">Admin templates</a></li>
				<li><a href="http://sc.chinaz.com">Bootstrap themes</a></li>
			</ul>
		</div>
	</div>

	<div class="clearfix"></div>

	<footer>

		<p>
			<span style="text-align: left; float: left">Copyright &copy;
				2016.Company name All rights reserved.<a target="_blank"
				href="http://sc.chinaz.com/moban/">&#x7F51;&#x9875;&#x6A21;&#x677F;</a>
			</span>
		</p>
	</footer>
	<!-- start: JavaScript-->
	<%@ include file="/common/s.jsp"%>
</body>
</html>
