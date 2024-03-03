import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class MalasPremium extends Malas {
    public MalasPremium() {
        super();
    }

    public MalasPremium(Dimensao dimensao, Material material, LocalDate date, double descontoMaterial, Estado estado, String danos, int nDonos, String descricao, String marca, String codigo, String user_Id, Transportadora transportadora, double preco, double preco_desconto) {
        super(dimensao,material,date,descontoMaterial,estado, danos, nDonos, descricao, marca, codigo,user_Id,transportadora,preco, preco_desconto);
    }

    public MalasPremium(MalasPremium m) {
        super(m);
    }

    public boolean equals(Object obj) {
        if(obj==this)
            return true;
        if(obj==null || obj.getClass() != this.getClass())
            return false;
        MalasPremium le = (MalasPremium) obj;
        return  super.equals(le);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        return super.toString() + sb.toString();
    }

    public MalasPremium clone() {
        return new MalasPremium(this);
    }

    @Override
    public void calculaDesconto(LocalDate date) {
        long years = ChronoUnit.YEARS.between(this.getDate(), date);
        if(getEstado() == Estado.Usado) this.setPrecoDesconto(this.getPreco() + (this.getPreco() * 0.05) * years);
        if(getEstado() == Estado.Novo) this.setPrecoDesconto(this.getPreco() + (this.getPreco() * 0.10) * years);
    }
}
