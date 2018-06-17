<%--
  Created by IntelliJ IDEA.
  User: anthonylouis
  Date: 15/06/18
  Time: 18:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Alterar Contato</title>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="js/validarformulario.js"></script>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col">
            <h1>Alterar Contato</h1>
        </div>
    </div>
    <hr>
    <div class="row">
        <div class="col">
            <form action="AlterarContato" method="post">
                <div class="form-group">
                    <label for="nome">Nome:</label>
                    <input type="text" class="form-control" name="nome" id="nome" value="${contato.nome}">
                </div>
                <div class="form-group">
                    <label>Nome Fonético:</label>
                    <input type="text" class="form-control" id="nomefonetico" name="nomefonetico"
                           value="${requestScope.contato.nomeFonetico}">
                </div>
                <div class="form-group">
                    <label for="sobrenome">Sobrenome:</label>
                    <input type="text" class="form-control" id="sobrenome" name="sobrenome"
                           value="${requestScope.contato.sobrenome}">
                </div>
                <div class="form-group">
                    <label for="sobrenomefonetico">Sobrenome Fonético:</label>
                    <input type="text" class="form-control" id="sobrenomefonetico" name="sobrenomefonetico"
                           value="${requestScope.contato.sobrenomeFonetico}">
                </div>
                <div class="form-group">
                    <label for="empresa">Empresa:</label>
                    <input type="text" class="form-control" id="empresa" name="empresa"
                           value="${requestScope.contato.empresa}">
                </div>
                <div class="form-group">
                    <label for="empresafonetico">Empresa Fonético:</label>
                    <input type="text" class="form-control" id="empresafonetico" name="empresafonetico"
                           value="${requestScope.contato.empresaFonetico}">
                </div>
                <div class="form-group" id="grupotelefone">
                    <label>Telefones:</label>
                    <c:forEach var="telefone" items="${requestScope.contato.listaTelefone}">
                        <input type="text" class="form-control" name="telefones" value="${telefone}" required>
                    </c:forEach>
                    <br>
                    <div class="input-group-append" id="addfone">
                        <button class="btn btn-primary" type="button" onclick="adicionarcampotelefone();">+ Telefone
                        </button> &nbsp;
                        <button class="btn btn-danger" type="button" onclick="removercampotelefone();">- Telefone
                        </button>
                    </div>
                </div>
                <div class="form-group" id="grupoemail">
                    <label>Emails:</label>
                    <c:forEach var="email" items="${requestScope.contato.listaEmail}">
                        <input type="email" class="form-control" name="email" value="${email}" required>
                    </c:forEach><br>
                    <div class="input-group-append" id="addemail">
                        <button class="btn btn-primary" type="button" onclick="adicionarcampoemail();">+ Email</button>&nbsp;
                        <button class="btn btn-danger" type="button" onclick="removercampoemail();">- Email</button>
                    </div>
                </div>
                <div class="form-group" id="grupourl">
                    <label>URL's:</label>
                    <c:forEach var="url" items="${requestScope.contato.listaURL}">
                        <input type="text" class="form-control" name="url" value="${url}" required>
                    </c:forEach><br>
                    <div class="input-group-append" id="addurl">
                        <button class="btn btn-primary" type="button" onclick="adicionarcampourl();">+ URL</button>&nbsp;
                        <button class="btn btn-danger" type="button" onclick="removercampourl();">- URL</button>
                    </div>
                </div>
                <div class="form-group">
                    <label for="grupoendereco">Endereços:</label>
                </div>
                <c:forEach var="endereco" items="${requestScope.contato.listaEndereco}">
                    <div class="form-group row" id="grupoendereco">
                        <div class="col">
                            <input type="text" class="form-control" name="rua" value="${endereco.rua}"
                                   placeholder="Rua..."
                                   required>
                        </div>
                        <div class="col">
                            <input type="text" class="form-control" name="bairro" value="${endereco.bairro}"
                                   placeholder="Bairro.." required>
                        </div>
                        <div class="col">
                            <input type="text" class="form-control" name="cidade" value="${endereco.cidade}"
                                   placeholder="Cidade.." required>
                        </div>
                        <div class="w-100"></div>
                        <div class="col">
                            <input type="text" class="form-control" name="estado" value="${endereco.estado}"
                                   placeholder="Estado.." required>
                        </div>
                        <div class="col">
                            <input type="text" class="form-control" name="complemento" value="${endereco.complemento}"
                                   placeholder="Complemento.." required>
                        </div>
                        <div class="col">
                            <input type="text" class="form-control" name="cep" value="${endereco.CEP}"
                                   placeholder="CEP..(Apenas números)" pattern="[0-9]+$" required>
                        </div>
                        <div class="col">
                            <input type="text" class="form-control" name="pais" value="${endereco.pais}"
                                   placeholder="Pais"
                                   required>
                        </div>
                    </div>
                </c:forEach>
                <div class="form-group" id="addendereco">
                    <button class="btn btn-primary" type="button" onclick="adicionarcampoendereco();">+ Endereco
                    </button>
                </div>
                <div class="form-group">
                    <label for="datanascimento">Data de Nascimento:</label>
                    <input type="text" class="form-control" id="datanascimento" name="datanascimento"
                           pattern="(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\d\d">
                </div>
                <div class="form-group" id="gruporedesocial">
                    <label>Redes Sociais:</label>
                    <c:forEach var="redesocial" items="${requestScope.contato.listaRedeSocial}">
                        <input type="text" class="form-control" name="redesocial" value="${redesocial}" required>
                    </c:forEach>
                    <div class="input-group-append" id="addredesocial">
                        <button class="btn btn-primary" type="button" onclick="adicionarcamporedesocial();">+ Rede
                            Social
                        </button> &nbsp;
                        <button class="btn btn-danger" type="button" onclick="removercamporedesocial();">- Rede Social
                        </button>
                    </div>
                </div>
                <input type="hidden" name="id" value="${requestScope.contato.idBanco}">
                <br>
                <button type="submit" onclick="return validarformulario();" class="btn btn-success">Enviar</button>
                <button type="reset" class="btn btn-success">Reset</button>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript" src="js/adicionarcampo.js"></script>
<script type="text/javascript" src="js/removercampos.js"></script>
</body>
</html>