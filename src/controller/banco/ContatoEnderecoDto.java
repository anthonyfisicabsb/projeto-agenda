package controller.banco;

import model.Contato;
import model.Endereco;

public class ContatoEnderecoDto {
    private Contato contato;
    private Endereco endereco;

    public ContatoEnderecoDto() {
        contato = new Contato();
        endereco = new Endereco();
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}