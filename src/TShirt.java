import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class TShirt extends Artigos{

    public enum Tamanho {
        S,
        M,
        L,
        XL
    }

    public enum Padrao {
        Liso,
        Riscas,
        Palmeiras
    }

    private Tamanho tamanho;
    private Padrao padrao;
    private LocalDate date;

    public TShirt() {
        super ();
        tamanho = null;
        padrao = null;
        date = null;
    }

    public TShirt(Tamanho tamanho, Padrao padrao, LocalDate date, Estado estado, String danos, int nDonos, String descricao, String marca, String codigo, String user_Id, Transportadora transportadora, double preco, double preco_desconto) {
        super (estado, danos, nDonos, descricao, marca, codigo, user_Id, transportadora, preco, preco_desconto);
        this.tamanho = tamanho;
        this.padrao = padrao;
        this.date = date;
    }

    public TShirt(TShirt t) {
        super (t);
        this.tamanho = t.getTamanho();
        this.padrao = t.getPadrao();
        this.date = t.getDate();
    }

    public Tamanho getTamanho() {
        return tamanho;
    }

    public Padrao getPadrao() {
        return padrao;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setTamanho(int tamanho) {
        switch (tamanho) {
            case 0: {
                this.tamanho = Tamanho.S;
                break;
            }
            case 1: {
                this.tamanho = Tamanho.M;
                break;
            }
            case 2: {
                this.tamanho = Tamanho.L;
                break;
            }
            case 3: {
                this.tamanho = Tamanho.XL;
            }
        }
    }

    public void setPadrao(int padrao) {
        switch (padrao) {
            case 0: {
                this.padrao = Padrao.Liso;
                break;
            }
            case 1: {
                this.padrao = Padrao.Riscas;
                break;
            }
            case 2: {
                this.padrao = Padrao.Palmeiras;
                break;
            }
        }
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    public TShirt clone() {
        return new TShirt(this);
    }

    public boolean equals(Object obj) {
        if(obj==this)
            return true;
        if(obj==null || obj.getClass() != this.getClass())
            return false;
        TShirt le = (TShirt) obj;
        return  super.equals(le) &&
                le.getTamanho() == this.tamanho &&
                le.getPadrao() == this.padrao &&
                le.getDate().equals(this.date);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("T-Shirt:: {");
        sb.append("Tamanho: ").append(this.tamanho);
        sb.append("Padrão: ").append(this.padrao);
        sb.append("Date: ").append(this.date).append("}");
        return super.toString() + sb.toString();
    }

    public void calculaDesconto (LocalDate date){
        if(this.getPadrao() != Padrao.Liso && this.getEstado() == Estado.Usado) this.setPrecoDesconto(this.getPreco() * 0.5);
        else this.setPrecoDesconto(this.getPreco());
    }
}
