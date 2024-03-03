import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Encomenda implements Serializable {
    public enum Dimensao {
        Pequena,
        Media,
        Grande
    }
    public enum Estado {
        Pendente,
        Finalizada,
        Expedida
    }
    private String codigo;
    private double preco;
    private Dimensao dimensao;
    private Estado estado;
    private LocalDate data;
    private List<Artigos> lista;
    private Transportadora transportadora;

    public Encomenda(){
        codigo = "";
        preco = 0;
        dimensao = null;
        estado = null;
        data = null;
        lista = new ArrayList<>();
        transportadora = null;
    }

    public Encomenda(String codigo, double preco, Dimensao dimensao, Estado estado, LocalDate data, List<Artigos> lista, Transportadora transportadora){
        this.codigo = codigo;
        this.preco = preco;
        this.dimensao = dimensao;
        this.estado = estado;
        this.data = data;
        this.lista = lista.stream().map(e -> e.clone()).collect(Collectors.toList());
        this.transportadora = transportadora;
    }

    public Encomenda(Encomenda x){
        this.codigo = x.getCodigo();
        this.preco = x.getPrecoE();
        this.dimensao = x.getDimensao();
        this.estado = x.getEstadoE();
        this.data = x.getData();
        this.lista = x.getLista();
        this.transportadora = x.getTransportadora();
    }

    public String getCodigo() { return codigo; }

    public double getPrecoE(){
        return preco;
    }

    public Dimensao getDimensao(){
        return dimensao;
    }

    public Estado getEstadoE(){
        return estado;
    }

    public LocalDate getData(){
        return data;
    }

    public List<Artigos> getLista(){
        return this.lista.stream().map(e -> e.clone()).collect(Collectors.toList());
    }

    public Transportadora getTransportadora() { return transportadora; }

    public void setCodigo(String codigo) { this.codigo = codigo; }

    public void setPrecoE(double preco){
        this.preco = preco;
    }

    public void setDimensao(Dimensao dimensao){
        this.dimensao = dimensao;
    }

    public void setEstadoE(Estado estado){
        this.estado = estado;
    }

    public void setData(LocalDate data){
        this.data = data;
    }

    public void setLista(List<Artigos> lista){
        for (Artigos a : lista){
            this.lista.add(a.clone());
        }
    }

    public void setTransportadora(Transportadora transportadora) { this.transportadora = transportadora; }

    public Encomenda clone(){
        return new Encomenda(this);
    }

    public boolean equals(Object obj) {
        if(obj==this)
            return true;
        if(obj==null || obj.getClass() != this.getClass())
            return false;
        Encomenda x = (Encomenda) obj;
        return x.getCodigo().equals(this.codigo) &&
                x.getPrecoE() == this.preco &&
                x.getDimensao().equals(this.dimensao) &&
                x.getEstadoE().equals(this.estado) &&
                x.getData().equals(this.data) &&
                this.lista.equals(x.getLista()) &&
                this.transportadora.equals(x.getTransportadora());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Encomenda:: {");
        sb.append("Código: ").append(this.codigo);
        sb.append("Preço: ").append(this.preco);
        sb.append("Dimensão: ").append(this.dimensao);
        sb.append("Estado: ").append(this.estado);
        sb.append("Data: ").append(this.data);
        sb.append("Lista de Artigos:").append(this.lista);
        sb.append("Transportadora: ").append(this.transportadora).append("}");
        return sb.toString();
    }

    public double calculaPreco(){
        int r = 0;
        double rendeu = 0;
        for (Artigos a : this.lista){
            if(a.getEstado().equals(Artigos.Estado.Novo)){
                r += a.getPrecoDesconto() + 0.5; // + taxa da transportadora
                rendeu += 0.5;
            }
            else{
                r += a.getPrecoDesconto() + 0.25; // + taxa da transportadora
                rendeu += 0.25;
            }
        }
        if(this.dimensao == Dimensao.Grande) r += this.transportadora.getValorBaseGra();
        if(this.dimensao == Dimensao.Media) r += this.transportadora.getValorBaseMed();
        if(this.dimensao == Dimensao.Pequena) r += this.transportadora.getValorBasePeq();
        this.preco = r;
        return rendeu;
    }
}

