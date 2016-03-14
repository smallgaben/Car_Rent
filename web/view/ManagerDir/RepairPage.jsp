<%@include file="/view/jspf/taglibs.jspf" %>

<html>
<%@include file="/view/jspf/head.jspf" %>
<body>
<div class="panel panel-primary col-lg-offset-2 col-lg-8">
    <div class="panel-heading">
        <p class="h3"><fmt:message key="heading.repairPage"/></p>
    </div>
    <div class="panel-body">
        <div class="row">
            <div class="col-lg-6">
                <p class="h3"><fmt:message key="car"/></p><br/>

                <p>mark: ${order.car.mark}</p><br/>

                <p>name: ${order.car.name}</p><br/>

                <p>class: ${order.car.carClass.name}</p><br>

                <p>cost (per day): ${order.car.cost}</p><br/>

            </div>
            <div class="col-lg-6">
                <p class="h3"><fmt:message key="user"/></p><br/>

                <p>username: ${order.user.username}</p><br/>

                <p>passport: ${order.passport}</p><br/>
            </div>
        </div>
        <div class="row">
            <form class="form-group" action="${pageContext.request.contextPath}/createRepairOrder" method="post">
                <label for="check"><fmt:message key="check"/>: <input type="number" name="id" value="${order.id}"
                                                                      id="check" hidden></label><br/>

                <div class="col-lg-3">
                    <label for="startDate"><fmt:message key="order.startDate"/> : </label>
                </div>
                <div class="col-lg-9">
                    <input type="date" required="" class="form-control" id="startDate" name="startDate">
                </div>
                <div class="col-lg-3">
                    <label for="finishDate"><fmt:message key="order.finishDate"/> : </label>
                </div>
                <div class="col-lg-9">
                    <input type="date" required="" class="form-control" id="finishDate" name="finishDate">
                </div>
                <label><fmt:message key="check.description"/> : </label><br/>
                <textarea rows="4" class="form-control" name="description"></textarea><br/>
                <label for="cost"><fmt:message key="car.cost"/> : </label>
                <input type="number" name="cost" class="form-control" id="cost"/>
                <button class="btn btn-primary" type="submit"><fmt:message key="manager.button.createOrder"/></button>
            </form>
            <div>
                <a href="${pageContext.request.contextPath}/return">
                    <button class="btn btn-danger"><fmt:message key="manager.button.return"/></button>
                </a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
