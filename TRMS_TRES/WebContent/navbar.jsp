<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%String path = request.getContextPath() + "/";%>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="<%=path %>employee.jsp"> <%= session.getAttribute("uname") %></a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <!-- <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li -->>
        <li><a href="<%=path %>reimbursement.jsp">Reimbursement</a></li>
        <li><a href="<%=path %>Pending/my">Pending</a></li>
        <%if ((int)session.getAttribute("deptid") == 1 || (int) session.getAttribute("roleid") != 1) 
        {
        %>
        	<li><a href="<%=path %>Pending/others">OtherPending</a></li>
        <%
        }
        %>
        <%--@ session.getAttribute("");--%>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="<%=path%>"index.jsp>Logout</a></li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>