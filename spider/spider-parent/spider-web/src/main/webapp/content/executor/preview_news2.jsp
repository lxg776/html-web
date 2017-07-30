<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
 
<!-- start: Meta -->
<%@include file="/common/meta.jsp"%>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">  
<!-- end: Mobile Specific -->
<title>新闻预览</title>

<!-- end: Mobile Specific -->

<!-- start: CSS -->
<link id="bootstrap-style" href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
<link href="css/news.css" rel="stylesheet">
<link id="base-style" href="css/style.css" rel="stylesheet">
<link id="base-style-responsive" href="css/style-responsive.css"
	rel="stylesheet">
<link
	href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800&amp;subset=latin,cyrillic-ext,latin-ext"
	rel="stylesheet" type="text/css">
<!-- end: CSS -->


<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
	  	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<link id="ie-style" href="css/ie.css" rel="stylesheet">
	<![endif]-->

<!--[if IE 9]>
		<link id="ie9style" href="css/ie9.css" rel="stylesheet">
	<![endif]-->

<!-- start: Favicon -->
<link rel="shortcut icon" href="img/favicon.ico">
<!-- end: Favicon -->




</head>

<body class="text-align:center">
	<div style="margin: 0px auto; width: 900px;">
		<div class="qq_article">
			<div class="hd">
				<h1>${modle.title}</h1>
				<div class="qq_bar clearfix" bosszone="titleDown">
					<div class="a_Info">
						<span class="a_catalog" bosszone="ztTopic"></span><span class="a_source"
							bosszone="jgname">来源：${modle.source}</span><span class="a_author">作者：${modle.author}</span><span
							class="a_time">时间：${modle.pubTime}</span>
					</div>



					<span class="a_commentNum"> </span>
				</div>
			</div>
			<div class="bd" accesskey="3" tabindex="-1">
				<div id="Cnt-Main-Article-QQ" class="Cnt-Main-Article-QQ"
					bosszone="content">
					${modle.content}
				</div>

			</div>
		</div>

	</div>




</body>
</html>