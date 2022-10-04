import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class Calculator {
    public static void main(String[] args) {
        JFrame frame=new Calc();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true); 
    }
}

class Calc extends JFrame {
    private final Font BIGGER_FONT=new Font("monspaced",Font.PLAIN,20);
    private JTextField textField;
    private boolean number=true;
    private String equalOp="=";
    private CalculatorOp op=new CalculatorOp();

    public Calc(){
    
        textField=new JTextField("0",12);
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setFont(BIGGER_FONT);

        ActionListener numberListener=new NumberListener();
        String buttonOrder="123456789 0 ";
        JPanel buttoPanel=new JPanel();
        buttoPanel.setLayout(new GridLayout(4,4,4,4));

        for (int i=0;i<buttonOrder.length();i++)
        {
            String key=buttonOrder.substring(i,i+1);
            if (key.equals(" ")){
                buttoPanel.add(new JLabel(""));
            }
            else{
                JButton button=new JButton(key);
                button.addActionListener(numberListener);
                button.setFont(BIGGER_FONT);
                buttoPanel.add(button);
            }
        }
        
        ActionListener operatorListener=new OperatorListener();
        JPanel panel= new JPanel();
        panel.setLayout(new GridLayout(4,4,4,4));
        String[] opOrder={"%","/","x","-","+",".","C","="};
        for (int i=0;i<opOrder.length;i++){
            JButton button=new JButton(opOrder[i]);
            button.addActionListener(operatorListener);
            button.setFont(BIGGER_FONT);
            panel.add(button);

        }

        JPanel pan=new JPanel();
        pan.setLayout(new BorderLayout(4,4));
        pan.add(textField,BorderLayout.NORTH);
        pan.add(buttoPanel,BorderLayout.CENTER);
        pan.add(panel,BorderLayout.EAST);
        this.setContentPane(pan);
        this.pack();
        this.setTitle("Calculator");
        this.setResizable(false);
    }
    private void action(){
        number=true;
        textField.setText("0");
        equalOp="=";
        op.setTotal("0");
    }
    
    class OperatorListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if (number){
                action();
                textField.setText("0");
            }
            else{
                number=true;
                String displaytext=textField.getText();
                if (equalOp.equals("=")){
                    op.setTotal(displaytext);
                }
                else if(equalOp.equals("+")){
                    op.add(displaytext);

                }
                else if(equalOp.equals("-")){
                    op.substract(displaytext);

                }
                else if(equalOp.equals("*")){
                    op.multiply(displaytext);
                }
                else if(equalOp.equals("/")){
                    op.divide(displaytext);
                }
                else if(equalOp.equals("%")){
                    op.percentage(displaytext);
                }
                textField.setText(""+op.getTotalString());
                equalOp=e.getActionCommand();
            }
        }
    }
    class NumberListener implements ActionListener{
            public void actionPerformed(ActionEvent event){
                String digit=event.getActionCommand();
                if (number){
                    textField.setText(digit);
                    number=false;
                }
                else{
                    textField.setText(textField.getText()+digit);
                }
            }
        }
    
    public class CalculatorOp{
        private double total;
        public CalculatorOp(){
            total=0;

        }
        public String getTotalString(){
            return ""+total;
        }
        public void setTotal(String n){
            total=convertToNumber(n);
        }
        public void add(String n){
            total+=convertToNumber(n);
        }
        public void substract(String n){
            total-=convertToNumber(n);
        }
        public void multiply(String n){
            total*=convertToNumber(n);
        }
        public void divide(String n){
            total/=convertToNumber(n);
        }
        public void percentage(String n){
            total/=convertToNumber(n)/100;
        }
        private double convertToNumber(String n){
            return Double.parseDouble(n);
        }
    }

}
    
    