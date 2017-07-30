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
					<li><i class="icon-edit"></i> <a href="#">编辑</a></li>
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
							<form class="form-horizontal" action="preview" method="post">
								<fieldset>
									<div class="control-group">
										<label class="control-label" for="typeahead">别名</label>
										<div class="controls">
											<input type="text" class="span6 typeahead" id="typeahead"
												value="${vo.name}" data-provide="typeahead" data-items="4"
												disabled="disabled">

										</div>
									</div>

									<input type="hidden" name="executorId" value="${vo.id}">

									<div class="control-group">
										<label class="control-label" for="typeahead">测试连接</label>
										<div class="controls">
											<input type="text" class="span6 typeahead" id="typeahead"
												value="${vo.linkUrl}" data-provide="typeahead"
												data-items="4" disabled="disabled">

										</div>
									</div>


									<div class="control-group">

										<label class="control-label" for="typeahead">新闻列表数据操作</label>
										<a class="btn btn-small btn-danger"
											href="${ctx}/executor/toOperationEdit?fileName=newsList&&executorId=${vo.id}"
											style="margin-top: 4px; margin-left: 16px;">添加操作</a>

										<div style="width: 1000px;">
											<table class="table table-bordered">
												<thead>
													<tr>
														<th>操作类型</th>
														<th>参数</th>
														<th>返回类型</th>
														<th>权重</th>
														<th>操作</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${vo.operationMap.newsList}" var="item">
														<tr>
															<c:if test="${item.type == 'location'}">
																<td>元素定位</td>
															</c:if>
															<c:if test="${item.type == 'cut'}">
																<td>字符串截取</td>
															</c:if>
															<c:if test="${item.type == 'gettext'}">
																<td>正则过滤</td>
															</c:if>
															<td class="center">${item.param1}<br />${item.param2}<br />${item.param3}<br />${item.param4}<br />${item.param5}</td>
															<td class="center">${item.returnType}</td>
															<td class="center">${item.weight}</td>

															<td class="center"><a
																href="${ctx}/executor/toOperationEdit?fileName=newsList&&executorId=${vo.id}&&id=${item.id}&&keyWord=edit">编辑</a>/
																<a
																href="${ctx}/executor/delOperation?executorId=${vo.id}&&id=${item.id}">删除</a>

															</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>


										</div>

									</div>

									<div class="control-group">

										<label class="control-label" for="typeahead">新闻详情数据操作</label>
										<a class="btn btn-small btn-danger"
											href="${ctx}/executor/toOperationEdit?fileName=detail&&executorId=${vo.id}"
											style="margin-top: 4px; margin-left: 16px;">添加操作</a>

										<div style="width: 1000px;">
											<table class="table table-bordered">
												<thead>
													<tr>
														<th>操作类型</th>
														<th>参数</th>
														<th>返回类型</th>
														<th>权重</th>
														<th>操作</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${vo.operationMap.detail}" var="item">
														<tr>
															<c:if test="${item.type == 'location'}">
																<td>元素定位</td>
															</c:if>
															<c:if test="${item.type == 'cut'}">
																<td>字符串截取</td>
															</c:if>
															<c:if test="${item.type == 'gettext'}">
																<td>正则过滤</td>
															</c:if>
															<td class="center">${item.param1}<br />${item.param2}<br />${item.param3}<br />${item.param4}<br />${item.param5}</td>
															<td class="center">${item.returnType}</td>
															<td class="center">${item.weight}</td>

															<td class="center"><a
																href="${ctx}/executor/toOperationEdit?fileName=detail&&executorId=${vo.id}&&id=${item.id}&&keyWord=edit">编辑</a>/
																<a
																href="${ctx}/executor/delOperation?executorId=${vo.id}&&id=${item.id}">删除</a>

															</td>
														</tr>
													</c:forEach>

												</tbody>
											</table>


										</div>

									</div>

									<div class="control-group">

										<label class="control-label" for="typeahead">标题数据操作</label> <a
											class="btn btn-small btn-danger"
											href="${ctx}/executor/toOperationEdit?fileName=title&&executorId=${vo.id}"
											style="margin-top: 4px; margin-left: 16px;">添加操作</a>

										<div style="width: 1000px;">
											<table class="table table-bordered">
												<thead>
													<tr>
														<th>操作类型</th>
														<th>参数</th>
														<th>返回类型</th>
														<th>权重</th>
														<th>操作</th>
													</tr>
												</thead>
												`
												<tbody>
													<c:forEach items="${vo.operationMap.title}" var="item">
														<tr>
															<c:if test="${item.type == 'location'}">
																<td>元素定位</td>
															</c:if>
															<c:if test="${item.type == 'cut'}">
																<td>字符串截取</td>
															</c:if>
															<c:if test="${item.type == 'gettext'}">
																<td>正则过滤</td>
															</c:if>
															<td class="center">${item.param1}<br />${item.param2}<br />${item.param3}<br />${item.param4}<br />${item.param5}</td>
															<td class="center">${item.returnType}</td>
															<td class="center">${item.weight}</td>

															<td class="center"><a
																href="${ctx}/executor/toOperationEdit?fileName=title&&executorId=${vo.id}&&id=${item.id}&&keyWord=edit">编辑</a>/
																<a
																href="${ctx}/executor/delOperation?executorId=${vo.id}&&id=${item.id}">删除</a>

															</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>


										</div>

									</div>


									<div class="control-group">

										<label class="control-label" for="typeahead">新闻图片数据操作</label>
										<a class="btn btn-small btn-danger"
											href="${ctx}/executor/toOperationEdit?fileName=newsImages&&executorId=${vo.id}"
											style="margin-top: 4px; margin-left: 16px;">添加操作</a>

										<div style="width: 1000px;">
											<table class="table table-bordered">
												<thead>
													<tr>
														<th>操作类型</th>
														<th>参数</th>
														<th>返回类型</th>
														<th>权重</th>
														<th>操作</th>
													</tr>
												</thead>


												<tbody>
													<c:forEach items="${vo.operationMap.newsImages}" var="item">
														<tr>
															<c:if test="${item.type == 'location'}">
																<td>元素定位</td>
															</c:if>
															<c:if test="${item.type == 'cut'}">
																<td>字符串截取</td>
															</c:if>
															<c:if test="${item.type == 'gettext'}">
																<td>正则过滤</td>
															</c:if>
															<td class="center">${item.param1}<br />${item.param2}<br />${item.param3}<br />${item.param4}<br />${item.param5}</td>
															<td class="center">${item.returnType}</td>
															<td class="center">${item.weight}</td>

															<td class="center"><a
																href="${ctx}/executor/toOperationEdit?fileName=newsImages&&executorId=${vo.id}&&id=${item.id}&&keyWord=edit">编辑</a>/
																<a
																href="${ctx}/executor/delOperation?executorId=${vo.id}&&id=${item.id}">删除</a>

															</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>


										</div>

									</div>


									<div class="control-group">

										<label class="control-label" for="typeahead">作者数据操作</label> <a
											class="btn btn-small btn-danger"
											href="${ctx}/executor/toOperationEdit?fileName=author&&executorId=${vo.id}"
											style="margin-top: 4px; margin-left: 16px;">添加操作</a>

										<div style="width: 1000px;">
											<table class="table table-bordered">
												<thead>
													<tr>
														<th>操作类型</th>
														<th>参数</th>
														<th>返回类型</th>
														<th>权重</th>
														<th>操作</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${vo.operationMap.author}" var="item">
														<tr>
															<c:if test="${item.type == 'location'}">
																<td>元素定位</td>
															</c:if>
															<c:if test="${item.type == 'cut'}">
																<td>字符串截取</td>
															</c:if>
															<c:if test="${item.type == 'gettext'}">
																<td>正则过滤</td>
															</c:if>
															<td class="center">${item.param1}<br />${item.param2}<br />${item.param3}<br />${item.param4}<br />${item.param5}</td>
															<td class="center">${item.returnType}</td>
															<td class="center">${item.weight}</td>

															<td class="center"><a
																href="${ctx}/executor/toOperationEdit?fileName=author&&executorId=${vo.id}&&id=${item.id}&&keyWord=edit">编辑</a>/
																<a
																href="${ctx}/executor/delOperation?executorId=${vo.id}&&id=${item.id}">删除</a>

															</td>
														</tr>
													</c:forEach>

												</tbody>
											</table>


										</div>

									</div>

									<div class="control-group">

										<label class="control-label" for="typeahead">来源数据操作</label> <a
											class="btn btn-small btn-danger"
											href="${ctx}/executor/toOperationEdit?fileName=source&&executorId=${vo.id}"
											style="margin-top: 4px; margin-left: 16px;">添加操作</a>

										<div style="width: 1000px;">
											<table class="table table-bordered">
												<thead>
													<tr>
														<th>操作类型</th>
														<th>参数</th>
														<th>返回类型</th>
														<th>权重</th>
														<th>操作</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${vo.operationMap.source}" var="item">
														<tr>
															<c:if test="${item.type == 'location'}">
																<td>元素定位</td>
															</c:if>
															<c:if test="${item.type == 'cut'}">
																<td>字符串截取</td>
															</c:if>
															<c:if test="${item.type == 'gettext'}">
																<td>正则过滤</td>
															</c:if>
															<td class="center">${item.param1}<br />${item.param2}<br />${item.param3}<br />${item.param4}<br />${item.param5}</td>
															<td class="center">${item.returnType}</td>
															<td class="center">${item.weight}</td>

															<td class="center"><a
																href="${ctx}/executor/toOperationEdit?fileName=source&&executorId=${vo.id}&&id=${item.id}&&keyWord=edit">编辑</a>/
																<a
																href="${ctx}/executor/delOperation?executorId=${vo.id}&&id=${item.id}">删除</a>

															</td>
														</tr>
													</c:forEach>

												</tbody>
											</table>


										</div>

									</div>
									
									
									<div class="control-group">

										<label class="control-label" for="typeahead">发布时间</label> <a
											class="btn btn-small btn-danger"
											href="${ctx}/executor/toOperationEdit?fileName=pubDate&&executorId=${vo.id}"
											style="margin-top: 4px; margin-left: 16px;">添加操作</a>

										<div style="width: 1000px;">
											<table class="table table-bordered">
												<thead>
													<tr>
														<th>操作类型</th>
														<th>参数</th>
														<th>返回类型</th>
														<th>权重</th>
														<th>操作</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${vo.operationMap.pubDate}" var="item">
														<tr>
															<c:if test="${item.type == 'location'}">
																<td>元素定位</td>
															</c:if>
															<c:if test="${item.type == 'cut'}">
																<td>字符串截取</td>
															</c:if>
															<c:if test="${item.type == 'gettext'}">
																<td>正则过滤</td>
															</c:if>
															<td class="center">${item.param1}<br />${item.param2}<br />${item.param3}<br />${item.param4}<br />${item.param5}</td>
															<td class="center">${item.returnType}</td>
															<td class="center">${item.weight}</td>

															<td class="center"><a
																href="${ctx}/executor/toOperationEdit?fileName=pubDate&&executorId=${vo.id}&&id=${item.id}&&keyWord=edit">编辑</a>/
																<a
																href="${ctx}/executor/delOperation?executorId=${vo.id}&&id=${item.id}">删除</a>

															</td>
														</tr>
													</c:forEach>

												</tbody>
											</table>


										</div>

									</div>


									<div class="control-group">

										<label class="control-label" for="typeahead">新闻详情数据操作</label>
										<a class="btn btn-small btn-danger"
											href="${ctx}/executor/toOperationEdit?fileName=newsContent&&executorId=${vo.id}"
											style="margin-top: 4px; margin-left: 16px;">添加操作</a>

										<div style="width: 1000px;">
											<table class="table table-bordered">
												<thead>
													<tr>
														<th>操作类型</th>
														<th>参数</th>
														<th>返回类型</th>
														<th>权重</th>
														<th>操作</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${vo.operationMap.newsContent}"
														var="item">
														<tr>
															<c:if test="${item.type == 'location'}">
																<td>元素定位</td>
															</c:if>
															<c:if test="${item.type == 'cut'}">
																<td>字符串截取</td>
															</c:if>
															<c:if test="${item.type == 'gettext'}">
																<td>正则过滤</td>
															</c:if>
															<td class="center">${item.param1}<br />${item.param2}<br />${item.param3}<br />${item.param4}<br />${item.param5}</td>
															<td class="center">${item.returnType}</td>
															<td class="center">${item.weight}</td>

															<td class="center"><a
																href="${ctx}/executor/toOperationEdit?fileName=newsContent&&executorId=${vo.id}&&id=${item.id}&&keyWord=edit">编辑</a>/
																<a
																href="${ctx}/executor/delOperation?executorId=${vo.id}&&id=${item.id}">删除</a>

															</td>
														</tr>
													</c:forEach>

												</tbody>
											</table>


										</div>

									</div>

									<div class="controls">
										<select id="selectError3" name="keyWord">
										<option value="online">抓取</option>
										<option value="local">本地</option>
										</select>
									</div>



									<div class="form-actions">
										<button type="submit" class="btn btn-primary">预览</button>
										
									</div>
								</fieldset>
							</form>

						</div>
					</div>
					<!--/span-->
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
