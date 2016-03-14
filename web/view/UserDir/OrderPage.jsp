<%@include file="/view/jspf/taglibs.jspf" %>
<html>
<%@include file="/view/jspf/head.jspf" %>
<body>
<div class="col-lg-12">
    <div class="col-lg-offset-2 col-lg-8 panel panel-primary">
        <div class="panel-heading">
            <fmt:message key="order.enterInfo"/>
        </div>
        <div class="panel-body">
            <form action="${pageContext.request.contextPath}/rentCar" method="post" class="form-control">
                <div class="row ">
                    <div class="col-lg-3 list-inline">
                        <div class="col-lg-4">
                            <input name="id" value="${id}" hidden>
                        </div>
                        <div class="col-lg-8">
                            <p><fmt:message key="car.mark"/>: ${mark}</p>
                        </div>
                    </div>
                    <div class="col-lg-3">
                        <p><fmt:message key="car.name"/>: ${name}</p>
                    </div>
                    <div class="col-lg-3">
                        <p><fmt:message key="car.costPerDay"/>: ${cost}</p>
                    </div>
                    <div class="col-lg-3">
                        <p><fmt:message key="car.carClass"/>: ${carClass}</p>
                    </div>
                </div>
                <div class="row list inline">
                    <div class="col-lg-3">
                        <label for="passport"><fmt:message key="order.passport"/>:</label>
                    </div>
                    <div class="col-lg-9">
                        <input type="number" required="" name="passport" class="form-control" id="passport">
                    </div>
                </div>
                <div class="row list-inline">
                    <label for="driver"><input type="checkbox" id="driver" name="driver"><fmt:message
                            key="order.boolean.driver"/></label>
                </div>
                <div class="row list-inline">
                    <div class="col-lg-3">
                        <label for="startDate"><fmt:message key="order.startDate"/>: </label>
                    </div>
                    <div class="col-lg-9">
                        <input type="date" required="" class="form-control" id="startDate" name="startDate">
                    </div>
                </div>
                <div class="row list-inline">
                    <div class="col-lg-3">
                        <label for="finishDate"><fmt:message key="order.finishDate"/>: </label>
                    </div>
                    <div class="col-lg-9">
                        <input type="date" required="" class="form-control" id="finishDate" name="finishDate">
                    </div>
                </div>
                <div class="row col-lg-3 center-block">
                    <button type="submit" class="btn btn-primary"><fmt:message key="manager.button.confirm"/></button>
                </div>
            </form>

        </div>

    </div>
    <div class="col-lg-3">
        <a href="${pageContext.request.contextPath}/carList">
            <button class="btn btn-danger"><fmt:message key="manager.button.return"/></button>
        </a>
    </div>
</div>
</body>
</html>
