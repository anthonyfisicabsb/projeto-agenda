function removercampotelefone() {
    var localDeRemocao = $("#grupotelefone");
    var botao = $("#addfone");
    var listaCampos = document.getElementsByName("telefones");

    botao.remove();

    if (listaCampos.length > 1)
        listaCampos[listaCampos.length - 1].remove();

    localDeRemocao.append('<div class="input-group-append" id="addfone">' +
        '<button class="btn btn-primary" type="button" onclick="adicionarcampotelefone();">+ Telefone</button> &nbsp;' +
        '<button class="btn btn-danger" type="button" onclick="removercampotelefone();">- Telefone</button>' + '</div>');
}

function removercampoemail() {
    var localDeRemocao = $("#grupoemail");
    var botao = $("#addemail");
    var listaCampos = document.getElementsByName("email");

    botao.remove();

    if (listaCampos.length > 1)
        listaCampos[listaCampos.length - 1].remove();


    localDeRemocao.append('<div class="input-group-append" id="addemail">' +
        '<button class="btn btn-primary" type="button" onclick="adicionarcampoemail();">+ Email</button>&nbsp;' +
        '<button class="btn btn-danger" type="button" onclick="removercampoemail();">- Email</button>' + '</div>');
}

function removercampourl() {
    var localDeRemocao = $("#grupourl");
    var botao = $("#addurl");
    var listaCampos = document.getElementsByName("url");

    botao.remove();

    if (listaCampos.length > 1)
        listaCampos[listaCampos.length - 1].remove();

    localDeRemocao.append('<div class="input-group-append" id="addurl">' +
        '<button class="btn btn-primary" type="button" onclick="adicionarcampourl();">+ URL</button>&nbsp;' +
        '<button class="btn btn-danger" type="button" onclick="removercampourl();">- URL</button>' + '</div>');
}

function removercamporedesocial() {
    var localDeRemocao = $("#gruporedesocial");
    var botao = $("#addredesocial");
    var listaCampos = document.getElementsByName("redesocial");

    botao.remove();

    if (listaCampos.length > 1)
        listaCampos[listaCampos.length - 1].remove();

    localDeRemocao.append('<div class="input-group-append" id="addredesocial">' +
        '<button class="btn btn-primary" type="button" onclick="adicionarcamporedesocial();">+ Rede Social</button>&nbsp;' +
        '<button class="btn btn-danger" type="button" onclick="removercamporedesocial();">- Rede Social</button>' + '</div>');
}