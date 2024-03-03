import java.io.Serializable;
import java.time.LocalDate;

public abstract class Artigos implements Serializable {

    public enum Estado{
        Novo,
        Usado
    }
    private Estado estado;
    private String danos;
    private int nDonos;
    private String descricao;
    private String marca;
    private String codigo;
    private String user_id;
    private Transportadora transportadora;
    private double preco;
    private double preco_desconto;
    private LocalDate data_venda;

    public Artigos() {
        estado = null;
        danos = null;
        descricao = null;
        marca = null;
        nDonos = 0;
        codigo = "";
        user_id = "";
        transportadora = null;
        preco = 0;
        preco_desconto = 0;
        data_venda = null;
    }

    public Artigos(Estado estado, String danos,int nDonos, String descricao, String marca, String codigo, String user_id, Transportadora transportadora, double preco, double preco_desconto) {
        this.estado = estado;
        this.danos = danos;
        this.descricao = descricao;
        this.marca = marca;
        this.codigo = codigo;
        this.user_id = user_id;
        this.transportadora = transportadora;
        this.nDonos = nDonos;
        this.preco = preco;
        this.preco_desconto = preco_desconto;
        this.data_venda = null;
    }

    public Artigos(Artigos a) {
        this.estado = a.getEstado();
        this.danos = a.getDanos();
        this.descricao = a.getDescricao();
        this.marca = a.getMarca();
        this.codigo = a.getCodigo();
        this.user_id = a.getUser_id();
        this.transportadora = a.getTransportadora();
        this.nDonos = a.getnDonos();
        this.preco = a.getPreco();
        this.preco_desconto = a.getPrecoDesconto();
        this.data_venda = a.getData_venda();
    }

    public Estado getEstado() {
        return estado;
    }

    public String getUser_id() { return user_id; }

    public Transportadora getTransportadora() { return transportadora; }

    public String getDanos() {
        return danos;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getMarca() {
        return marca;
    }

    public String getCodigo() {
        return codigo;
    }

    public double getPreco() {
        return preco;
    }

    public double getPrecoDesconto() {
        return preco_desconto;
    }

    public int getnDonos() {
        return nDonos;
    }

    public LocalDate getData_venda() {
        return data_venda;
    }

    public void setEstado(int estado) {
        switch (estado) {
            case 0: {this.estado = Estado.Novo;break;}
            case 1: {this.estado = Estado.Usado;break;}
        }
    }
    public void setDanos(String danos) {
        this.danos = danos;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setUser_id(String user_id) { this.user_id = user_id; }

    public void setTransportadora(Transportadora transportadora) { this.transportadora = transportadora; }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setPrecoDesconto(double preco_desconto) {
        this.preco_desconto = preco_desconto;
    }

    public void setnDonos(int nDonos) {
        this.nDonos = nDonos;
    }

    public void setData_venda(LocalDate data_venda) {
        this.data_venda = data_venda;
    }

    public abstract Artigos clone();

    public boolean equals(Object obj) {
        if(obj==this)
            return true;
        if(obj==null || obj.getClass() != this.getClass())
            return false;
        Artigos le = (Artigos) obj;
        return  le.getEstado() == this.estado &&
                le.getDanos().equals(this.danos) &&
                le.getDescricao().equals(this.descricao) &&
                le.getMarca().equals(this.marca) &&
                le.getCodigo().equals(this.codigo) &&
                le.getUser_id().equals(this.user_id) &&
                le.getData_venda().equals(this.data_venda) &&
                le.getTransportadora().equals(this.transportadora) &&
                le.getPreco() == this.preco &&
                le.getPrecoDesconto() == this.preco_desconto &&
                le.getnDonos() == this.nDonos;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Artigo:: {");
        sb.append(" Estado: ").append(this.estado);
        sb.append(" Danos: ").append(this.danos);
        sb.append(" NºDonos: ").append(this.nDonos);
        sb.append(" Descrição: ").append(this.descricao);
        sb.append(" Marca: ").append(this.marca);
        sb.append(" Código: ").append(this.codigo);
        sb.append(" User_id: ").append(this.user_id);
        sb.append(" Transportadora: ").append(this.transportadora);
        sb.append(" Data de venda: ").append(this.data_venda);
        sb.append(" Preço: ").append(this.preco);
        sb.append(" Preço com Desconto: ").append(this.preco_desconto).append("}");
        return sb.toString();
    }

    public abstract void calculaDesconto (LocalDate date);
}