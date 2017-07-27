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
					<li><i class="icon-home"></i> <a href="index.html">执行者</a> <i
						class="icon-angle-right"></i></li>
					<li><i class="icon-edit"></i> <a href="#">解析规则</a></li>
					<li><i class="icon-edit"></i> <a href="#">操作编辑</a></li>
				</ul>

				<div class="row-fluid sortable">
					<div class="box span12">
						<div data-original-title="" class="box-header">
							<h2>
								<i class="halflings-icon white edit"></i><span class="break"></span>抓取配置
							</h2>
							<div class="box-icon">
								<a href="#" class="btn-setting"><i
									class="halflings-icon white wrench"></i></a> <a href="#"
									class="btn-minimize"><i
									class="halflings-icon white chevron-up"></i></a> <a href="#"
									class="btn-close"><i class="halflings-icon white remove"></i></a>
							</div>
						</div>
						<div class="box-content">
							<form class="form-horizontal" action="saveOperation" method="post">
								<h3>${vo.editFileShow}</h3>
								<fieldset>
									<div class="control-group">
										<label class="control-label" for="selectError3">操作类型</label>
										<div class="controls">
											<select id="selectError3" name="type">
												<option value="location">元素定位</option>
												<option value="cut">字符串截取</option>
												<option value="gettext">正则过滤</option>
											</select>
										</div>
									</div>


									<div class="control-group">
										<label class="control-label" for="typeahead">权重</label>
										<div class="controls">
											<input type="text" name="weight" value="${vo.weight}" class="span6 typeahead" id="typeahead"
												data-provide="typeahead" data-items="4">

										</div>
									</div>


									<div class="control-group">
										<label class="control-label" for="typeahead">参数1</label>
										<div class="controls">
											<input type="text" class="span6 typeahead" id="typeahead"
												name="param1" value="${vo.param1}"  data-provide="typeahead" data-items="4">

										</div>
									</div>



									<div class="control-group">
										<label class="control-label" for="typeahead">参数2</label>
										<div class="controls">
											<input type="text" class="span6 typeahead" id="typeahead"
												name="param2" value="${vo.param2}"  data-provide="typeahead" data-items="4">

										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="typeahead">参数3</label>
										<div class="controls">
											<input type="text" class="span6 typeahead" id="typeahead"
												name="param3" value="${vo.param3}" data-provide="typeahead" data-items="4">

										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="typeahead">参数4</label>
										<div class="controls">
											<input type="text" class="span6 typeahead" id="typeahead"
												name="param4" value="${vo.param4}" data-provide="typeahead" data-items="4">

										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="typeahead">参数5</label>
										<div class="controls">
											<input type="text" class="span6 typeahead" id="typeahead"
												name="param5" value="${vo.param5}" data-provide="typeahead" data-items="4">

										</div>
									</div>

									<div class="form-actions">
										<button type="submit" class="btn btn-primary">保存</button>
									</div>
								</fieldset>
								
								<c:if test="${vo.keyWord != null }">
									<input type="hidden" name="keyWord" value="${vo.keyWord}">
									<input type="hidden" name="id" value="${vo.id}">
								</c:if>
									
									<input type="hidden" name="fileName" value="${vo.fileName}">
									<input type="hidden" name="executorId" value="${vo.executorId}">
								
							</form>

						</div>
					</div>
					<!--/span-->

				</div>
				<!--/row-->

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
