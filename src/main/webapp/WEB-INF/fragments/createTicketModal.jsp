<div class="modal fade" id="createTicketModal" tabindex="-1" aria-labelledby="createTicketModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5 text-center" id="createTicketModalLabel">Order cruise</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form method="post" action="controller">
          <input type="hidden" name="action" value="create_ticket">
          <input type="hidden" name="price" value="${requestScope.ship.route.price}">
          <input type="hidden" name="shipId" value="${requestScope.ship.id}">
          <div class="input-group mb-2">
            <label class="input-group-text">Passengers count</label>
            <input type="number" class="form-control" name="passengersCount" required>
          </div>
          <div class="input-group mb-2">
            <label class="input-group-text">Documents</label>
            <input type="file" class="form-control" name="documents">
          </div>

          <div class="row justify-content-center mt-3">
            <div class="col">
              <button type="button" class="btn btn-secondary w-100" data-bs-dismiss="modal">Close</button>
            </div>
            <div class="col">
              <button type="submit" class="btn btn-primary w-100">Order</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>