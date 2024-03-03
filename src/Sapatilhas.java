import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Sapatilhas extends Artigos{
    private int tamanho;
    private int atacador;
    private String cor;
    private LocalDate date;

    public Sapatilhas() {
        super ();
        tamanho = 0;
        atacador = 0;
        cor = "";
        date = null;
    }

    public Sapatilhas(int tamanho, int atacador, String cor, LocalDate date, Estado estado, String danos, int nDonos, String descricao, String marca, String codigo, String user_Id, Transportadora transportadora, double preco, double preco_desconto) {
        super (estado, danos, nDonos, descricao, marca, codigo, user_Id, transportadora, preco, preco_desconto);
        this.tamanho = tamanho;
        this.atacador = atacador;
        this.cor = cor;
        this.date = date;
    }

    public Sapatilhas(Sapatilhas s) {
        super (s);
        this.tamanho = s.getTamanho();
        this.atacador = s.getAtacador();
        this.cor = s.getCor();
        this.date = s.getDate();
    }

    public int getTamanho() {
        return tamanho;
    }

    public int getAtacador() {
        return atacador;
    }

    public String getCor() {
        return cor;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public void setAtacador(int atacador) {
        this.atacador = atacador;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Sapatilhas clone() {
        return new Sapatilhas(this);
    }

    public boolean equals(Object obj) {
        if(obj==this)
            return true;
        if(obj==null || obj.getClass() != this.getClass())
            return false;
        Sapatilhas le = (Sapatilhas) obj;
        return  super.equals(le) &&
                le.getTamanho() == this.tamanho &&
                le.getAtacador() == this.atacador &&
                le.getCor().equals(this.cor) &&
                le.getDate().equals(this.date);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sapatilhas:: {");
        sb.append("Tamanho: ").append(this.tamanho);
        sb.append("Atacador: ").append(this.atacador);
        sb.append("Cor: ").append(this.cor);
        sb.append("Date: ").append(this.date).append("}");
        return super.toString() + sb.toString();
    }

    public void calculaDesconto (LocalDate date) {
        if(getnDonos() > 0){
            this.setPrecoDesconto( this.getPreco() - (this.getPreco()/this.getnDonos() * 0.40));
        }
        else if(this.getTamanho() > 45 && getnDonos() == 0) {
            this.setPrecoDesconto( this.getPreco() - (this.getPreco() * 0.20));
        }
        else this.setPrecoDesconto(this.getPreco());
    }
}