import javax.swing.*;

public class Card {
    public JButton button;
    public String name;

    public Card(int x,int y,int width,int height){
        button = new JButton();
        button.setBounds(x,y,width,height);
    }
    public void showName(){
        button.setText(name);

    }
    public void clear()  {
        button.setText("");
    }

}