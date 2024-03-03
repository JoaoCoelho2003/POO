import java.io.Serializable;

public class Transportadora implements Serializable {
    private double margemLucro, imposto,valorBasePeq, valorBaseMed, valorBaseGra, totalObtido;
    private String transportadora;

    public Transportadora() {
        margemLucro = 0.05;
        imposto = 0.23;
        valorBaseGra = -1;
        valorBasePeq = -1;
        valorBaseMed = -1;
        totalObtido = 0;
        transportadora = null;
    }

    public Transportadora(double margemLucro, double imposto, double valorBaseGra1, double valorBaseMed1, double valorBasePeq1, String transportadora1, double totalObtido) {
        this.margemLucro = margemLucro;
        this.imposto = imposto;
        this.valorBaseMed = valorBaseMed1;
        this.valorBaseGra = valorBaseGra1;
        this.valorBasePeq = valorBasePeq1;
        this.transportadora = transportadora1;
        this.totalObtido = totalObtido;
    }

    public Transportadora(Transportadora t) {
        this.margemLucro = t.getMargemLucro();
        this.imposto = t.getImposto();
        this.valorBaseMed = t.getValorBaseMed();
        this.valorBaseGra = t.getValorBaseGra();
        this.valorBasePeq = t.getValorBasePeq();
        this.transportadora = t.getTransportadora();
        this.totalObtido = t.getTotalObtido();
    }

    public void setMargemLucro(double margemLucro) {
        this.margemLucro = margemLucro;
    }

    public void setImposto(double imposto) {
        this.imposto = imposto;
    }

    public void setTransportadora(String transportadora) {
        this.transportadora = transportadora;
    }

    public void setValorBaseGra(double valorBaseGra) {
        this.valorBaseGra = valorBaseGra;
    }

    public void setValorBaseMed(double valorBaseMed) {
        this.valorBaseMed = valorBaseMed;
    }

    public void setValorBasePeq(double valorBasePeq) {
        this.valorBasePeq = valorBasePeq;
    }

    public void setTotalObtido(double totalObtido) {
        this.totalObtido = totalObtido;
    }

    public double getMargemLucro() {
        return margemLucro;
    }

    public double getImposto() {
        return imposto;
    }

    public double getValorBaseMed() {
        return valorBaseMed;
    }

    public double getValorBaseGra() {
        return valorBaseGra;
    }

    public double getValorBasePeq() {
        return valorBasePeq;
    }

    public String getTransportadora() {
        return transportadora;
    }

    public double getTotalObtido() {
        return totalObtido;
    }

    public Transportadora clone() {
        return new Transportadora(this);
    }

    public boolean equals(Object obj) {
        if(obj==this)
            return true;
        if(obj==null || obj.getClass() != this.getClass())
            return false;
        Transportadora x = (Transportadora) obj;
        return  x.getMargemLucro() == this.margemLucro &&
                x.getImposto() == this.imposto &&
                x.getTotalObtido() == this.totalObtido &&
                x.getValorBaseGra() == this.valorBaseGra &&
                x.getValorBaseMed() == this.valorBaseMed &&
                x.getValorBasePeq() == this.valorBasePeq &&
                x.getTransportadora().equals(this.transportadora);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Transportadora:: {");
        sb.append("Total faturado: ").append(this.totalObtido);
        sb.append("Margem de lucro: ").append(this.margemLucro);
        sb.append("Imposto: ").append(this.imposto);
        sb.append("Nome da Transportadora: ").append(this.transportadora);
        sb.append("Preço Base Encomenda Pequena: ").append(this.valorBasePeq);
        sb.append("Preço Base Encomenda Media: ").append(this.valorBaseMed);
        sb.append("Preço Base Encomenda Grande: ").append(this.valorBaseGra).append("}");
        return sb.toString();
    }

    public void custoExpedicao(Encomenda.Dimensao dimensao) {
        switch (dimensao) {
            case Pequena: {
                this.totalObtido += (valorBasePeq *  margemLucro * (1+ imposto)) * 0.9;
                break;
            }
            case Media: {
                this.totalObtido += (valorBaseMed *  margemLucro * (1+ imposto)) * 0.9;
                break;
            }
            case Grande: {
                this.totalObtido += (valorBaseGra *  margemLucro * (1+ imposto)) * 0.9;
                break;
            }
        }
    }
    public void addTotalObtido(double value) {
        this.totalObtido += value;
    }
}
