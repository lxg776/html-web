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
<!-- end: Favicon -->
<style type="text/css">
body {
	background: url(img/bg-login.jpg) !important;
}
</style>
<script type="text/javascript">
	$(function() {
		focusTenant();
	});

	function focusTenant() {
		if (document.f.tenant.value == '') {
			document.f.tenant.focus();
		} else {
			focusUsername();
		}
	}

	function focusUsername() {
		if (document.f.j_username.value == '') {
			document.f.j_username.focus();
		} else {
			document.f.j_password.focus();
		}
	}
</script>
</head>

<body>
	<div class="container-fluid-full">
		<div class="row-fluid">

			<div class="row-fluid">
				<div class="login-box">
					<div class="icons">
						<a href="index.html"><i class="halflings-icon home"></i></a> <a
							href="#"><i class="halflings-icon cog"></i></a>
					</div>
					<h2>爬爬管理器</h2>
					<form class="form-horizontal" id="loginForm" method="post"
						action="${ctx}/j_spring_security_check" prependId = "false">
						<fieldset>

							<div class="input-prepend" title="Username">
								<span class="add-on"><i class="halflings-icon user"></i></span>
								<input type='text' id="j_username" name='j_username'
									class="input-large span10"
									value="${empty sessionScope['SECURITY_LAST_USERNAME'] ? cookie['SECURITY_LAST_USERNAME'].value : sessionScope['SECURITY_LAST_USERNAME']}"
									aria-describedby="inputSuccess3Status" />
							</div>
							<div class="clearfix"></div>

							<div class="input-prepend" title="Password">
								<span class="add-on"><i class="halflings-icon lock"></i></span>
								<input class="input-large span10" type='password' id="j_password"
									name='j_password' placeholder="type password" />
							</div>
							<div class="clearfix"></div>

							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

							<c:if test="${sessionScope['captchaSessionToken']}">
								<div class="input-prepend" title="Username">
									<label class="col-md-2 control-label" for="password"
										style="padding-left: 0px;">验证码</label>
									<div class="col-md-2">
										<img id="captchaPicture"
											src="captcha.jsp?_=<%=System.currentTimeMillis()%>"
											onclick="this.src='captcha.jsp?_=' + new Date().getTime()">
									</div>
									<div class="col-md-8">
										<input type='text' id="captcha" name='captcha'
											class="form-control" value=''>
									</div>
								</div>
							</c:if>

							<label class="remember" for="remember"><input
								type="checkbox" id="remember" />Remember me</label>

							<div class="button-login">
								<button type="submit" class="btn btn-primary">Login</button>
							</div>
							<div class="clearfix"></div>
					</form>
					<hr>
					<h3>Forgot Password?</h3>
					<p>
						No problem, <a href="#">click here</a> to get a new password.
					</p>
				</div>
				<!--/span-->
			</div>
			<!--/row-->


		</div>
		<!--/.fluid-container-->

	</div>
	<!--/fluid-row-->
	<div class="common-modal modal fade" id="common-Modal1" tabindex="-1"
		role="dialog" aria-hidden="true">
		<div class="modal-content">
			<ul class="list-inline item-details">
				<li><a href="http://sc.chinaz.com">Admin templates</a></li>
				<li><a href="http://sc.chinaz.com">Bootstrap themes</a></li>
			</ul>
		</div>
	</div>
	<!-- start: JavaScript-->

	<script src="js/jquery-1.9.1.min.js"></script>
	<script src="js/jquery-migrate-1.0.0.min.js"></script>

	<script src="js/jquery-ui-1.10.0.custom.min.js"></script>

	<script src="js/jquery.ui.touch-punch.js"></script>

	<script src="js/modernizr.js"></script>

	<script src="js/bootstrap.min.js"></script>

	<script src="js/jquery.cookie.js"></script>

	<script src='js/fullcalendar.min.js'></script>

	<script src='js/jquery.dataTables.min.js'></script>

	<script src="js/excanvas.js"></script>
	<script src="js/jquery.flot.js"></script>
	<script src="js/jquery.flot.pie.js"></script>
	<script src="js/jquery.flot.stack.js"></script>
	<script src="js/jquery.flot.resize.min.js"></script>

	<script src="js/jquery.chosen.min.js"></script>

	<script src="js/jquery.uniform.min.js"></script>

	<script src="js/jquery.cleditor.min.js"></script>

	<script src="js/jquery.noty.js"></script>

	<script src="js/jquery.elfinder.min.js"></script>

	<script src="js/jquery.raty.min.js"></script>

	<script src="js/jquery.iphone.toggle.js"></script>

	<script src="js/jquery.uploadify-3.1.min.js"></script>

	<script src="js/jquery.gritter.min.js"></script>

	<script src="js/jquery.imagesloaded.js"></script>

	<script src="js/jquery.masonry.min.js"></script>

	<script src="js/jquery.knob.modified.js"></script>

	<script src="js/jquery.sparkline.min.js"></script>

	<script src="js/counter.js"></script>

	<script src="js/retina.js"></script>

	<script src="js/custom.js"></script>
	<!-- end: JavaScript-->

</body>
</html>
