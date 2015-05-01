
public class Terms {
    //member variable
    double deg;
    double coef;

    public void setCoef(double c){
        coef=c;
    }

    public double getDeg(){
        return deg;
    }

    public double getCoef(){
        return coef;
    }

    public Terms(double c, double d){
        deg=d;
        coef=c;

    }

    public String toString(){
        String str="";
        str="coeff: "+coef;
        str+="deg: "+ deg;

        return str;
    }

}






