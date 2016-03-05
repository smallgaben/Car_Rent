<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="/view/jspf/head.jspf" %>
<body>
<div class="col-lg-12">
    <div class="col-lg-offset-2 col-lg-8 panel panel-primary">
        <div class="panel-heading">
            Enter Information
        </div>
        <div class="panel-body">
            <form action="${pageContext.request.contextPath}/rentCar" class="form-control">
                <div class="row ">
                    <div class="col-lg-3 list-inline">
                        <div class="col-lg-4">
                            <input class="form-control" name="id" value="${id}" readonly>
                        </div>
                        <div class="col-lg-8">
                            <p>Mark: ${mark}</p>
                        </div>
                    </div>
                    <div class="col-lg-3">
                        <p>Name: ${name}</p>
                    </div>
                    <div class="col-lg-3">
                        <p>Cost(per day): ${cost}</p>
                    </div>
                    <div class="col-lg-3">
                        <p>Class: ${carClass}</p>
                    </div>
                </div>
                <div class="row list inline">
                    <div class="col-lg-3">
                        <label for="passport">Passport code:</label>
                    </div>
                    <div class="col-lg-9">
                        <input type="number" required="" name="passport" class="form-control" id="passport">
                    </div>
                </div>
                <div class="row list-inline">
                    <label for="driver"><input type="checkbox" id="driver" class="form-control" name="driver">Driver/No Driver</label>
                </div>
                <div class="row list-inline">
                    <div class="col-lg-3">
                        <label for="startDate">Start Date: </label>
                    </div>
                    <div class="col-lg-9">
                        <input type="date" required="" class="form-control" id="startDate" name="startDate">
                    </div>
                </div>
                <div class="row list-inline">
                    <div class="col-lg-3">
                        <label for="finishDate">Finish Date: </label>
                    </div>
                    <div class="col-lg-9">
                        <input type="date" required="" class="form-control" id="finishDate" name="finishDate">
                    </div>
                </div>
                <div class="row col-lg-3 center-block">
                    <button type="submit" class="btn btn-primary">Confirm</button>
                </div>
            </form>

        </div>

    </div>
    <div class="col-lg-3">
    <a href="${pageContext.request.contextPath}/carList">
        <button class="btn btn-danger">Return</button>
    </a>
</div>
</div>
</body>
</html>
