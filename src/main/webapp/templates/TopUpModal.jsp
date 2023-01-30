<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="modal fade" id="topUpModal" tabindex="-1" aria-labelledby="topUpModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="topUpModalLabel">Top up balance</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form method="post" action="controller">
                    <input type="hidden" name="action" value="top_up_balance">
                    <div class="input-group mb-2">
                        <label class="input-group-text w-50">Amount</label>
                        <input type="number" class="form-control" name="amount" required>
                    </div>
                    <div class="row justify-content-center mt-3">
                        <div class="col">
                            <button type="button" class="btn btn-secondary w-100" data-bs-dismiss="modal">Close</button>
                        </div>
                        <div class="col">
                            <button type="submit" class="btn btn-primary w-100">Top up</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
