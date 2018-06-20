<%--
  Created by IntelliJ IDEA.
  User: anthonylouis
  Date: 20/06/18
  Time: 03:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/telaprincipal.css" rel="stylesheet">
    <title>Agenda Web</title>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col" align="center">
            <h1>Resultados da Pesquisa</h1>
        </div>
    </div>
    <hr>
    <div class="row">
        <div class="col">
            <form method="get" action="PesquisaContato">
                <div class="input-group mb-3">
                    <input type="text" max="10" class="form-control" placeholder="Pesquisar contato"
                           aria-label="procurarContato" , name="barrapesquisa"
                           aria-describedby="basic-addon2" , required>
                    <div class="input-group-append">
                        <button class="btn btn-outline-primary" type="submit">Pesquisar</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <c:if test="${requestScope.resultado.size() > 0}">
        <div class="row">
            <div class="table table-responsive">
                <table class="table table-hover">
                    <tbody>

                    <c:forEach var="contato" items="${requestScope.resultado}">
                        <tr>
                            <td align=center>${contato}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <br>
    </c:if>
    <c:if test="${requestScope.resultado.size() == 0}">
        <div class="row">
            <div class="table table-responsive">
                <table class="table table-hover">
                    <tbody>
                    <tr>
                        <td>Não foi encontrado nenhum resultado</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <br>
    </c:if>
    <div class="row">
        <div class="col text-center">
            <a href="startagenda.html">
                <button type="button" class="btn btn-primary btn-sm">Tela Incial</button>
            </a>
        </div>
    </div>
</div>
</body>
</html>