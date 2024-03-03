import javax.swing.plaf.metal.MetalMenuBarUI;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class UtilizadorController {

    private Vintage v;
    private Utilizador u;

    public UtilizadorController(Vintage v) {
        this.v = v;
        this.u = null;
    }

    public Utilizador registerUtilizador() throws VintageException {
        Utilizador u = new Utilizador();
        Scanner sc = new Scanner(System.in);
        System.out.print("Email :: ");
        String email = sc.nextLine();
        if(!v.getUtilizadores().containsKey(email)) u.setEmail(email);
        else throw new VintageException("O email "+ email + " já está a ser utilizado");
        System.out.print("Nome :: ");
        u.setNome(sc.nextLine());
        System.out.print("Morada :: ");
        u.setMorada(sc.nextLine());
        System.out.print("NIF :: ");
        u.setNif(Read_Scanner.getInt(sc));
        u.setId(Codigos.gerarCodigo());

        return u;
    }

    public void login () throws VintageException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Email: ");
        String email = sc.nextLine();
        this.u = v.getUtilizadores().get(email);
        if (u == null)
            throw new VintageException("Não existe nenhum utilizador com o email " + email);
    }

    public void selecionaTransportadora (int premium, Artigos a) {
        Scanner sc = new Scanner(System.in);
        int index = 1;
        Map<Integer,Transportadora> transp = new HashMap<>();
        for (Map.Entry<String, Transportadora> t : v.getTransportadoras().entrySet()) {
            Transportadora tr = t.getValue();
            if (premium == 1 && tr instanceof TransportadoraPremium) {
                transp.put(index, tr);
                System.out.println(index + " -> " + tr.getTransportadora());
                index++;
            }
            else if (premium == 0 && !(tr instanceof TransportadoraPremium)) {
                transp.put(index, tr);
                System.out.println(index + " -> " + tr.getTransportadora());
                index++;
            }
        }
        int choice;
        do {
            choice = Read_Scanner.getInt(sc);
        } while(choice < 1 || choice > index);
        a.setTransportadora(transp.get(choice));
    }

    public void adicionaSapatilha () {
        int atacador = -1, dia, mes,ano, estado = -1, premium = -1;
        Scanner sc = new Scanner(System.in);
        while(premium != 1 && premium != 0) {
            System.out.print("Premium (1 -> sim ou 0 -> não) :: ");
            premium = Read_Scanner.getInt(sc);
            sc.nextLine();
        }
        Sapatilhas s;
        if(premium == 1) {
            //verifica existencia de transportadoras premium
            if (!v.verificaTransportadorasPremium()) {
                System.out.println("Não existem Transportadoras Premium.");
                return;
            }
            s = new SapatilhasPremium();
        }
        else s = new Sapatilhas();
        System.out.print("Marca :: ");
        s.setMarca(sc.nextLine());
        System.out.print("Tamanho :: ");
        s.setTamanho(Read_Scanner.getInt(sc));
        sc.nextLine(); //O nextInt mete um \n no final, é preciso skippar este \n
        System.out.print("Cor :: ");
        s.setCor(sc.nextLine());


        while (atacador != 0 && atacador != 1) {
            System.out.print("Atacador (0 ou 1) :: ");
            atacador = Read_Scanner.getInt(sc);
        }
        s.setAtacador(atacador);

        System.out.print("Data de lançamento (YYYY-MM-DD):: ");
        sc.nextLine();
        s.setDate(Read_Scanner.getData(sc));
        do {
            System.out.print("Estado do Artigo (0->Novo, 1 -> Usado) :: ");
            estado = Read_Scanner.getInt(sc);
        } while (estado != 0 && estado != 1);
        s.setEstado(estado);
        sc.nextLine(); //O nextInt mete um \n no final, é preciso skippar este \n
        if (estado == 1) {
            System.out.print("Número de Donos :: ");
            s.setnDonos(Read_Scanner.getInt(sc));
            sc.nextLine(); //O nextInt mete um \n no final, é preciso skippar este \n
            System.out.print("Danos do Artigo :: ");
            s.setDanos(sc.nextLine());
        }
        System.out.print("Descrição :: ");
        s.setDescricao(sc.nextLine());
        System.out.print("Preço :: ");
        s.setPreco(Read_Scanner.getDouble(sc));
        System.out.println ("Escolha a Transportadora: ");
        selecionaTransportadora(premium,s);
        s.setUser_id(this.u.getEmail());

        v.addArtigo(s);
        v.addArtigoDisponivel(s);
        u.adicionaArtigosVenda(s);
    }

    public void adicionaMala () {
        int dimensao = -1, dia, mes,ano, estado = -1, material = -1, premium = -1;
        Scanner sc = new Scanner(System.in);
        while(premium != 1 && premium != 0) {
            System.out.print("Premium (1 -> sim ou 0 -> não) :: ");
            premium = Read_Scanner.getInt(sc);
            sc.nextLine();
        }
        Malas m;
        if(premium == 1) {
            //verifica existencia de transportadoras premium
            if (!v.verificaTransportadorasPremium()) {
                System.out.println("Não existem Transportadoras Premium.");
                return;
            }
            m = new MalasPremium();
        }
        else m = new Malas();



        System.out.print("Marca :: ");
        m.setMarca(sc.nextLine());
        while (material != 0 && material != 1 && material != 2) {
            System.out.print("Material (0 -> polipiel ou 1 -> couro ou 2 -> tecido) :: ");
            material = Read_Scanner.getInt(sc);
        }
        m.setMaterial(material);

        while (dimensao != 0 && dimensao != 1 && dimensao != 2) {
            System.out.print("Dimensão (0 -> pequena ou 1 -> media ou 2 -> grande) :: ");
            dimensao = Read_Scanner.getInt(sc);
        }
        m.setDimensao(dimensao);
        sc.nextLine();
        System.out.print("Data de lançamento (YYYY-MM-DD):: ");
        m.setDate(Read_Scanner.getData(sc));
        do {
            System.out.print("Estado do Artigo (0->Novo, 1 -> Usado) :: ");
            estado = Read_Scanner.getInt(sc);
        } while (estado != 0 && estado != 1);
        m.setEstado(estado);
        sc.nextLine(); //O nextInt mete um \n no final, é preciso skippar este \n
        if (estado == 1) {
            System.out.print("Número de Donos :: ");
            m.setnDonos(Read_Scanner.getInt(sc));
            sc.nextLine(); //O nextInt mete um \n no final, é preciso skippar este \n
            System.out.print("Danos do Artigo :: ");
            m.setDanos(sc.nextLine());
        }
        System.out.print("Descrição :: ");
        m.setDescricao(sc.nextLine());
        System.out.print("Preço :: ");
        m.setPreco(Read_Scanner.getDouble(sc));
        System.out.println ("Escolha a Transportadora: ");
        selecionaTransportadora(premium,m);
        m.setUser_id(this.u.getEmail());

        v.addArtigo(m);
        v.addArtigoDisponivel(m);
        u.adicionaArtigosVenda(m);
    }

    public void adicionaTshirt () {
        int tamanho = -1, padrao = -1, dia, mes,ano, estado = -1;

        TShirt t = new TShirt();
        Scanner sc = new Scanner(System.in);
        System.out.print("Marca :: ");
        t.setMarca(sc.nextLine());

        while (tamanho != 0 && tamanho != 1 && tamanho != 2 && tamanho != 3) {
            System.out.print("Dimensão (0 -> S ou 1 -> M ou 2 -> L ou 3 -> XL) :: ");
            tamanho = Read_Scanner.getInt(sc);
        }
        t.setTamanho(tamanho);

        while (padrao != 0 && padrao != 1 && padrao != 2) {
            System.out.print("Padrão (0 -> Liso ou 1 -> Riscas ou 2 -> Palmeiras) :: ");
            padrao = Read_Scanner.getInt(sc);
        }
        t.setPadrao(padrao);
        sc.nextLine();
        System.out.print("Data de lançamento (YYYY-MM-DD):: ");
        t.setDate(Read_Scanner.getData(sc));
        do {
            System.out.print("Estado do Artigo (0->Novo, 1 -> Usado) :: ");
            estado = Read_Scanner.getInt(sc);
        } while (estado != 0 && estado != 1);
        t.setEstado(estado);
        sc.nextLine(); //O nextInt mete um \n no final, é preciso skippar este \n
        if (estado == 1) {
            System.out.print("Número de Donos ::");
            t.setnDonos(Read_Scanner.getInt(sc));
            sc.nextLine(); //O nextInt mete um \n no final, é preciso skippar este \n
            System.out.print("Danos do Artigo ::");
            t.setDanos(sc.nextLine());
        }
        System.out.print("Descrição :: ");
        t.setDescricao(sc.nextLine());
        System.out.print("Preço :: ");
        t.setPreco(Read_Scanner.getDouble(sc));
        System.out.println ("Escolha a Transportadora: ");
        selecionaTransportadora(0,t);
        t.setUser_id(this.u.getEmail());

        v.addArtigo(t);
        v.addArtigoDisponivel(t);
        u.adicionaArtigosVenda(t);
    }

    public void compraArtigo () throws VintageException {
        if (this.v.getartigosDisponiveis().size() == 0)
            throw new VintageException("Não existem Artigos disponíveis.");
        else {
            this.v.getartigosDisponiveis().forEach((key, value) -> {
                if (!value.getUser_id().equals(this.u.getEmail())) {
                    value.calculaDesconto(v.getCurrentDate());
                    System.out.println(value.getCodigo() + " -> "
                            + value.getClass().toString() + ", Marca: " + value.getMarca() + ", Descrição: " + value.getDescricao() + ", Preço: " + value.getPrecoDesconto());
                }
            });

            Scanner sc = new Scanner(System.in);
            System.out.print("Quer adicionar um artigo ao carrinho? (0 ou 1): ");
            int confirmacao = -1;
            while (confirmacao != 0 && confirmacao != 1) {
                confirmacao = Read_Scanner.getInt(sc);
            }
            if (confirmacao == 0) return;
            sc.nextLine();
            System.out.print("Escolha o artigo para adicionar ao carrinho: ");
            String codigo = sc.nextLine();
            while (!this.v.getartigosDisponiveis().containsKey(codigo)) {
                System.out.print("Artigo não Disponível! ");
                System.out.print("Escolha o artigo para adicionar ao carrinho: ");
                codigo = sc.nextLine();
            }
            Artigos a = v.getartigosDisponiveis().get(codigo);
            u.adicionaCarrinho(a); // adiciona ao carrinho do user
            v.getUtilizadores().get(a.getUser_id()).vendaArtigo(codigo); // move de venda para vendido no user a qual o artigo pertence
            v.removeArtigoDisponiveis(codigo); // remove da lista de artigos disponiveis
        }
    }

    public void printCarrinho() {
        u.getCarrinho().forEach(value -> System.out.println(value.getCodigo() + " -> "
                + value.getClass() + ", Marca: " + value.getMarca() + ", Descrição: " + value.getDescricao() + ", Preço: " + value.getPrecoDesconto()));
    }

    public void validaCarrinho() {
        LocalDate data = v.getCurrentDate();
        Map <String,Utilizador> ut = v.getUtilizadores();
        u.getCarrinho().forEach(e -> ut.get(e.getUser_id()).aumentaValor(e.getPrecoDesconto()));
        double rendeu = u.percorreCarrinho(data);
        v.addLucro(rendeu);
        u.setCarrinho(new ArrayList<>());
    }


    public void devolveEncomenda () {
        AtomicBoolean existe = new AtomicBoolean(false);
        u.getEncomendas().forEach((key,value) -> {
            if (value.getEstadoE() == Encomenda.Estado.Finalizada && Math.abs(ChronoUnit.DAYS.between(v.getCurrentDate(),value.getData()))<=5) {
                System.out.println(value.getCodigo() + " -> Artigos: ");
                List<Artigos> l = value.getLista();
                l.forEach(e -> System.out.println("\t" + e.getCodigo() + " -> "
                        + e.getClass() + ", Marca: " + e.getMarca() + ", Descrição: " + e.getDescricao() + ", Preço: " + e.getPrecoDesconto()));
                existe.set(true);
            }
        });
        if (existe.get()) {
            System.out.print("Escolha encomenda a devolver: ");
            Scanner sc = new Scanner(System.in);
            String codigo = sc.nextLine();
            while (!u.getEncomendas().containsKey(codigo)) {
                System.out.print("O código inserido não corresponde a nenhuma encomenda! ");
                codigo = sc.nextLine();
            }

            Encomenda e = u.getEncomendas().get(codigo);
            u.getEncomendas().remove(codigo);

            e.getLista().forEach(a-> {
                v.getUtilizadores().get(a.getUser_id()).removeVendeu(a.getCodigo());
                v.addArtigoDisponivel(a);
            });
        }
    }

    public void removeArtigoCarrinho() {
        System.out.print("Escolha o Artigo a remover: ");
        Scanner sc = new Scanner(System.in);
        String codigo = sc.nextLine();
        boolean removeu = true;
        Artigos add = null;
        try {
            add = u.removeCarrinho(codigo);
        } catch (VintageException e) {
            System.out.println(e.getMessage());
            removeu = false;
            removeArtigoCarrinho();
        }
        if (removeu) {
            v.getUtilizadores().get(add.getUser_id()).removeVendeu(codigo);
            v.addArtigoDisponivel(add);
        }
    }

    public void verEncomendaUser() {
        u.getEncomendas().forEach((key,value) -> {
            System.out.println(key + " -> " + "Transportadora: " + value.getTransportadora().getTransportadora()
                    + ", Preco: " + value.getPrecoE() + " Data: "+ value.getData() + " Estado: " + value.getEstadoE() + " Artigos:");
            value.getLista().forEach(e -> System.out.println("\t" + e.getCodigo() + " -> "
                    + e.getClass() + ", Marca: " + e.getMarca() + ", Descrição: " + e.getDescricao() + ", Preço: " + e.getPrecoDesconto()));
        });
    }


    public boolean verificaExistenciaTransportadoras() {
        return this.v.getTransportadoras().size() != 0;
    }

    public void verFaturas() {
        List<String> f = u.getFaturas();
        f.forEach(e -> System.out.println((f.indexOf(e)+1) + ":: \n" + e));
    }
}
