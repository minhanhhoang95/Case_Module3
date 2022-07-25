<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<script src="assets\js\vendor.min.js"></script>

<c:if test="${param.vendor_js == 'index'}">
    <script src="assets\libs\morris-js\morris.min.js"></script>
    <script src="assets\libs\raphael\raphael.min.js"></script>
    <script src="assets\js\pages\dashboard.init.js"></script>
</c:if>


<!-- toast -->
<script src="assets\libs\toastr\toastr.min.js"></script>
<script src="assets\js\pages\toastr.init.js"></script>

<!-- sweet alert -->
<%--<script src="assets\libs\sweetalert2\sweetalert2.min.js"></script>--%>
<%--<script src="assets\js\pages\sweetalerts.init.js"></script>--%>
<script src="assets/js/sweetalert2.min.js"></script>
<script src="assets\js\app.min.js"></script>

