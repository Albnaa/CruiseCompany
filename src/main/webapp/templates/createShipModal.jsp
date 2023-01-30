<div class="modal fade" id="createShipModal" tabindex="-1" aria-labelledby="createShipModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5 text-center" id="createShipModalLabel">Create ship</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form method="post" action="controller">
          <input type="hidden" name="action" value="create_ship">
          <div class="input-group mb-2">
            <span class="input-group-text w-25">Name</span>
            <input type="text" class="form-control" name="shipName" required>
          </div>
          <div class="input-group mb-2">
            <span class="input-group-text w-25">Capacity</span>
            <input type="number" class="form-control" name="shipCapacity" required>
          </div>
          <div class="input-group mb-2">
            <span class="input-group-text w-25">Visited ports</span>
            <input type="number" class="form-control" name="shipVisitedPorts" required>
          </div>
          <div class="input-group mb-2">
            <span class="input-group-text w-25">Staff</span>
            <input type="number" class="form-control" name="shipStaff" required>
          </div>
          <div class="row justify-content-center mt-3">
            <div class="col">
              <button type="button" class="btn btn-secondary w-100" data-bs-dismiss="modal">Close</button>
            </div>
            <div class="col">
              <button type="submit" class="btn btn-primary w-100">Create</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>