import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class SapatilhasPremium extends Sapatilhas{

    public SapatilhasPremium() {
        super();

    }

    public SapatilhasPremium(int tamanho, int atacador, String cor, LocalDate date, Estado estado, String danos, int nDonos, String descricao, String marca, String codigo, String user_Id, Transportadora transportadora, double preco, double preco_desconto) {
        super(tamanho, atacador, cor, date, estado, danos, nDonos, descricao, marca, codigo, user_Id, transportadora, preco, preco_desconto);
    }

    public SapatilhasPremium(SapatilhasPremium s) {
        super(s);
    }

    public boolean equals(Object obj) {
        if(obj==this)
            return true;
        if(obj==null || obj.getClass() != this.getClass())
            return false;
        SapatilhasPremium le = (SapatilhasPremium) obj;
        return  super.equals(le);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        return super.toString() + sb.toString();
    }

    public SapatilhasPremium clone() {
        return new SapatilhasPremium(this);
    }

    @Override
    public void calculaDesconto(LocalDate date) {
        long years = ChronoUnit.YEARS.between(this.getDate(), date);
        if(getEstado() == Estado.Usado) this.setPrecoDesconto(this.getPreco() + (this.getPreco() * 0.05) * years);
        if(getEstado() == Estado.Novo) this.setPrecoDesconto(this.getPreco() + (this.getPreco() * 0.10) * years);
    }
}
