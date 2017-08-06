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
.jqstooltip {
	position: absolute;
	left: 0px;
	top: 0px;
	visibility: hidden;
	background: rgb(0, 0, 0) transparent;
	background-color: rgba(0, 0, 0, 0.6);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr=#99000000,
		endColorstr=#99000000);
	-ms-filter:
		"progid:DXImageTransform.Microsoft.gradient(startColorstr=#99000000, endColorstr=#99000000)";
	color: white;
	font: 10px arial, san serif;
	text-align: left;
	white-space: nowrap;
	padding: 5px;
	border: 1px solid white;
	z-index: 10000;
}

.jqsfield {
	color: white;
	font: 10px arial, san serif;
	text-align: left;
}

.menuTree {
	margin-left: -30px;
}

.menuTree div {
	padding-left: 30px;
}

.menuTree div ul {
	overflow: hidden;
	display: none;
	height: auto;
}

.menuTree span {
	display: block;
	height: 25px;
	line-height: 25px;
	padding-left: 5px;
	margin: 1px 0;
	cursor: pointer;
	border-bottom: 1px solid #CCC;
}

.menuTree span:hover {
	background-color: #e6e6e6;
	color: #cf0404;
}

.menuTree a {
	color: #00F;
	text-decoration: none;
}

.menuTree a:hover {
	color: #06F;
}

.btn {
	height: 30px;
	margin-top: 10px;
	border-bottom: 1px solid #CCC;
}
</style>



<script type="text/javascript">
	var listUrl = "${ctx}" + "/news/columnList?pageNum=";
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
	<div id="content" class="span11" style="min-height: 794px;">
			
			
			<ul class="breadcrumb">
				<li>
					<i class="icon-home"></i>
					<a href="index.html">节点管理</a> 
					<i class="icon-angle-right"></i>
				</li>
				<li><a href="#">列表</a></li>
			</ul>

			<!--/row-->

			<!--/row-->
			
			<!--/row-->
			
			<div class="row-fluid  ui-sortable">	
				<div class="box span12">
					<div class="box-header">
						<h2><i class="halflings-icon white align-justify"></i><span class="break"></span>全部节点</h2>
							<div class="box-icon">
							<a href="${ctx}/node/toAdd"  style="color: #FFF">添加根节点</a>
						</div>
					</div>
					<div class="box-content">
    				<div id="menuTree" class="menuTree"></div>
						   
					</div>
				</div><!--/span-->
			</div><!--/row-->
    

	</div>
			<!--/span-->
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

	<script language="javascript">
		var json = ${jsonStr}
		/*递归实现获取无级树数据并生成DOM结构*/
		var str = "";
		var forTree = function(o) {
			for (var i = 0; i < o.length; i++) {
				var urlstr = "";
				try {
					if (typeof o[i]["url"] == "undefined") {
						
						urlstr = "<div><span>"
								+ o[i]["nodeName"]
								+ " &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"${ctx}/node/toEdit?nodeId="+o[i]["id"]+"\">edit</a>"+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"${ctx}/node/toAdd?fid="+o[i]["id"]+"\">add</a>"+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"${ctx}/news/newsList?nodeId="+o[i]["id"]+"\">新闻编辑</a></span><ul>";
					} else {
						urlstr = "<div><span><a href=" + o[i]["id"] + ">"
								+ o[i]["nodeName"] + "</a>99</span><ul>";
					}
					str += urlstr;
					if (o[i]["childList"] != null) {
						forTree(o[i]["childList"]);
					}
					str += "</ul></div>";
				} catch (e) {
				}
			}
			return str;
		}
		/*添加无级树*/
		document.getElementById("menuTree").innerHTML = forTree(json);
		/*树形菜单*/
		var menuTree = function() {
			//给有子对象的元素加[+-]
			$("#menuTree ul").each(function(index, element) {
				var ulContent = $(element).html();
				var spanContent = $(element).siblings("span").html();

				if (ulContent) {
					$(element).siblings("span").html("[+] " + spanContent)
				}
			});

			$("#menuTree").find("div span").click(function() {
				var ul = $(this).siblings("ul");
				var spanStr = $(this).html();
				var spanContent = spanStr.substr(3, spanStr.length);
				console.log(spanContent);
				if (ul.find("div").html() != null) {
					if (ul.css("display") == "none") {
						ul.show(300);
						$(this).html("[-] " + spanContent);
					} else {
						ul.hide(300);
						$(this).html("[+] " + spanContent);
					}
				}
			})
		}()
		/*展开*/
		$("#btn_open").click(function() {
			$("#menuTree ul").show(300);
			curzt("-");
		})
		/*收缩*/
		$("#btn_close").click(function() {
			$("#menuTree ul").hide(300);
			curzt("+");
		})
		function curzt(v) {
			$("#menuTree span").each(function(index, element) {
				var ul = $(this).siblings("ul");
				var spanStr = $(this).html();
				var spanContent = spanStr.substr(3, spanStr.length);
				if (ul.find("div").html() != null) {
					$(this).html("[" + v + "] " + spanContent);
				}
			});
		}
	</script>
</body>
</html>
