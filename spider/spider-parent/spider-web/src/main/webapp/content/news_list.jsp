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


<script type="text/javascript">
	var listUrl = "${ctx}" + "/news/newsList?pageNum=";
	function getDataList(pageNum) {
		window.location.href = listUrl + pageNum;
	}
</script>

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
					<li><a href="#">栏目列表</a></li>
				</ul>

				<!-- 新闻栏目 -->
				<div class="box span6" style="width: 1100px;">
					<div class="box-header">
						<h2>
							<i class="halflings-icon white align-justify"></i><span
								class="break"></span>新闻栏目
						</h2>
						<div class="box-icon">
							<a href="#" class="btn-setting" style="color: #FFF">添加栏目</a>

						</div>
					</div>
					<div class="box-content">
						<table class="table table-bordered table-striped table-condensed">
							<thead>
								<tr>
									<th style="text-align: center; min-width: 100px;">选择</th>
									<th style="text-align: center; min-width: 100px;">id</th>
									<th style="text-align: center; min-width: 800px;">内容</th>
								</tr>
							</thead>
							<tbody>

								<c:forEach var="item" items="${page.dataList}">
									<tr>
										<td style="text-align: center; vertical-align: middle;"><span
											class=""><div class="checker"
													id="uniform-inlineCheckbox1">
													<span><input type="checkbox" id="inlineCheckbox1"
														value="option1"></span>
												</div></span></td>
										<td style="text-align: center; vertical-align: middle;">${item.id}</td>
										<td><div class="newsItem">
												<div class="title">
													<a href="#">${item.title}</a>
												</div>
												<c:if test="${item.imgList!= null && fn:length(item.imgList) > 0}">
													<ul class="imageList">
														<c:forEach items="${item.imgList}" var="url" >
														
															<li><img src="${url}" /></li>
														</c:forEach>
													</ul>
												</c:if>

											</div>
											<div style="padding-top: 8px; clear: both;">
												<div class="sp">${item.pubTime}</div>
												<div class="sp">来源：${item.source}</div>
												<div class="sp">
													<a href="${item.sourceUrl}" target="_blank">原文地址</a>
												</div>
											</div></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<%@include file="/common/c_pagination.jsp"%>
					</div>

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
