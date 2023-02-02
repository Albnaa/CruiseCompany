<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale"/>

<div class="modal fade" id="topUpModal" tabindex="-1" aria-labelledby="topUpModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="topUpModalLabel"><fmt:message key="user.modal.topUp.header"/></h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form method="post" action="controller">
                    <input type="hidden" name="action" value="top_up_balance">
                    <div class="mb-2">
                        <tag:inputField fieldName="amount" entity="topUp.user" labelKey="common.amount" width="25"
                                        type="number" placeholder="user.modal.topUp.amount.placeholder"/>
                    </div>
                    <div class="row justify-content-center mt-3">
                        <div class="col">
                            <button type="button" class="btn btn-secondary w-100" data-bs-dismiss="modal">
                                <fmt:message key="common.button.close"/>
                            </button>
                        </div>
                        <div class="col">
                            <button type="submit" class="btn btn-primary w-100">
                                <fmt:message key="common.button.topUp"/>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
