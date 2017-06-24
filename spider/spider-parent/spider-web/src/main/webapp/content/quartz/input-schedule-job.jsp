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
			1
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
					<li><a href="#">新闻栏目</a></li>
					<i class="icon-angle-right"></i>
					</li>
					<li><a href="#">添加</a></li>
				</ul>

				<!-- 新闻栏目 -->
				<div class="box-content">
					<form action="save-schedule-job" method="post"
						class="form-horizontal">
						<input type="hidden" name="scheduleJobId"
							value="${scheduleJobVo.scheduleJobId}"> <input
							type="hidden" name="keywords" value="${scheduleJobVo.keywords}">

						<div class="form-group">
							<label for="jobName" class="col-sm-2 control-label">任务名称</label>
							<div class="col-sm-8">
								<input type="text" name="jobName"
									value="${scheduleJobVo.jobName}" class="form-control">
							</div>
						</div>

						<div class="form-group">
							<label for="jobGroup" class="col-sm-2 control-label">任务分组</label>
							<div class="col-sm-8">
								<input type="text" name="jobGroup"
									value="${scheduleJobVo.jobGroup}" class="form-control">
							</div>
						</div>

						<div class="form-group">
							<label for="aliasName" class="col-sm-2 control-label">任务别名</label>
							<div class="col-sm-8">
								<input type="text" name="aliasName"
									value="${scheduleJobVo.aliasName}" class="form-control">
							</div>
						</div>

						<div class="form-group">
							<label for="cronExpression" class="col-sm-2 control-label">时间表达式</label>
							<div class="col-sm-8">
								<input type="text" name="cronExpression"
									value="${scheduleJobVo.cronExpression}" class="form-control">
							</div>
						</div>

					   <div class="form-group">
							<label for="executor" class="col-sm-2 control-label">执行者</label>
							<div class="col-sm-8">
								<input type="text" name="executor"
									value="${scheduleJobVo.executor}" class="form-control">
							</div>
						</div>

						<div class="form-group">
							<label for="url" class="col-sm-2 control-label">任务执行url</label>
							<div class="col-sm-8">
								<input type="text" name="url" value="${scheduleJobVo.url}"
									class="form-control">
							</div>
						</div>

						<div class="form-group">
							<label for="description" class="col-sm-2 control-label">任务描述</label>
							<div class="col-sm-8">
								<input type="text" name="description"
									value="${scheduleJobVo.description}" class="form-control">
							</div>
						</div>

						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-primary">保存</button>
								<a href="list-schedule-job.shtml" class="btn btn-default"
									role="button">返回</a>
							</div>
						</div>

					</form>

				</div>

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
