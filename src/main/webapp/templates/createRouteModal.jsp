<div class="modal fade" id="createRouteModal" tabindex="-1" aria-labelledby="createRouteModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5 text-center" id="createRouteModalLabel">Create route</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form method="get">
                    <input type="hidden" name="action" value="create_route">
                    <div class="input-group mb-2">
                        <span class="input-group-text w-25">Name</span>
                        <input type="text" class="form-control" name="routeName" required>
                    </div>
                    <div class="input-group mb-2">
                        <span class="input-group-text w-25">Start date</span>
                        <input type="date" class="form-control" name="routeStart" required>
                    </div>
                    <div class="input-group mb-2">
                        <span class="input-group-text w-25">End date</span>
                        <input type="date" class="form-control" name="routeEnd" required>
                    </div>
                    <div class="input-group mb-2">
                        <span class="input-group-text w-25">Price</span>
                        <input type="number" class="form-control" name="price" required>
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