<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class="container">
    <div>
        <a type="button" class="btn btn-primary btn-md" href="${pageContext.servletContext.contextPath}/add-todo">Add Todo</a>
    </div>
    <br>
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3>Your Todos</h3>
        </div>
            <div class="panel-body">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th width="20%">Completed</th>
                    <th width="20%">Description</th>
                    <th width="20%">Last Updated</th>
                    <th width="20%"></th>
                </tr>
                </thead>
                <tbody>

                <form:form name='f' action="update-todo" method='POST' modelAttribute="todoListWrapper">
                    <c:forEach items="${todoListWrapper.todos}" var="todo" varStatus="i">
                        <tr>
                            <td width="20%"><form:checkbox path="todos[${i.index}].done"/></td>
                            <td width="20%"><form:input path="todos[${i.index}].desc" type="text"/></td>
                            <td width="20%"><fmt:formatDate value="${todo.lastUpdated}" pattern="dd/MM/yyyy"/></td>
                            <td width="20%"><a type="button" class="btn btn-warning" href="${pageContext.servletContext.contextPath}/remove-todo?id=${todo.id}">Delete</a></td>
                            <td style="display:none"><form:input path="todos[${i.index}].id" type="hidden"/></td>
                        </tr>
                    </c:forEach>

                    <input name="submit" type="submit" class="btn btn-primary btn-md" value="Save Todos"/>
                </form:form>

                </form>
                </tbody>
            </table>
        </div>
    </div>
</div>

