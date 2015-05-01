import java.awt.EventQueue; 
import java.awt.BorderLayout; 
import java.awt.Color; 
import java.awt.FlowLayout;

import javax.swing.BorderFactory; 
import javax.swing.border.Border; 
import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame; 
import javax.swing.JLabel;
import javax.swing.JPanel; 
import javax.swing.JButton; 
import javax.swing.JTextField;

import java.awt.Container; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.BoxLayout;
public class PolyFrame  extends JFrame implements ActionListener 
{	
    JLabel labelGenEq, labelGenDeriv, labelGenYInt, labelGenXInt, labelGenInterval,labelGenVert; 
    JTextField textEq, textInterval, textInterval2;

    JButton butEq;
    JLabel labelEq, labelDeriv, labelYInt, labelXInt, labelInterval, labelVert;
    JCheckBox checkBoxDeriv, checkBoxYInt, checkBoxXInt, checkBoxInterval, checkBoxVert;

    /** 
     *constructor
     *creates the frame using a group layout, JLabels, JButtons, JCheckBoxes, and 
     *JTextFields
     *also uses action listeners 
     **/
    public PolyFrame()
    {
        JPanel myPanel = new JPanel();
        myPanel.setBackground(new Color(0,255,255));
        add(myPanel);

        //enter equation
        labelEq = new JLabel("Equation(as 2x^3-2x^2-5x+6)");//label
        myPanel.add(labelEq);

        textEq = new JTextField(15); //textbox
        myPanel.add(textEq);

        butEq=new JButton("Submit");//submit
        myPanel.add(butEq); 
        butEq.addActionListener(this);

        labelGenEq = new JLabel();//equation
        myPanel.add(labelGenEq);

        //calc deriv
        labelDeriv = new JLabel("Derivative"); //label
        myPanel.add(labelDeriv);

        checkBoxDeriv = new JCheckBox();//checkbox
        myPanel.add(checkBoxDeriv);

        labelGenDeriv = new JLabel();//answer
        labelGenDeriv.setForeground(new Color(0,0,255));
        myPanel.add(labelGenDeriv);

        //calc possible roots
        labelXInt = new JLabel("Roots (x-Intercepts)"); //label
        myPanel.add(labelXInt);

        checkBoxXInt = new JCheckBox();//checkbox
        myPanel.add(checkBoxXInt);

        labelGenXInt = new JLabel();//answer
        labelGenXInt.setForeground(new Color(0,255,0));
        myPanel.add(labelGenXInt);

        //calc y-Intercept
        labelYInt = new JLabel("y-Intercepts"); //label
        myPanel.add(labelYInt);

        labelGenYInt = new JLabel(); //label y-intercept 
        myPanel.add(labelGenYInt);

        checkBoxYInt = new JCheckBox();//checkbox y-intercept
        labelGenXInt.setForeground(new Color(150,10,250));
        myPanel.add(checkBoxYInt);

        //calc vertices
        labelVert = new JLabel("Vertices"); //label
        myPanel.add(labelVert);

        checkBoxVert = new JCheckBox();//checkbox
        myPanel.add(checkBoxVert);

        labelGenVert = new JLabel();//answer
        labelGenVert.setForeground(new Color(100,100,255));
        myPanel.add(labelGenVert);

        //calc Interval
        labelGenInterval = new JLabel();//label
        myPanel.add(labelGenInterval);

        labelInterval = new JLabel("Interval");//label
        myPanel.add(labelInterval);

        textInterval = new JTextField(5); //textbox for interval start
        myPanel.add(textInterval);

        textInterval2 = new JTextField(5); //textbox for interval final
        myPanel.add(textInterval2);

        checkBoxInterval = new JCheckBox();//checkbox for interval
        myPanel.add(checkBoxInterval);
        checkBoxInterval.addActionListener(this);

        //GUI layout, using Group Layout to arrange the objects
        GroupLayout layout = new GroupLayout(myPanel);
        myPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setVerticalGroup(
            layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(labelEq)                      
                .addComponent(textEq)
                .addComponent(labelGenEq))

            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(labelDeriv)
                .addComponent(checkBoxDeriv)
                .addComponent(labelGenDeriv))

            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(labelXInt)
                .addComponent(checkBoxXInt)
                .addComponent(labelGenXInt))	   

            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(labelYInt)
                .addComponent(checkBoxYInt)
                .addComponent(labelGenYInt))	

            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(labelVert)
                .addComponent(checkBoxVert)
                .addComponent(labelGenVert))

            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(labelInterval)
                .addComponent(checkBoxInterval)
                .addComponent(textInterval)
                .addComponent(textInterval2)
                .addComponent(labelGenInterval))	

            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(butEq))

        );

        layout.setHorizontalGroup(
            layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(labelEq)                      
                .addComponent(labelDeriv)
                .addComponent(labelXInt)
                .addComponent(labelYInt)
                .addComponent(labelVert)
                .addComponent(labelInterval)
                .addComponent(butEq))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(textEq) //all checkboxes are in order
                .addComponent(checkBoxDeriv)
                .addComponent(checkBoxXInt)
                .addComponent(checkBoxYInt)
                .addComponent(checkBoxVert)
                .addComponent(checkBoxInterval))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(labelGenEq)
                .addComponent(labelGenDeriv)
                .addComponent(labelGenXInt)
                .addComponent(labelGenYInt)
                .addComponent(labelGenVert)
                .addComponent(textInterval))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(textInterval2))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(labelGenInterval))

        );
	        
    }
    private void initialize()
    {
        labelGenEq.setText("");
        labelGenDeriv.setText("");
        labelGenXInt.setText("");
        labelGenYInt.setText("");
    }

    /** 
     *actionPerformed()
     *return type: void
     *param: ActionEvent e
     * This allows the user to choose what they want to see
     * calculated based on the given equation
     **/
    public void actionPerformed(ActionEvent e) {
        // Here you will create instance of polynomial class and pass this equation txt.getText, then call derivative method
        // and print derivative in a label
        System.out.println("*** "+e.getSource());
        if(e.getSource().equals(checkBoxInterval))
        {
            System.out.println("*** "+e.getSource());
            // defaults the interval to -10, 10
            textInterval.setText("-10");
            textInterval2.setText("10");
        }else
        {
            initialize();
            String eqStr = textEq.getText();
            try{
                Polyinomial p = new Polyinomial(eqStr);

                System.out.println("** "+checkBoxDeriv.isSelected());

                labelGenEq.setText("f(x) = "+p.generateEQ());
                if(checkBoxDeriv.isSelected())
                {
                    labelGenDeriv.setText("Derivative: "+p.deriv());
                }
                if(checkBoxInterval.isSelected())
                {	  
                    double startVal = -10.0;
                    double endVal = 10.0;
                    if( !"".equals(textInterval.getText().trim()))
                        startVal = Double.parseDouble(textInterval.getText());
                    else
                        textInterval.setText("-10");

                    if( !"".equals(textInterval2.getText().trim()))
                        endVal = Double.parseDouble(textInterval2.getText());
                    else
                        textInterval2.setText("10");

                    p.setInterval(startVal, endVal);

                }
                if(checkBoxXInt.isSelected())
                {
                    String str="";
                    ArrayList<Double> r = p.roots();
                    for(int i=0; i<r.size();i++){
                        str+=r.get(i);
                        if(i<r.size()-1) str+=", ";

                    }
                    labelGenXInt.setText("roots: "+str);

                }
                if(checkBoxYInt.isSelected())
                {
                    labelGenYInt.setText("yIntercept: "+p.yIntercept());
                }

                if(checkBoxVert.isSelected())
                {
                    labelGenYInt.setText("vertices: "+p.vertices());
                } 

            }catch(Exception exception)
            {
                exception.printStackTrace();
                System.out.println("Incorrect equations, enter equation in form of f(x) eg 3x^3+2x^2-4x+5 ");
            }
        }

    }
    /** 
     *main()
     *runs the GUI application
     *sample polyinomials:
     * x^3-2x^2-5x+6
     * -2x^3-2x^2-5x+6
     * -x^3-2x^2-5x-4x+6
     * x^2-4x-2-6
     * x^2-x-2
     * 2x+4
     * x+1
     **/
    public static void main(String args[]) {
        PolyFrame g = new PolyFrame();
        g.setLocation(20, 10);
        g.setSize(800, 500);
        g.setVisible(true);
    } 

}
