package model;

public class Endereco {

    private String rua;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private int CEP;
    private String pais;

    /**
     * @return
     */
    public String getPais() {
        return pais;
    }

    /**
     * @param pais
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * @return
     */
    public String getRua() {
        return rua;
    }

    /**
     * @param rua
     */
    public void setRua(String rua) {
        this.rua = rua;
    }

    /**
     * @return
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     * @param complemento
     */
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    /**
     * @return
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * @param bairro
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * @return
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * @param cidade
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * @return
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return
     */
    public int getCEP() {
        return CEP;
    }

    /**
     * @param CEP
     */
    public void setCEP(int CEP) {
        this.CEP = CEP;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return rua + " " + complemento + " " + bairro + " " + cidade + " " + estado + " " + pais + "," + CEP;
    }
}