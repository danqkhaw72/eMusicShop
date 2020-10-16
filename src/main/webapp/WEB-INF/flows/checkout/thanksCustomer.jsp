<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@include file="/WEB-INF/views/template/header.jsp" %>

<div class="container-wrapper">
    <div class="container">
    <br><br><br>
        <section>
            <div class="jumbotron">
                <div class="container">
                    <h1>Thanks you for your business!</h1>     
                    <p>Your order will be shipped in two business days</p>       
                </div>
            </div>
        </section>

        <section class="container" ng-app="cartApp">
            <p>
            <a href="<spring:url value="/" />" class="btn btn-default">OK</a>
            </p>
        </section>



<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.1/angular.min.js"></script>
<script src="<c:url value="/resources/js/controller.js" /> "></script>
<%@include file="/WEB-INF/views/template/footer.jsp" %>