<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale"/>
<div class="modal fade" id="createTicketModal" tabindex="-1" aria-labelledby="createTicketModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5 text-center" id="createTicketModalLabel">
          <fmt:message key="modal.create.ticket.header"/>
        </h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form method="post" action="controller" enctype="multipart/form-data">
          <input type="hidden" name="action" value="create_ticket">
          <input type="hidden" name="price" value="${requestScope.ship.route.price}">
          <input type="hidden" name="shipId" value="${requestScope.ship.id}">
          <div class="mb-2">
            <tag:inputField fieldName="passengersCount" entity="create.ticket" labelKey="common.passengersCount"
                            width="auto" type="number" placeholder="modal.create.ticket.passengersCount.placeholder"/>
          </div>
          <div class="input-group mb-2">
            <label class="input-group-text"><fmt:message key="common.documents"/></label>
            <input type="file" class="form-control" name="document">
          </div>

          <div class="row justify-content-center mt-3">
            <div class="col">
              <button type="button" class="btn btn-secondary w-100" data-bs-dismiss="modal">
                <fmt:message key="common.button.close"/>
              </button>
            </div>
            <div class="col">
              <button type="submit" class="btn btn-primary w-100">
                <fmt:message key="common.button.order"/>
              </button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>