<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale"/>
<div class="modal fade" id="createRouteModal" tabindex="-1" aria-labelledby="createRouteModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5 text-center" id="createRouteModalLabel">
                    <fmt:message key="route.modal.createRoute"/>
                </h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form method="post" action="controller">
                    <input type="hidden" name="action" value="create_route">
                    <div class="mb-2">
                        <tag:inputField fieldName="name" entity="create.route" labelKey="table.name" width="25"
                                        type="text" placeholder="route.modal.name.placeholder"/>
                    </div>
                    <div class="mb-2">
                        <tag:inputField fieldName="startDate" entity="create.route" labelKey="table.startDate" width="25"
                                        type="date"/>
                    </div>
                    <div class="mb-2">
                        <tag:inputField fieldName="endDate" entity="create.route" labelKey="table.endDate" width="25"
                                        type="date"/>
                    </div>
                    <div class="mb-2">
                        <tag:inputField fieldName="price" entity="create.route" labelKey="table.price" width="25"
                                        type="number" placeholder="route.modal.price.placeholder"/>
                    </div>
                    <div class="row justify-content-center mt-3">
                        <div class="col">
                            <button type="button" class="btn btn-secondary w-100" data-bs-dismiss="modal">
                                <fmt:message key="table.button.close"/>
                            </button>
                        </div>
                        <div class="col">
                            <button type="submit" class="btn btn-primary w-100">
                                <fmt:message key="table.button.create"/>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>