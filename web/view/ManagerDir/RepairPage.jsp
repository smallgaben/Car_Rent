<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<%@include file="/view/jspf/head.jspf"%>
<body>
<div class="panel panel-primary col-lg-offset-2 col-lg-8">
  <div class="panel-heading">
    <p class="h3">Repair Page</p>
  </div>
  <div class="panel-body">
    <div class="row">
      <div class="col-lg-6">
        <p class="h3">Car</p><br/>

        <p>mark: ${check.order.car.mark}</p><br/>

        <p>name: ${check.order.car.name}</p><br/>

        <p>class: ${check.order.car.carClass.name}</p><br>

        <p>cost (per day): ${check.order.car.cost}</p><br/>

      </div>
      <div class="col-lg6">
        <p class="h3"> User</p><br/>
        <p>username: ${check.order.user.username}</p><br/>
        <p>passport: ${check.order.passport}</p><br/>
      </div>
    </div>
    <div class="row">
      <form class="form-group" action="${pageContext.request.contextPath}/createRepairOrder">
        <label for="check">Check: <input type="number" name="id" value="${check.order.id}" id="check" hidden></label><br/>
        <div class="col-lg-3">
          <label for="startDate">Start Date: </label>
        </div>
        <div class="col-lg-9">
          <input type="date" required="" class="form-control" id="startDate" name="startDate">
        </div>
        <div class="col-lg-3">
          <label for="finishDate">Finish Date: </label>
        </div>
        <div class="col-lg-9">
          <input type="date" required="" class="form-control" id="finishDate" name="finishDate">
        </div>
        <label>Description: </label><br/>
        <textarea rows="4" class="form-control" name="description"></textarea><br/>
        <label for="cost">Cost: </label>
        <input type="number" name="cost" class="form-control" id="cost"/>
        <button class="btn btn-primary" type="submit">Create Order</button>
      </form>
      <div>
        <a href="${pageContext.request.contextPath}/orderList">
          <button class="btn btn-danger">Return</button>
        </a>
      </div>
    </div>
  </div>
</div>
</body>
</html>
