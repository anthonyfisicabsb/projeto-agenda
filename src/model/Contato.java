package model;

import java.util.ArrayList;
import java.util.Date;

public class Contato {

    private String nome;
    private String nomeFonetico;
    private String sobrenome;
    private String sobrenomeFonetico;
    private String empresa;
    private String empresaFonetico;
    private ArrayList<String> listaTelefone;
    private ArrayList<String> listaEmail;
    private ArrayList<String> listaURL;
    private ArrayList<Endereco> listaEndereco;
    private Date dataAniversario;
    private ArrayList<String> listaRedeSocial;
    private int idBanco;

    public Contato() {
        listaTelefone = new ArrayList<>();
        listaEmail = new ArrayList<>();
        listaURL = new ArrayList<>();
        listaEndereco = new ArrayList<>();
        listaRedeSocial = new ArrayList<>();
    }

    /**
     * @return
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return
     */
    public String getNomeFonetico() {
        return nomeFonetico;
    }

    /**
     * @param nomeFonetico
     */
    public void setNomeFonetico(String nomeFonetico) {
        this.nomeFonetico = nomeFonetico;
    }

    /**
     * @return
     */
    public String getSobrenome() {
        return sobrenome;
    }

    /**
     * @param sobrenome
     */
    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    /**
     * @return
     */
    public String getSobrenomeFonetico() {
        return sobrenomeFonetico;
    }

    /**
     * @param sobrenomeFonetico
     */
    public void setSobrenomeFonetico(String sobrenomeFonetico) {
        this.sobrenomeFonetico = sobrenomeFonetico;
    }

    /**
     * @return
     */
    public String getEmpresa() {
        return empresa;
    }

    /**
     * @param empresa
     */
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    /**
     * @return
     */
    public String getEmpresaFonetico() {
        return empresaFonetico;
    }

    /**
     * @param empresaFonetico
     */
    public void setEmpresaFonetico(String empresaFonetico) {
        this.empresaFonetico = empresaFonetico;
    }

    /**
     * @return
     */
    public ArrayList<String> getListaTelefone() {
        return listaTelefone;
    }

    /**
     * @param listaTelefone
     */
    public void setListaTelefone(ArrayList<String> listaTelefone) {
        this.listaTelefone = listaTelefone;
    }

    /**
     * @return
     */
    public ArrayList<String> getListaEmail() {
        return listaEmail;
    }

    /**
     * @param listaEmail
     */
    public void setListaEmail(ArrayList<String> listaEmail) {
        this.listaEmail = listaEmail;
    }

    /**
     * @return
     */
    public ArrayList<String> getListaURL() {
        return listaURL;
    }

    /**
     * @param listaURL
     */
    public void setListaURL(ArrayList<String> listaURL) {
        this.listaURL = listaURL;
    }

    /**
     * @return
     */
    public ArrayList<Endereco> getListaEndereco() {
        return listaEndereco;
    }

    /**
     * @param listaEndereco
     */
    public void setListaEndereco(ArrayList<Endereco> listaEndereco) {
        this.listaEndereco = listaEndereco;
    }

    /**
     * @return
     */
    public Date getDataAniversario() {
        return dataAniversario;
    }

    /**
     * @param dataAniversario
     */
    public void setDataAniversario(Date dataAniversario) {
        this.dataAniversario = dataAniversario;
    }

    /**
     * @return
     */
    public ArrayList<String> getListaRedeSocial() {
        return listaRedeSocial;
    }

    /**
     * @param listaRedeSocial
     */
    public void setListaRedeSocial(ArrayList<String> listaRedeSocial) {
        this.listaRedeSocial = listaRedeSocial;
    }

    /**
     * @return
     */
    public int getIdBanco() {
        return idBanco;
    }

    /**
     * @param idBanco
     */
    public void setIdBanco(int idBanco) {
        this.idBanco = idBanco;
    }
}