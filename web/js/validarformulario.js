/**
 *
 */
function validarformulario() {
    var nome = document.getElementById("nome").value;
    var nomefonetico = document.getElementById("nomefonetico").value;
    var sobrenome = document.getElementById("sobrenome").value;
    var sobrenomefonetico = document.getElementById("sobrenomefonetico").value;
    var empresa = document.getElementById("empresa").value;
    var empresafonetico = document.getElementById("empresafonetico").value;
    var datanascimento = document.getElementById("datanascimento").value;

    if (nome == "" || nomefonetico == "" || sobrenome == "" || sobrenomefonetico == "" || empresa == "" || empresafonetico == "" || datanascimento == "") {
        alert("Preencha todos os campos");
        return false;
    }

    return true;
}