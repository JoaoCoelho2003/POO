import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Utilizador implements Serializable {
    private int nif;
    private String id,nome,morada,email;
    private double preco_vendidos;
    private Map <String, Artigos> venda,vendeu;
    private List <Artigos> carrinho;
    private Map <String, Encomenda> encomendas;
    private List <String> faturas;

    public Utilizador() {
        id = "";
        nif = -1;
        nome = morada = email = null;

        preco_vendidos = 0;
        venda = new HashMap<>();
        vendeu = new HashMap<>();
        carrinho = new ArrayList<>();
        faturas = new ArrayList<>();
        encomendas = new HashMap<>();
    }

    public Utilizador (String id, int nif, String nome,String morada, String email) {
        this.id = id;
        this.nif = nif;
        this.nome = nome;
        this.morada = morada;
        this.email = email;

        preco_vendidos = 0;
        venda = new HashMap<>();
        vendeu = new HashMap<>();
        carrinho = new ArrayList<>();
        faturas = new ArrayList<>();
        encomendas = new HashMap<>();
    }

    public Utilizador (Utilizador a) {
        this.id = a.getId();
        this.nome = a.getNome();
        this.nif = a.getNif();
        this.nome = a.getNome();
        this.email = a.getEmail();
        this.morada = a.getMorada();
        this.preco_vendidos = a.getPreco_vendidos();
        this.vendeu = a.getVendeu();
        this.venda = a.getVenda();
        this.carrinho = a.getCarrinho();
        this.faturas = a.getFaturas();
        this.encomendas = a.getEncomendas();
    }

    public double getPreco_vendidos() {
        return preco_vendidos;
    }

    public String getId() {
        return id;
    }

    public int getNif() {
        return nif;
    }

    public String getEmail() {
        return email;
    }

    public String getMorada() {
        return morada;
    }

    public String getNome() {
        return nome;
    }

    public List<Artigos> getCarrinho () {
        return this.carrinho.stream().map(a -> a.clone()).collect(Collectors.toList());
    }

    public List<String> getFaturas () {
        return new ArrayList<>(this.faturas);
    }

    public void setNome (String nome1) {
        nome = nome1;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPreco_vendidos(double preco_vendidos) {
        this.preco_vendidos = preco_vendidos;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public void setVenda(Map<String,Artigos> venda) {
        Map <String,Artigos> clone = new HashMap<>();
        for (Map.Entry<String, Artigos> a : venda.entrySet())
            clone.put(a.getKey(),a.getValue().clone());
        this.venda = clone;
    }

    public void setVendeu(Map<String,Artigos> vendeu) {
        Map <String,Artigos> clone = new HashMap<>();
        for (Map.Entry<String, Artigos> a : vendeu.entrySet())
            clone.put(a.getKey(),a.getValue().clone());
        this.vendeu = clone;
    }

    public void setCarrinho (List<Artigos> carrinho) {
        List <Artigos> l;
        l = carrinho.stream().map(a -> a.clone()).collect(Collectors.toList());
        this.carrinho = l;
    }

    public void setFaturas (List<String> faturas) {
        this.faturas = new ArrayList<>(faturas);
    }
    public void setEncomendas (Map<String,Encomenda> encomendas) {
        Map <String,Encomenda> clone = new HashMap<>();
        for (Map.Entry<String, Encomenda> a : encomendas.entrySet())
            clone.put(a.getKey(),a.getValue().clone());
        this.encomendas = clone;
    }

    public Map<String,Encomenda> getEncomendas() {
        return this.encomendas.entrySet().stream().collect(Collectors.toMap((e)->e.getKey(), (e)->e.getValue().clone()));
    }

    public Map<String,Artigos> getVenda() {
        return this.venda.entrySet().stream().collect(Collectors.toMap((e)->e.getKey(), (e)->e.getValue().clone()));
    }

    public Map<String,Artigos> getVendeu() {
        return this.vendeu.entrySet().stream().collect(Collectors.toMap((e)->e.getKey(), (e)->e.getValue().clone()));
    }

    public Utilizador clone() {
        return new Utilizador (this);
    }

    public boolean equals(Object obj) {
        if(obj==this)
            return true;
        if(obj==null || obj.getClass() != this.getClass())
            return false;
        Utilizador le = (Utilizador) obj;
        return le.getId().equals(this.id) &&
                le.getEmail().equals(this.email) &&
                le.getMorada().equals(this.morada) &&
                le.getNome().equals(this.nome) &&
                le.getNif() == this.nif &&
                le.getPreco_vendidos() == this.preco_vendidos &&
                this.venda.equals(le.getVenda()) &&
                this.vendeu.equals(le.getVendeu()) &&
                this.carrinho.equals(le.getCarrinho()) &&
                this.encomendas.equals(le.getEncomendas());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Utilizador:: {");
        sb.append("Id: ").append(this.id);
        sb.append("Nome: ").append(this.nome);
        sb.append("Email: ").append(this.email);
        sb.append("Morada: ").append(this.morada);
        sb.append("Nif: ").append(this.nif);
        sb.append("Preco Total Vendido: ").append(this.preco_vendidos);
        sb.append("Lista de Artigos em Venda:").append(this.venda);
        sb.append("Lista de Artigos Vendidos:").append(this.vendeu);
        sb.append("Lista de Artigos no Carrinho:").append(this.carrinho);
        sb.append("Lista de Artigos que Comprou:").append(this.encomendas).append("}");

        return sb.toString();
    }

    public void adicionaArtigosVenda (Artigos a) {
        venda.put(a.getCodigo(),a);
    }

    public void vendaArtigo (String codigo) {
        Artigos a = venda.get(codigo);
        vendeu.put(codigo,a);
        venda.remove(codigo);
    }

    public void adicionaCarrinho (Artigos a) {
        carrinho.add(a);
    }

    public Artigos removeCarrinho (String s) throws VintageException {
        Artigos remover = null;
        for (Artigos a : carrinho) {
            if (a.getCodigo().equals(s)) {
                carrinho.remove(a);
                remover = a;
                break;
            }
        }

        if (remover == null) {
            throw new VintageException("O artigo não pertence ao carrinho");
        }
        return remover;
    }

    public void removeVendeu (String code) {
        Artigos a = vendeu.get(code);
        vendeu.remove(code);
        decrementaValor(a.getPrecoDesconto());
        adicionaArtigosVenda(a);
    }

    public void decrementaValor (double preco) {
        this.preco_vendidos -= preco;
    }

    public double percorreCarrinho(LocalDate data){
        double rendeu = 0, total = 0;
        StringBuilder fatura = new StringBuilder();
        Map<Transportadora, List<Artigos>> enc = this.carrinho.stream().collect(Collectors.groupingBy(a -> a.getTransportadora()));
        for(Map.Entry<Transportadora, List<Artigos>> p : enc.entrySet()) {
            Encomenda e = new Encomenda();
            List<Artigos> l = p.getValue();
            if(l.size() == 1){
                e.setDimensao(Encomenda.Dimensao.Pequena);
            }
            else if(l.size() > 5){
                e.setDimensao(Encomenda.Dimensao.Grande);
            }
            else{
                e.setDimensao(Encomenda.Dimensao.Media);
            }
            String codigo;
            do {
                codigo = Codigos.gerarCodigo();
            } while (this.encomendas.containsKey(codigo));
            e.setCodigo(codigo);
            e.setData(data);
            e.setEstadoE(Encomenda.Estado.Pendente);
            l.forEach(a -> a.setData_venda(data));
            e.setLista(l);
            e.setTransportadora(p.getKey());
            this.encomendas.put(e.getCodigo(),e);
            p.getKey().custoExpedicao(e.getDimensao());
            rendeu = e.calculaPreco();
            total+= e.getPrecoE();

            fatura.append("Encomenda: ").append(e.getCodigo()).append("| Preco Encomenda: ").append(e.getPrecoE());
            fatura.append("| Transportadora: ").append(e.getTransportadora().getTransportadora()).append("| Data: ");
            fatura.append(e.getData()).append("| Artigos: \n");
            l.forEach(a -> fatura.append("\t").append(a.getClass()).append("| Marca: ").append(a.getMarca()).append("| Preco: ").append(a.getPreco()).append("| Preco com Desconto: ").append(a.getPrecoDesconto()).append("\n"));
        }
        fatura.append("Preco Total: ").append(total+"\n");
        addFaturas(fatura.toString());
        return rendeu;
    }

    public void atualizaEncomendas(LocalDate localDate) {
        this.encomendas.forEach((key,value) -> {
            if (value.getEstadoE() != Encomenda.Estado.Finalizada) {
                if (Math.abs(ChronoUnit.DAYS.between(localDate, value.getData())) <= 3)
                    value.setEstadoE(Encomenda.Estado.Expedida);
                else
                    value.setEstadoE(Encomenda.Estado.Finalizada);
            }
        });
    }

    public void aumentaValor (double preco){
        this.preco_vendidos += preco;
    }

    public void addFaturas (String fatura) {this.faturas.add(fatura);}
}