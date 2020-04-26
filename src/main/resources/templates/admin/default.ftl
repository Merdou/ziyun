<#assign ctx="${request.contextPath}">

<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Flot图表</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link href="${ctx}/static/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx }/static/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx }/static/css/animate.css" rel="stylesheet">
    <link href="${ctx }/static/css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg"  data-project="${ctx}">

    <div class="wrapper wrapper-content animated fadeInRight">

        <!-- 一行2列-->
        <div class="row">
            <div class="col-md-6">

                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">最近6个月的销售额</h3>
                    </div>
                    <div class="panel-body">
                        <!-- flot div-->
                        <div class="flot-chart">
                            <div class="flot-chart-content" id="flot-bar-sell"></div>
                        </div>
                    </div>
                </div>

            </div>

            <div class="col-md-6">

            </div>
        </div>


    </div>

</body>

    <!-- 全局js -->
    <script src="${ctx }/static/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctx }/static/js/bootstrap.min.js?v=3.3.6"></script>



    <!-- Flot -->
    <script src="${ctx}/static/js/plugins/flot/jquery.flot.js"></script>
    <script src="${ctx}/static/js/plugins/flot/jquery.flot.tooltip.min.js"></script>
    <script src="${ctx}/static/js/plugins/flot/jquery.flot.resize.js"></script>
    <script src="${ctx}/static/js/plugins/flot/jquery.flot.pie.js"></script>

    <!-- 自定义js -->
    <script src="${ctx}/static/js/content.js?v=1.0.0"></script>


    <!-- 后台首页的js自己定义 -->
     <script src="${ctx}/static/js/admin/default.js?v=1.0.0"></script>

</html>