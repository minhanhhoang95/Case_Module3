<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html class="no-js" lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Dashboard V.1 | Nalika - Material Admin Template</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- favicon
		============================================ -->
    <link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico">
    <!-- Google Fonts
		============================================ -->
    <link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,700,900" rel="stylesheet">
    <!-- Bootstrap CSS
		============================================ -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <!-- Bootstrap CSS
		============================================ -->
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <!-- nalika Icon CSS
        ============================================ -->
    <link rel="stylesheet" href="css/nalika-icon.css">
    <!-- owl.carousel CSS
		============================================ -->
    <link rel="stylesheet" href="css/owl.carousel.css">
    <link rel="stylesheet" href="css/owl.theme.css">
    <link rel="stylesheet" href="css/owl.transitions.css">
    <!-- animate CSS
		============================================ -->
    <link rel="stylesheet" href="css/animate.css">
    <!-- normalize CSS
		============================================ -->
    <link rel="stylesheet" href="css/normalize.css">
    <!-- meanmenu icon CSS
		============================================ -->
    <link rel="stylesheet" href="css/meanmenu.min.css">
    <!-- main CSS
		============================================ -->
    <link rel="stylesheet" href="css/main.css">
    <!-- morrisjs CSS
		============================================ -->
    <link rel="stylesheet" href="css/morrisjs/morris.css">
    <!-- mCustomScrollbar CSS
		============================================ -->
    <link rel="stylesheet" href="css/scrollbar/jquery.mCustomScrollbar.min.css">
    <!-- metisMenu CSS
		============================================ -->
    <link rel="stylesheet" href="css/metisMenu/metisMenu.min.css">
    <link rel="stylesheet" href="css/metisMenu/metisMenu-vertical.css">
    <!-- calendar CSS
		============================================ -->
    <link rel="stylesheet" href="css/calendar/fullcalendar.min.css">
    <link rel="stylesheet" href="css/calendar/fullcalendar.print.min.css">
    <!-- style CSS
		============================================ -->
    <link rel="stylesheet" href="style.css">
    <!-- responsive CSS
		============================================ -->
    <link rel="stylesheet" href="css/responsive.css">
    <!-- modernizr JS
		============================================ -->
    <script src="js/vendor/modernizr-2.8.3.min.js"></script>
</head>

<body>
<!--[if lt IE 8]>
<p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade
    your browser</a> to improve your experience.</p>
<![endif]-->
<jsp:include page="/WEB-INF/admin/layout/navleft.jsp"></jsp:include>

<!-- Start Welcome area -->
<div class="all-content-wrapper">
<jsp:include page="/WEB-INF/admin/layout/head.jsp"></jsp:include>


<div class="section-admin container-fluid">
    <div class="row admin text-center">
        <div class="col-md-12">
            <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
                <div class="admin-content analysis-progrebar-ctn res-mg-t-15">
                    <h4 class="text-left text-uppercase"><b>Orders</b></h4>
                    <div class="row vertical-center-box vertical-center-box-tablet">
                        <div class="col-xs-3 mar-bot-15 text-left">
                            <label class="label bg-green">30% <i class="fa fa-level-up" aria-hidden="true"></i></label>
                        </div>
                        <div class="col-xs-9 cus-gh-hd-pro">
                            <h2 class="text-right no-margin">10,000</h2>
                        </div>
                    </div>
                    <div class="progress progress-mini">
                        <div style="width: 78%;" class="progress-bar bg-green"></div>
                    </div>
                </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12" style="margin-bottom:1px;">
                <div class="admin-content analysis-progrebar-ctn res-mg-t-30">
                    <h4 class="text-left text-uppercase"><b>Tax Deduction</b></h4>
                    <div class="row vertical-center-box vertical-center-box-tablet">
                        <div class="text-left col-xs-3 mar-bot-15">
                            <label class="label bg-red">15% <i class="fa fa-level-down" aria-hidden="true"></i></label>
                        </div>
                        <div class="col-xs-9 cus-gh-hd-pro">
                            <h2 class="text-right no-margin">5,000</h2>
                        </div>
                    </div>
                    <div class="progress progress-mini">
                        <div style="width: 38%;" class="progress-bar progress-bar-danger bg-red"></div>
                    </div>
                </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
                <div class="admin-content analysis-progrebar-ctn res-mg-t-30">
                    <h4 class="text-left text-uppercase"><b>Revenue</b></h4>
                    <div class="row vertical-center-box vertical-center-box-tablet">
                        <div class="text-left col-xs-3 mar-bot-15">
                            <label class="label bg-blue">50% <i class="fa fa-level-up" aria-hidden="true"></i></label>
                        </div>
                        <div class="col-xs-9 cus-gh-hd-pro">
                            <h2 class="text-right no-margin">$70,000</h2>
                        </div>
                    </div>
                    <div class="progress progress-mini">
                        <div style="width: 60%;" class="progress-bar bg-blue"></div>
                    </div>
                </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
                <div class="admin-content analysis-progrebar-ctn res-mg-t-30">
                    <h4 class="text-left text-uppercase"><b>Yearly Sales</b></h4>
                    <div class="row vertical-center-box vertical-center-box-tablet">
                        <div class="text-left col-xs-3 mar-bot-15">
                            <label class="label bg-purple">80% <i class="fa fa-level-up" aria-hidden="true"></i></label>
                        </div>
                        <div class="col-xs-9 cus-gh-hd-pro">
                            <h2 class="text-right no-margin">$100,000</h2>
                        </div>
                    </div>
                    <div class="progress progress-mini">
                        <div style="width: 60%;" class="progress-bar bg-purple"></div>
                    </div>
                </div>
            </div>
        </div>
        -->
    </div>
