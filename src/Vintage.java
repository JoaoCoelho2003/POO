import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Vintage implements Serializable {
    private LocalDate currentDate;
    private double rendeu;
    private Map<String,Utilizador> utilizadores;
    private Map<String,Artigos> artigos;
    private Map<String,Artigos> artigosDisponiveis;
    private Map<String,Transportadora> transportadoras;

    public Vintage () {
        rendeu = 0;
        utilizadores = new HashMap<>();
        artigos = new HashMap<>();
        artigosDisponiveis = new HashMap<>();
        transportadoras = new HashMap<>();
        currentDate = LocalDate.now();
    }

    public Vintage (LocalDate date) {
        rendeu = 0;
        utilizadores = new HashMap<>();
        artigos = new HashMap<>();
        artigosDisponiveis = new HashMap<>();
        transportadoras = new HashMap<>();
        currentDate = date;
    }

    public Map<String, Utilizador> getUtilizadores() {
        return utilizadores;
    }

    public Map<String, Artigos> getArtigos() {
        return artigos;
    }

    public Map<String, Artigos> getartigosDisponiveis() {
        return artigosDisponiveis;
    }

    public Map<String, Transportadora> getTransportadoras() {
        return transportadoras;
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public void setUtilizadores(Map<String, Utilizador> utilizadores) {
        this.artigos = artigos.entrySet().stream().collect(Collectors.toMap((e)->e.getKey(), (e)->e.getValue().clone()));
    }

    public void setArtigos(Map<String, Artigos> artigos) {
        this.artigos = artigos.entrySet().stream().collect(Collectors.toMap((e)->e.getKey(), (e)->e.getValue().clone()));
    }

    public void setartigosDisponiveis(Map<String, Artigos> artigos) {
        this.artigosDisponiveis = artigos.entrySet().stream().collect(Collectors.toMap((e)->e.getKey(), (e)->e.getValue().clone()));
    }

    public void setTransportadoras (Map<String, Transportadora> transportadoras) {
        this.transportadoras = transportadoras.entrySet().stream().collect(Collectors.toMap((e)->e.getKey(), (e)->e.getValue().clone()));
    }

    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;
    }

    public double getRendeu() {
        return rendeu;
    }

    public void addUtilizador (Utilizador u) throws VintageException {
        if (!utilizadores.containsKey(u.getEmail()))
            utilizadores.put(u.getEmail(), u.clone());
        else {
            throw new VintageException("O email "+ u.getEmail() + " já está a ser utilizado");
        }
    }

    public void addArtigoDisponivel (Artigos a) {
        artigosDisponiveis.put(a.getCodigo(), a);
    }
    public void addArtigo (Artigos a) {
        String codigo;
        do {
            codigo = Codigos.gerarCodigo();
        } while (this.artigos.containsKey(codigo));
        a.setCodigo(codigo);
        artigos.put(codigo, a);
    }

    public void addTransportadora (Transportadora t) {
        transportadoras.put(t.getTransportadora(),t);
    }

    public void removeUtilizador (String email) {
        utilizadores.remove(email);
    }

    public void removeArtigo (String codigo) {
        artigos.remove(codigo);
    }

    public void removeArtigoDisponiveis (String codigo) {
        artigosDisponiveis.remove(codigo);
    }

    public void removeTransportadora (String id) {
        transportadoras.remove(id);
    }

    public void addDays(LocalDate localDate) {
        this.currentDate = localDate;
        this.utilizadores.forEach((key,value) -> {
            value.atualizaEncomendas (localDate);
        });
    }

    public String utilizadorMaisRendeu(LocalDate dateI, LocalDate dateF) {
        String email = null;
        double rendeu = 0, max = -1;
        for (Map.Entry<String, Utilizador> u : utilizadores.entrySet()) {
            for (Map.Entry<String, Artigos> a : u.getValue().getVendeu().entrySet()) {
                    if (a.getValue().getData_venda() != null) {
                        if (dateI != null && dateF != null && dateI.isBefore(a.getValue().getData_venda()) && dateF.isAfter(a.getValue().getData_venda()))
                            rendeu += a.getValue().getPrecoDesconto();
                        else
                            rendeu += a.getValue().getPrecoDesconto();
                    }
            }
            if (rendeu > max) {
                max = rendeu;
                email = u.getValue().getEmail();
            }
            rendeu = 0;
        }
        return email;
    }

    public Transportadora maisFaturacao () {
        double maior = -1;
        Transportadora result = null;
        for (Map.Entry <String,Transportadora> a : this.transportadoras.entrySet()) {
            if (a.getValue().getTotalObtido() > maior) {
                maior = a.getValue().getTotalObtido();
                result = a.getValue();
            }
        }
        return result;
    }

    public List<Encomenda> listarEncomendasVendedor (String email) {
        List<Encomenda> l = new ArrayList<>();
        this.utilizadores.forEach((key,value) -> {
            Map<String, Encomenda> enc = value.getEncomendas();
            enc.forEach((key1,value1) -> {
                for (Artigos a : value1.getLista()) {
                    if (a.getUser_id().equals(email)) {
                        l.add(value1);
                        break;
                    }
                };
            });
        });
        return l;
    }

    public Map <String, Utilizador> maioresVendedores (LocalDate dateI, LocalDate dateF) {
        Map <String,Utilizador> ordenado = new TreeMap<>(Comparator.reverseOrder());
        double rendeu = 0;
        for (Map.Entry<String, Utilizador> u : utilizadores.entrySet()) {
            for (Map.Entry<String, Artigos> a : u.getValue().getVendeu().entrySet()) {
                if (a.getValue().getData_venda() != null && dateI.isBefore(a.getValue().getData_venda()) && dateF.isAfter(a.getValue().getData_venda()))
                        rendeu += a.getValue().getPrecoDesconto();
            }
            ordenado.put(rendeu + " " + u.getValue().getEmail(),u.getValue());
            rendeu = 0;
        }
        return ordenado;
    }

    public Map <String, Utilizador> maioresCompradores(LocalDate dateI, LocalDate dateF) {
        Map <String,Utilizador> ordenado = new TreeMap<>(Comparator.reverseOrder());
        double rendeu = 0;
        for (Map.Entry<String, Utilizador> u : utilizadores.entrySet()) {
            for (Map.Entry<String, Encomenda> a : u.getValue().getEncomendas().entrySet()) {
                if (dateI.isBefore(a.getValue().getData()) && dateF.isAfter(a.getValue().getData()))
                    rendeu += a.getValue().getPrecoE();
            }
            ordenado.put(rendeu + " " + u.getValue().getEmail(),u.getValue());
            rendeu = 0;
        }
        return ordenado;
    }

    public void addLucro(double rendeu) {
        this.rendeu += rendeu;
    }

    public boolean verificaTransportadorasPremium() {
        for (Map.Entry<String, Transportadora> t : this.transportadoras.entrySet()) {
            Transportadora tr = t.getValue();
            if (tr instanceof TransportadoraPremium) return true;
        }
        return false;
    }
}