<!DOCTYPE html>
<html>
<head>
  	<title>分布式配置管理平台</title>
  	<#import "/common/common.macro.ftl" as netCommon>
	<@netCommon.commonStyle />
</head>
<body class="hold-transition skin-blue sidebar-mini <#if cookieMap?exists && "off" == cookieMap["adminlte_settings"].value >sidebar-collapse</#if> ">
<div class="wrapper">
	<!-- header -->
	<@netCommon.commonHeader />
	<!-- left -->
	<@netCommon.commonLeft />
	
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>使用教程<small></small></h1>
			<!--
			<ol class="breadcrumb">
				<li><a><i class="fa fa-dashboard"></i>调度中心</a></li>
				<li class="active">使用教程</li>
			</ol>
			-->
		</section>

		<!-- Main content -->
		<section class="content">
			<div class="callout callout-info">
				<h4>简介：分布式配置管理</h4>
				<br>
				<p>
					<a target="_blank" href="https://github.com/shiziqiu/shiziqiu-conf">github地址</a>&nbsp;&nbsp;&nbsp;&nbsp;
					<iframe src="https://ghbtns.com/github-btn.html?user=shiziqiu&repo=shiziqiu-conf&type=star" frameborder="0" scrolling="0" width="170px" height="20px" style="margin-bottom:-5px;"></iframe>
					<br><br>
					<a target="_blank" href="http://kuanghe.iteye.com/admin/blogs">博客地址</a>
                    <br><br>
                    <a >技术交流群：535115567</a>&nbsp;&nbsp;&nbsp;&nbsp;
				</p>
				<p></p>
            </div>
		</section>
		<!-- /.content -->
	</div>
	<!-- /.content-wrapper -->
	
	<!-- footer -->
	<@netCommon.commonFooter />
</div>
<@netCommon.commonScript />
</body>
</html>
