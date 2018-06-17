<%--
  Created by IntelliJ IDEA.
  User: anthonylouis
  Date: 15/06/18
  Time: 18:14
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/telaprincipal.css" rel="stylesheet">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <title>Agenda Web</title>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col" align="center">
            <h1>Agenda Web</h1>
        </div>
    </div>
    <hr>
    <div class="row">
        <div class="col">
            <div class="input-group mb-3">
                <input type="text" class="form-control" placeholder="Pesquisar contato" aria-label="procurarContato"
                       aria-describedby="basic-addon2">
                <div class="input-group-append">
                    <button class="btn btn-outline-primary" type="button">Pesquisar</button>
                </div>
            </div>
        </div>
    </div>
    <c:if test="${requestScope.listacontatos.size() > 0}">
    <div class="row">
        <div class="table table-responsive">
            <table class="table table-hover">
                <thead>
                <tr>
                    <td align=center>Contato</td>
                    <td align=center>Opções</td>
                </tr>
                </thead>
                <tbody id="minhaTabela">

                <c:forEach var="contato" items="${requestScope.listacontatos}">
                <tr>

                    <td align=center>
                        <button class="accordion">${contato.nome} ${contato.sobrenome}
                        </button>
                        <div class="panel">
                            Nome Fonetico: ${contato.nomeFonetico}</br>
                            Sobrenome Fonetico: ${contato.sobrenomeFonetico}<br>
                            Empresa: ${contato.empresa}<br>
                            Empresa Fonetico: ${contato.empresaFonetico}<br>
                            Data de Nascimento: <fmt:formatDate pattern="dd/MM/yyyy" value="${contato.dataAniversario}" /><br/>
                            <c:forEach var="url" items="${contato.listaURL}">
                            Url: ${url}<br>
                            </c:forEach>
                            <c:forEach var="email" items="${contato.listaEmail}">
                            Email: ${email} <br>
                            </c:forEach>
                            <c:forEach var="telefone" items="${contato.listaTelefone}">
                            Telefone: ${telefone}<br>
                            </c:forEach>
                            <c:forEach var="redesocial" items="${contato.listaRedeSocial}">
                            Rede Social: ${redesocial}<br>
                            </c:forEach>
                            <c:forEach var="endereco" items="${contato.listaEndereco}">
                            Endereco: ${endereco} <br>
                            </c:forEach>
                        </div>
                    </td>
                    <td align=center>
                        <form action="ExcluirContato" method="post">
                            <input type="hidden" value="${contato.idBanco}" name="idcontato"/>
                            <input type="submit" class="btn btn-success btn-sm" value="Remover"/>
                        </form>
                        <form action="ContatoParaAlterar" method="post">
                            <input type="hidden" value="${contato.idBanco}" name="idcontato"/>
                            <input type="submit" class="btn btn-success btn-sm" value="Alterar"/>
                        </form>
                    </td>
                </tr>

                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="col pull-center">
            <ul class="pagination" id="myPager"></ul>
        </div>
    </div>
    <br>
    </c:if>
    <div class="row">
        <div class="col text-center">
            <a href="adicionarcontato.html">
                <button type="button" class="btn btn-primary btn-sm">Adicionar Contato</button>
            </a>
        </div>
    </div>
</div>
<script type="text/javascript" src="js/paginacao.js"></script>
<script type="text/javascript" src="js/adicionarpanel.js"></script>
</body>
</html>