</div>


<div class="container-fluid">
    <div class="row">
        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
            <div class="product-sales-chart">
                <div class="portlet-title">
                    <div class="row">
                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                            <div class="caption pro-sl-hd">
                                <span class="caption-subject text-uppercase"><b>Product Sales</b></span>
                            </div>
                        </div>
                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                            <div class="actions graph-rp">
                                <div class="btn-group" data-toggle="buttons">
                                    <label class="btn btn-grey active">
                                        <input type="radio" name="options" class="toggle" id="option1" checked="">Today</label>
                                    <label class="btn btn-grey">
                                        <input type="radio" name="options" class="toggle" id="option2">Week</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="curved-line-chart" class="flot-chart-sts flot-chart curved-chart-statistic"></div>
            </div>
        </div>
        <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
            <div class="white-box analytics-info-cs mg-b-30 res-mg-t-30">
                <h3 class="box-title">Total Visit</h3>
                <ul class="list-inline two-part-sp">
                    <li>
                        <div id="sparklinedash"></div>
                    </li>
                    <li class="text-right sp-cn-r"><i class="fa fa-level-up" aria-hidden="true"></i> <span
                            class="counter sales-sts-ctn">8659</span></li>
                </ul>
            </div>
            <div class="white-box analytics-info-cs mg-b-30">
                <h3 class="box-title">Total Page Views</h3>
                <ul class="list-inline two-part-sp">
                    <li>
                        <div id="sparklinedash2"></div>
                    </li>
                    <li class="text-right"><i class="fa fa-level-up" aria-hidden="true"></i> <span
                            class="counter sales-sts-ctn">7469</span></li>
                </ul>
            </div>
            <div class="white-box analytics-info-cs mg-b-30">
                <h3 class="box-title">Unique Visitor</h3>
                <ul class="list-inline two-part-sp">
                    <li>
                        <div id="sparklinedash3"></div>
                    </li>
                    <li class="text-right"><i class="fa fa-level-up" aria-hidden="true"></i> <span
                            class="counter sales-sts-ctn">6011</span></li>
                </ul>
            </div>
            <div class="white-box analytics-info-cs">
                <h3 class="box-title">Bounce Rate</h3>
                <ul class="list-inline two-part-sp">
                    <li>
                        <div id="sparklinedash4"></div>
                    </li>
                    <li class="text-right"><i class="fa fa-level-down" aria-hidden="true"></i> <span
                            class="sales-sts-ctn">18%</span></li>
                </ul>
            </div>
        </div>
    </div>
</div>


<div class="calender-area mg-tb-30">
    <div class="container-fluid">

    </div>
</div>
<jsp:include page="/WEB-INF/admin/layout/footer.jsp"></jsp:include>
</div>
<!-- jquery
    ============================================ -->
<script src="js/vendor/jquery-1.12.4.min.js"></script>
<!-- bootstrap JS
    ============================================ -->
<script src="js/bootstrap.min.js"></script>
<!-- wow JS
    ============================================ -->
<script src="js/wow.min.js"></script>
<!-- price-slider JS
    ============================================ -->
<script src="js/jquery-price-slider.js"></script>
<!-- meanmenu JS
    ============================================ -->
<script src="js/jquery.meanmenu.js"></script>
<!-- owl.carousel JS
    ============================================ -->
<script src="js/owl.carousel.min.js"></script>
<!-- sticky JS
    ============================================ -->
<script src="js/jquery.sticky.js"></script>
<!-- scrollUp JS
    ============================================ -->
<script src="js/jquery.scrollUp.min.js"></script>
<!-- mCustomScrollbar JS
    ============================================ -->
<script src="js/scrollbar/jquery.mCustomScrollbar.concat.min.js"></script>
<script src="js/scrollbar/mCustomScrollbar-active.js"></script>
<!-- metisMenu JS
    ============================================ -->
<script src="js/metisMenu/metisMenu.min.js"></script>
<script src="js/metisMenu/metisMenu-active.js"></script>
<!-- sparkline JS
    ============================================ -->
<script src="js/sparkline/jquery.sparkline.min.js"></script>
<script src="js/sparkline/jquery.charts-sparkline.js"></script>
<!-- calendar JS
    ============================================ -->
<script src="js/calendar/moment.min.js"></script>
<script src="js/calendar/fullcalendar.min.js"></script>
<script src="js/calendar/fullcalendar-active.js"></script>
<!-- float JS
    ============================================ -->
<script src="js/flot/jquery.flot.js"></script>
<script src="js/flot/jquery.flot.resize.js"></script>
<script src="js/flot/curvedLines.js"></script>
<script src="js/flot/flot-active.js"></script>
<!-- plugins JS
    ============================================ -->
<script src="js/plugins.js"></script>
<!-- main JS
    ============================================ -->
<script src="js/main.js"></script>
</body>

</html>