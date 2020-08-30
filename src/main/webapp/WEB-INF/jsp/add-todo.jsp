<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class="container">
    <form name='f' action="save-todo" method='POST'>

        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3>Add Todo</h3>
            </div>
        </div>

        <div class="panel-body">
            <table>
                <tr>
                    <td>Description:</td>
                    <td><input type='text' name='desc' value='${Todo.desc}'></td>
                    <td><input name="submit" type="submit" value="submit" class="btn btn-primary btn-md"/></td>
                </tr>
            </table>
        </div>

    </form>
</div>


