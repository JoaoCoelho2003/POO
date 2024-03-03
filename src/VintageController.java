public class VintageController {
    private Vintage v;
    private UtilizadorController user;
    private AdminController admin;

    public VintageController(Vintage v, UtilizadorController user, AdminController admin) {
        this.v = v;
        this.user = user;
        this.admin = admin;
    }

    public UtilizadorController getUtilizadorController() {
        return user;
    }
    public AdminController getAdminController() {
        return admin;
    }

    public Vintage getVintage() {
        return v;
    }

    public void registerUtilizador() {
        try {
            v.addUtilizador(user.registerUtilizador());
        } catch (VintageException e){
            System.out.println(e.getMessage());
        }
    }

    public void login() {
        try {
            user.login();
        } catch (VintageException e) {
            System.out.println(e.getMessage());
            return;
        }
        UtilizadorView account = new UtilizadorView(user);
        account.run();
    }

    public void registerTransportadora () {
        v.addTransportadora(admin.registerTransportadora());
    }

    public void removeTransportadora () {
        admin.removeTransportadora();
    }

    public void verTransportadoras () {
        admin.verTransportadoras();
    }

    public void avancarTempo () {
        admin.avancaTempo();
    }

    public void alterarTransportadora() {
        admin.alterarTransportadora();
    }

    public void utilizadorMaisRendeu() {
        admin.utilizadorMaisRendeu();
    }

    public void lucrouVintage() {
        admin.lucrouVintage();
    }

    public void transportadoraMaisFaturou() {
        admin.transportadoraMaisFaturou();
    }

    public void encomendasVendedor() {
        admin.encomendasVendedor();
    }

    public void maioresComVen() {
        admin.maioresComVen();
    }
}
