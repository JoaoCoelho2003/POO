import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.ChronoUnit;

public class Malas extends Artigos{

    public enum Dimensao {
        Pequena,
        Media,
        Grande
    }

    public enum Material {
        Polipiel,
        Couro,
        Tecido
    }

    private Dimensao dimensao;
    private Material material;
    private LocalDate date;
    private double descontoMaterial;

    public Malas () {
        super();
        dimensao = null;
        material = null;
        date = null;
        descontoMaterial = 0;
    }

    public Malas (Dimensao dimensao, Material material, LocalDate date,double descontoMaterial, Estado estado, String danos, int nDonos, String descricao, String marca, String codigo,String user_Id, Transportadora transportadora, double preco, double preco_desconto) {
        super (estado, danos, nDonos, descricao, marca, codigo,user_Id,transportadora,preco, preco_desconto);
        this.dimensao = dimensao;
        this.material = material;
        this.date = date;
        this.descontoMaterial = descontoMaterial;
    }

    public Malas (Malas a) {
        super (a);
        dimensao = a.getDimensao();
        material = a.getMaterial();
        date = a.getDate();
        descontoMaterial = a.getDescontoMaterial();
    }

    public LocalDate getDate() {
        return date;
    }

    public double getDescontoMaterial() {
        return descontoMaterial;
    }

    public Dimensao getDimensao() {
        return dimensao;
    }

    public Material getMaterial() {
        return material;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDescontoMaterial(double descontoMaterial) {
        this.descontoMaterial = descontoMaterial;
    }

    public void setDimensao(int dimensao) {
        switch (dimensao) {
            case 0: {
                this.dimensao = Dimensao.Pequena;
                break;
            }
            case 1: {
                this.dimensao = Dimensao.Media;
                break;
            }

            case 2: {
                this.dimensao = Dimensao.Grande;
                break;
            }
        }
    }

    public void setMaterial(int material) {
        switch (material) {
            case 0: {
                this.material = Material.Polipiel;
                this.setDescontoMaterial(0.10);
                break;
            }
            case 1: {
                this.material = Material.Couro;
                this.setDescontoMaterial(0.025);
                break;
            }

            case 2: {
                this.material = Material.Tecido;
                this.setDescontoMaterial(0.05);
                break;
            }
        }
    }

    public boolean equals(Object obj) {
        if(obj==this)
            return true;
        if(obj==null || obj.getClass() != this.getClass())
            return false;
        Malas le = (Malas) obj;
        return  super.equals(le) &&
                le.getDimensao() == this.dimensao &&
                le.getMaterial().equals(this.material) &&
                le.getDate().equals(this.getDate());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Mala:: {");
        sb.append("Dimensao: ").append(this.dimensao);
        sb.append("Material: ").append(this.material);
        sb.append("Ano:").append(this.date).append("}");
        return super.toString() + sb.toString();
    }

    public Malas clone () {
        return new Malas (this);
    }


    public void calculaDesconto (LocalDate date) {
        switch (this.dimensao) {
            case Pequena: {
                this.setPrecoDesconto(this.getPreco() - this.getPreco() * (0.3 + this.descontoMaterial  + 0.05 * Math.abs(ChronoUnit.YEARS.between(this.date, date))));
                break;
            }
            case Media: {
                this.setPrecoDesconto(this.getPreco() - this.getPreco() * (0.2 + this.descontoMaterial  + 0.05 * Math.abs(ChronoUnit.YEARS.between(this.date, date))));
                break;
            }
            case Grande: {
                this.setPrecoDesconto(this.getPreco() - this.getPreco() * (0.1 + this.descontoMaterial  + 0.05 * Math.abs(ChronoUnit.YEARS.between(this.date, date))));
                break;
            }
        }
    }
}
