public class TransportadoraPremium extends Transportadora {
    public TransportadoraPremium() {
        super();
    }

    public TransportadoraPremium(double margemLucro, double imposto, double valorBaseGra1, double valorBaseMed1, double valorBasePeq1, String transportadora1, double totalobtido) {
        super(margemLucro,imposto,valorBaseGra1,valorBaseMed1,valorBasePeq1,transportadora1, totalobtido);
    }

    public TransportadoraPremium(TransportadoraPremium t){
        super(t);
    }

    public boolean equals(Object obj) {
        if(obj==this)
            return true;
        if(obj==null || obj.getClass() != this.getClass())
            return false;
        TransportadoraPremium le = (TransportadoraPremium) obj;
        return  super.equals(le);
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        return super.toString() + sb.toString();
    }

    public TransportadoraPremium clone() {
        return new TransportadoraPremium(this);
    }

    @Override
    public void custoExpedicao(Encomenda.Dimensao dimensao) {
        switch (dimensao) {
            case Pequena: {
                this.addTotalObtido((this.getValorBasePeq() - (this.getValorBasePeq() * this.getImposto())) * 0.2 + (this.getMargemLucro() * this.getValorBasePeq()));
                break;
            }
            case Media: {
                this.addTotalObtido((this.getValorBaseMed() - (this.getValorBaseMed() * this.getImposto())) * 0.2 + (this.getMargemLucro() * this.getValorBaseMed()));
                break;
            }
            case Grande: {
                this.addTotalObtido((this.getValorBaseGra() - (this.getValorBaseGra() * this.getImposto())) * 0.2 + (this.getMargemLucro() * this.getValorBaseGra()));
                break;
            }
        }
    }
}
