public class MainView {
    private VintageController v;

    public MainView(VintageController v) {
        this.v = v;
    }

    public void run() {
        Menu menuPrincipal = new Menu(new String[]{
                "Utilizador",
                "Admin"
        });
        menuPrincipal.setHandler(1,this :: ViewUtilizador);
        menuPrincipal.setHandler(2,this :: ViewAdmin);
        menuPrincipal.run();
    }

    public void ViewUtilizador () {
        Menu menuUtilizador = new Menu (new String[] {
                "Register",
                "Login"
        });
        menuUtilizador.setHandler(1, v :: registerUtilizador);
        menuUtilizador.setHandler(2, v :: login);
        menuUtilizador.run();
    }

    public void ViewAdmin () {
        Menu menuAdmin = new Menu(new String[] {
                "Gerir Transportadoras",
                "Estatistica",
                "Avançar Tempo"
        });
        menuAdmin.setHandler(1, this ::menuTransportadoras);
        menuAdmin.setHandler(2,this ::menuEstatistica);
        menuAdmin.setHandler(3, v ::avancarTempo);
        menuAdmin.run();
    }

    public void menuTransportadoras() {
        Menu menuTransportadoras = new Menu(new String[] {
                "Ver transportadoras",
                "Adicionar Transportadora",
                "Remover Transportadora",
                "Alterar transportadora"
        });
        menuTransportadoras.setHandler(1, v ::verTransportadoras);
        menuTransportadoras.setHandler(2, v ::registerTransportadora);
        menuTransportadoras.setHandler(3, v ::removeTransportadora);
        menuTransportadoras.setHandler(4, v ::alterarTransportadora);
        menuTransportadoras.run();
    }

    public void menuEstatistica() {
        Menu menuEstatistica = new Menu(new String[] {
                "Utilizador que mais rendeu",
                "Transportadora que mais faturou",
                "Encomendas emitidas por um vendedor",
                "Maiores compradores/vendedores do sistema",
                "Rendimento do Vintage"
        });
        menuEstatistica.setHandler(1,v :: utilizadorMaisRendeu);
        menuEstatistica.setHandler(2,v :: transportadoraMaisFaturou);
        menuEstatistica.setHandler(3,v :: encomendasVendedor);
        menuEstatistica.setHandler(4,v :: maioresComVen);
        menuEstatistica.setHandler(5,v :: lucrouVintage);
        menuEstatistica.run();
    }
}
