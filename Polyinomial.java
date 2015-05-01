import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Polyinomial {
    //instance variables
    double start;
    double finalV;
    String eqStr; 
    ArrayList<Terms> termsList;
    ArrayList<Terms> derivList;

    //constructor
    public Polyinomial(String str) {
        eqStr=str.toLowerCase();
        start=-10;
        finalV=10;
        parsePolynimalEquation();
        sortTerms();

    }
    //constructor 
    public Polyinomial() {

    }

    /** 
     *enterEq
     *return type: String
     *param: none
     *allows the user to enter in the equation  
     **/
    public String enterEq(){
        Scanner s = new Scanner(System.in);
        System.out.println("Enter in polyinomial Equation: ");
        eqStr=s.nextLine();
        parsePolynimalEquation();
        return "f(x)="+eqStr;
    }

    /** 
     *addTerms
     *return type: void
     *param: Terms t
     *this takes the terms from the termsList
     *if degree doesn't exists then adds the new term
     *else it just updates the coefficient of the existing term by adding the coeff of
     *existing term and new term t.
     **/
    public void addTerms(Terms t){
        double coef=0;

        //populate the arraylist 
        if(termsList == null){
            termsList = new ArrayList<Terms>();
        }
        Terms tmpTerm;
        boolean existed = false;
        for(int i=0;i<termsList.size(); i++){
            tmpTerm = termsList.get(i);
            if(tmpTerm.getDeg() == t.getDeg()){
                tmpTerm.setCoef(tmpTerm.getCoef()+t.getCoef());	
                existed = true;
                break;
            }		

        }
        if(!existed){
            termsList.add(t);
        }
        //		System.out.println("##**** "+termsList);
    }

    /** 
     *deriv
     *return type: String
     *param: none
     *calculates the derivative value using the terms of equation stored in ArrayList
     **/
    public String deriv()
    {
        double coeff,deg;
        boolean sign;
        derivList = new ArrayList<Terms>();
        //		System.out.println("**** "+termsList);
        for(int i=0;i<termsList.size();i++){
            coeff=termsList.get(i).getCoef() * termsList.get(i).getDeg();
            deg=termsList.get(i).getDeg()-1;
            //sign=termsList.get(i).getSign();
            Terms t = new Terms(coeff,deg); //,sign);
            derivList.add(t);
        }
        //		System.out.println("dList: "+derivList);
        String str=generateEQ(derivList);
        return str;
    }

    /** 
     *quadraticRoots()
     *return type: ArrayList
     *finds the roots quadratic equations by applying formula -b+Math.sqrt(b*b-4*a*c))/(2*a)); 
     **/	
    private ArrayList<Double> quadraticRoots()
    {
        double a=0,b=0,c=0,m=0, coeff=0,deg=0;

        double oneRoot;
        int j=0;
        Terms term = null;

        ArrayList<Double> rts = new ArrayList<Double>();

        for(int i=0; i<termsList.size();i++){
            term = termsList.get(i);
            if(term.getDeg() == 2)
            {
                a = term.getCoef();
            }else if(term.getDeg() == 1)
            {
                b = term.getCoef();
            }else if(term.getDeg() == 0)
            {
                c = term.getCoef();
            }
        }

        double r1 = (-b+Math.sqrt(b*b-4*a*c))/(2*a);
        double r2 = (-b-Math.sqrt(b*b-4*a*c))/(2*a);
        if(r1 >= start && r1 <= finalV)
            rts.add(r1);
        if(r2 >= start && r2 <= finalV)
            rts.add(r2);

        return rts;
    }

    /** 
     *linearRoots()
     *return type: ArrayList
     *param: none
     *finds the roots quadratic equations 
     **/
    private ArrayList<Double> linearRoots(){
        double a=0,b=0,c=0,m=0, coeff=0,deg=0;
        Terms term;
        double oneRoot;
        int j=0;

        ArrayList<Double> rts = new ArrayList<Double>();

        for(int i=0; i<termsList.size();i++){
            term = termsList.get(i);
            if(term.getDeg() == 1)
            {
                a = term.getCoef();
            }else if(term.getDeg() == 0)
            {
                c = term.getCoef();
            }
        }
        m=(b/-1); //m - constant
        double r1 = 0;
        if( c!=0){
            r1 = (m/c); //root for a linear function
        }
        else 
            r1 = 0;;//if no constant then -m/b=0
        if(r1 >= start && r1 <= finalV)
            rts.add(r1);
        return rts;	
    }

    /** 
     *longDiv()
     *return type: ArrayList
     *param: none
     *uses long div to find the possible roots (synthetic div) for degrees higher than 2
     **/
    private ArrayList<Double> longDiv(){
        double value=0, oneRoot=0, c=0, zero=0;
        Terms term;

        ArrayList<Double> rts = new ArrayList<Double>();
        ArrayList<Double> possRootsList = possibleRoots();

        double highestDeg = termsList.get(0).getDeg();
        int d=(int)highestDeg;
        int currDeg = 0;

        //** Check for the missing degree, if missing add it at appropriate index

        for(int i=0; i<termsList.size(); i++){
            currDeg = (int)termsList.get(i).getDeg();
            if(currDeg != d)
            {
                term = new Terms(0,d);
                termsList.add(i, term);
            }
            d--;
        }
        System.out.println("possRootsList: "+possRootsList);

        for(int i=0; i<possRootsList.size(); i++){
            oneRoot=possRootsList.get(i);

            //			System.out.println("\n\noneRoot: "+oneRoot);

            for(int j=0; j<termsList.size(); j++){
                c=termsList.get(j).getCoef();

                if(j==0){
                    value=c*oneRoot;
                }

                else{
                    value=c+value;
                    value=value*oneRoot;
                    //					System.out.println("zero: "+value);
                }

                //				
            }
            if(value==0){
                rts.add(oneRoot);
            }
        }

        //		System.out.println("rts: "+rts);

        return rts;

    }

    /** 
     *roots()
     *return type: ArrayList
     *param: none
     *finds all the roots for any positive degree function
     **/
    public ArrayList<Double> roots(){
        double a=0,b=0,c=0,m=0, coeff=0,deg=0;

        double oneRoot;
        int j=0;

        ArrayList<Double> rts = new ArrayList<Double>();

        double d=termsList.get(0).getDeg();

        if(d==1){
            rts=linearRoots();
        }
        if(d==2){
            rts = quadraticRoots();	
        }

        else{//use long division
            rts=longDiv();
        }

        return rts;
    }			

    /** 
     *generateEQ()
     *return type: String
     *takes in a list of terms of the equation and returns a string in equation form.
     **/

    public String generateEQ()
    {
        return generateEQ(termsList);
    }

    /** 
     *generateEQ()
     *return type: String
     *param: ArrayList<Terms> tList
     *takes in a list of terms of the equation and returns a string in equation form.
     **/
    public String generateEQ(ArrayList<Terms> tList)
    {
        String str="";
        double coeff,deg;
        boolean sign;

        for(int i=0;i<tList.size();i++){
            coeff=tList.get(i).getCoef();
            deg=tList.get(i).getDeg();

            if(coeff != 0)
            {
                if(i != 0 && coeff > 0)
                    str += "+";
                //if(coeff%1 == 0)
                //{
                //str += Integer.toString((int)coeff);
                //}else
                if(coeff != 1 || deg == 0)
                {
                    if(coeff == -1 && deg != 0)
                        str+="-";
                    else
                        str+=coeff;
                }
                if(deg != 0)
                {
                    if(deg > 1)
                    {
                        if(deg%1 == 0)
                            str += "x^"+Integer.toString((int)deg)+" ";
                        else
                            str += "x^"+deg+" ";
                    }else
                        str += "x ";  // if deg is one print x only
                }
            }
        }
        return str;
    }

    public ArrayList<Double> vertices(){
        deriv();
        ArrayList<Double> v = new ArrayList<Double>();
        termsList = derivList;
        double d=termsList.get(0).getDeg();

        if(d==1){
            v=linearRoots();

        }

        else if(d==2){
            v=quadraticRoots();
        }

        else{
            v=longDiv();

        }

        return v;

        //		double a=0,b=0,c=0, coeff=0,deg=0;
        //		boolean sign;
        //		double[] v = new double[10];
        //		double vertex=0;
        //		
        //		if(termsList.get(0).getDeg() == 2){
        //			//getting the terms: a,b,c
        //			for(int i=0;i<termsList.size();i++){
        //				coeff=termsList.get(i).getCoef();
        //				deg=termsList.get(i).getDeg();
        //				//sign=termsList.get(i).getSign();
        //				if(deg==2) a=coeff; // ax^2 
        //				else if(deg==1) b=coeff; // bx^1
        //				else c=coeff; //cx^0
        //			}
        //			//formula v=-b/2*a
        //			vertex=-b/2*a;
        //			v[0]=vertex;
        //		}
        //		
        //		else{
        //			String str=deriv();
        //		}
        //		
        //		return v;

    }

	
    /** 
     *yIntercept()
     *return type: double
     *param: none
     *pre-condition: assumed that polynomial is a function and not and trig/log functions
     *if degree 0 exists then returns the constant else y-intercept occurs at 0 
     **/
    public double yIntercept(){
        double lowestCoeff=termsList.get(termsList.size()-1).getCoef();
        double lowestDeg=termsList.get(termsList.size()-1).getDeg();
        double y=0;

        if(lowestDeg==0){
            y=lowestCoeff;
        }
        return y;

    }

    /** 
     *setInterval()
     *return type: double[]
     *param: double startVal, double finalVal
     *user can alter the range of x values
     *default range of x values: [-10,10]
     **/
    public double[] setInterval(double startVal, double finalVal){
        //if no interval given then interval is by default from -10 to 10
        start=startVal;
        finalV=finalVal;
        double[] rangeVal = new double[2];

        rangeVal[0]=startVal;
        rangeVal[1]=finalVal;

        return rangeVal; // returns the range of values
    }

    /** 
     *factors()
     *return type: ArrayList
     *param: none
     *find the lowest degrees coeff, which is the constant
     *finds the factors of the constant and 
     **/
    public ArrayList<Double> factors(){
        double lowestCoeff=termsList.get(termsList.size()-1).getCoef();
        ArrayList<Double> facts = new ArrayList<Double>();
        double constant=Math.abs(lowestCoeff);

        double startingPos = (constant/-1);
        for(int i= (int)(startingPos); i<=constant; i++){
            if((constant % i == 0)){
                facts.add((double) i);
            }
        }

        return facts;
    }

    /** 
     *possibleRoots()
     *return type: ArrayList
     *param: none
     *finds the possible roots, by adding the factors of the constant
     *and coeff/constant 
     **/
    public ArrayList<Double> possibleRoots(){
        double lCoeff= termsList.get(0).getCoef();//leading coefficient
        ArrayList<Double> rList = new ArrayList<Double>();

        ArrayList<Double> rFacts = factors();
        //		System.out.println("******rFacts: "+ rFacts);

        for(int i=0; i<rFacts.size(); i++){
            if(rFacts.get(i) >= start && rFacts.get(i) <= finalV){
                if (!rList.contains(rFacts.get(i)))
                {
                    rList.add(rFacts.get(i));
                }
                if(!rList.contains(lCoeff/rFacts.get(i)))
                {
                    rList.add(lCoeff/rFacts.get(i));
                }

            }
        }

        //		System.out.println("******list: "+ rList);

        return rList;
    }

    /**
     **sortTerms()
     *return type: void
     *param: none
     *sorts the terms in List from high degree to lowest degree.
     *
     */
    public void sortTerms(){

        Terms temp;
        ArrayList<Terms> tmpList = new ArrayList<Terms>();

        for(int i=0; i<termsList.size(); i++){

            for(int j=i+1; j<termsList.size(); j++){

                if(termsList.get(i).getDeg() < termsList.get(j).getDeg()){
                    temp=termsList.get(i);
                    termsList.set(i, termsList.get(j));
                    termsList.set(j, temp);

                }
            }
        }

        //		System.out.println("&&&&&  "+termsList);
    }

    /** 
     *createTerm()
     *return type: void
     *param: String cStr, String cDeg
     *creates term object using the numbers passed as Strings and add it to the ArrayList
     * 
     **/
    private void createTerm(String cStr, String cDeg)
    {
        Terms term = null;
        double coeff = 0.0f;
        double deg = 0.0f;
        if (!cStr.equals("")){
            coeff = Double.parseDouble(cStr);
            if (!cDeg.equals(""))
            {
                deg = Double.parseDouble(cDeg);				

            }
            term = new Terms(coeff, deg);
            addTerms(term);
        }
    }

    /** 
     *parsePolynimalEquation()
     *return type: void
     *param: none
     *parses the String and creates terms.
     *Uses StringTokenizer java API to parse the equation. creates token using tokenizer
     *as "-+^x", assumes equation is function is "x"
     **/
    private  void parsePolynimalEquation(){
        int coeff= 1;
        int deg =0;
        String s1, s2;
        String ps1 = null;

        String token = null;
        String ntoken = null;
        String cStr = "";
        String cDeg = "";
        double num = 0.0;
        String delim = "+";
        StringTokenizer strtok = new StringTokenizer(eqStr, "-+^x",true);
        String preToken = null;

        while (strtok.hasMoreTokens())
        {
            token = strtok.nextToken().trim();

            if(token.equals("-") || token.equals("+"))
            {
                delim = token;
                createTerm(cStr, cDeg);

                cStr = "";
                cDeg = "";
                if(token.equals("-"))
                    cStr = "-";

            }else
            {
                if(token.equals("x"))
                {
                    //delim = "x";
                    if(("").equals(cStr) || "-".equals(cStr))
                    {
                        cStr = cStr+"1";
                    }
                    cDeg = "1";

                }else if(token.equals("^"))
                {
                    delim = "^";
                }else
                {
                    try
                    {
                        num = Double.parseDouble(token);
                        if(delim.equals("-") || delim.equals("+"))
                        {
                            cStr += token;
                        }
                        else if(delim.equals("^"))
                        {
                            cDeg = token;
                        }

                    }catch(NumberFormatException e)
                    {
                        e.printStackTrace();
                    }
                }	
            }

            preToken = token;

        }

        //// grab the last term;\

        createTerm(cStr, cDeg);
    }

    //	public static void main(String[] args){
    //		String str="";
    //		str="x^3-2x^2-5x+6";
    //		str="x^3-2x^2-5x+6";//p.enterEq();
    //		str="x+x^2-2";
    //		str="x^2";
    //		str = "2x+1";
    //		Polyinomial p = new Polyinomial(str);
    //		System.out.println("original: "+str);
    //		p.generateTerms(str);
    //		p.setInterval(-5,5);
    //		str=p.deriv();
    //		System.out.println("Deriv: "+str);
    //		uble[] d=p.degree();
    //		System.out.println(d);
    //		
    //		System.out.println("\n\n\nsorted: ");
    //		double value=p.degreeSorted().length;
    //		double[] value2=p.degreeSorted();
    //		
    //		for(int i=0; i<value; i++){
    //			System.out.println(" "+value2[i]);
    //		}
    //		p.possibleRoots();
    //		ArrayList<Double> values = p.roots();
    //		System.out.println("roots "+values[0] + " , " + values[1]);
    //		System.out.println("\n\nFactors");
    //		double[] values=p.factors();
    //		for(int i=0; i<values.length; i++){
    //			if(values[i] == 0) break;
    //			System.out.println(" "+values[i]);
    //			System.out.println("roots: "+values[i]);
    //		}
    //		
    //		System.out.println("vertices: "+p.vertices());
    //		
    //		
    //		
    //	}
}




