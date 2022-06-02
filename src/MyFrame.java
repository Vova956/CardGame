import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Thread.sleep;

public class MyFrame extends JFrame {
    public static boolean canPair(boolean[][] arr){
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if(arr[i][j])
                    return true;
            }
        }
        return false;
    }
    int move = 1;
    int x1 = 0;
    int y1 = 0;
    int x2 = 0;
    int y2 = 0;
    int victory = 36;
    public MyFrame() throws InterruptedException {
        setSize(800,525);
        setLocation(150,150);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Find Pair");
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0,0,800,525);
        panel.setBackground(Color.BLUE);
        add(panel);

        String[] names = {"Cat","Door","Pen","Vova","Mouse"};


        Card[][] cards = new Card[6][6];
        boolean[][] moves = new boolean[6][6];


        int x = 0;
        int y = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                moves[i][j] = true;
                cards[i][j] = new Card(x,y,120,70);
                panel.add(cards[i][j].button);
                x += 130;
            }
            y += 80;
            x = 0;
        }



        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if(moves[i][j]){
                    cards[i][j].name = names[(int) (Math.random() * 5)];
                    moves[i][j] = false;
                    while(true) {
                        int a = (int)(Math.random()*6);
                        int b = (int)(Math.random()*6);
                        if(moves[a][b]) {
                            cards[a][b].name = cards[i][j].name;
                            moves[a][b] = false;
                            break;
                        }
                    }

                }
            }
        }



        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                int finalJ = j;
                int finalI = i;
                cards[i][j].button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if(move == 1){
                            x1 = finalJ;
                            y1 = finalI;
                            cards[y1][x1].showName();
                            move = 2;
                        }
                        else if(move == 2) {
                           x2 = finalJ;
                           y2 = finalI;
                           cards[y2][x2].showName();
                           move = 1;

                          if(cards[y1][x1].name == cards[y2][x2].name) {
                               cards[y2][x2].showName();
                               cards[y1][x1].showName();
                               victory -= 2;
                           }
                          else {
                              cards[y2][x2].showName();
                              try {
                                  sleep(2000);
                              } catch (InterruptedException interruptedException) {
                                  interruptedException.printStackTrace();
                              }
                              cards[y1][x1].clear();
                              cards[y2][x2].clear();


                          }

                           if(victory == 0)
                               setVisible(false);
                        }
                    }
                });
            }
        }


        setVisible(true);
    }
}
