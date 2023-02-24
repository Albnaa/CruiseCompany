<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale"/>
<div class="modal fade" id="createShipModal" tabindex="-1" aria-labelledby="createShipModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5 text-center" id="createShipModalLabel"><fmt:message key="ship.modal.createShip"/></h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form method="post" action="controller" enctype="multipart/form-data">
          <input type="hidden" name="action" value="create_ship">
          <div class="mb-2">
            <tag:inputField fieldName="name" entity="create.ship" labelKey="common.name" width="25" type="text"
                            placeholder="ship.name.placeholder"/>
          </div>
          <div class="mb-2">
            <tag:inputField fieldName="capacity" entity="create.ship" labelKey="common.capacity" width="50" type="number"
                            placeholder="ship.capacity.placeholder"/>
          </div>
          <div class="mb-2">
            <tag:inputField fieldName="visitedPorts" entity="create.ship" labelKey="common.visitedPorts" width="50"
                            type="number" placeholder="ship.visitedPorts.placeholder"/>
          </div>
          <div class="mb-2">
            <tag:inputField fieldName="staff" entity="create.ship" labelKey="common.staff" width="25" type="number"
                            placeholder="ship.staff.placeholder"/>
          </div>
          <div class="input-group mb-2">
            <input type="file" name="image" class="form-control" accept="image/*" required/>
            <span class="input-group-text w-25"><fmt:message key="common.image"/></span>
          </div>
          <div class="mb-2">

          </div>
          <div class="row justify-content-center mt-3">
            <div class="col">
              <button type="button" class="btn btn-secondary w-100" data-bs-dismiss="modal">
                <fmt:message key="common.button.close"/>
              </button>
            </div>
            <div class="col">
              <button type="submit" class="btn btn-primary w-100">
                <fmt:message key="common.button.create"/>
              </button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>