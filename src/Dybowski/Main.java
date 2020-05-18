package Dybowski;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;


class MyPanel extends JPanel implements MouseListener, MouseMotionListener {

    // plansza binarna
    public int[][]plansza={
            {0,2,0,2,0,2,0,2},
            {2,0,2,0,2,0,2,0},
            {0,2,0,2,0,2,0,2},
            {1,0,1,0,1,0,1,0},
            {0,1,0,1,0,1,0,1},
            {3,0,3,0,3,0,3,0},
            {0,3,0,3,0,3,0,3},
            {3,0,3,0,3,0,3,0}
    };

    // plansza stringow
    public String[][]planszaS={
            {"A1","B1","C1","D1","E1","F1","G1","H1"},
            {"A2","B2","C2","D2","E2","F2","G2","H2"},
            {"A3","B3","C3","D3","E3","F3","G3","H3"},
            {"A4","B4","C4","D4","E4","F4","G4","H4"},
            {"A5","B5","C5","D5","E5","F5","G5","H5"},
            {"A6","B6","C6","D6","E6","F6","G6","H6"},
            {"A7","B7","C7","D7","E7","F7","G7","H7"},
            {"A8","B8","C8","D8","E8","F8","G8","H8"}
    };

    public int sekwencja_klikania = 0;


    // gridy zczytane od myszki
    int x=0;
    int y = 0;
    int xx = 0;
    int yy = 0;
    int tempx = 0;
    int tempy = 0;

    // potrzebne do zmiany gracza
    boolean gracz1= true;
    boolean gracz2=true;

    // gracz 1 gridy zbitego pionka
    int zbity_prawy_x=0;
    int zbity_prawy_y=0;

    int zbity_lewy_x=0;
    int zbity_lewy_y=0;

    int zbity_prawy_x_dol=0;
    int zbity_prawy_y_dol=0;

    int zbity_lewy_x_dol=0;
    int zbity_lewy_y_dol=0;

    // gracz 2 gridy zbitego pionka
    int zbity_prawy_x2=0;
    int zbity_prawy_y2=0;

    int zbity_lewy_x2=0;
    int zbity_lewy_y2=0;


    public MyPanel() {
        setPreferredSize(new Dimension(945,620));
        addMouseListener(this);
        addMouseMotionListener(this);
        System.out.println(plansza[4][5]);
    }

    void Rysuj(Graphics2D g2){
        int test = 0;
        for(int i = 0 ; i < 8 ; i++){
            for(int j = 0 ; j < 8 ; j++){
                test = plansza[i][j];
                switch (test){
                    case 0:
                        MySquare pole = new MySquare(j*70, i*70, 70 , 70 , test);
                        pole.setName(String.format("%s%d", i, j));
                        g2.setPaint(Color.red);
                        g2.fill(pole.getSquare());
                        break;
                    case 1:
                        MySquare pole4 = new MySquare(j*70, i*70, 70 , 70 , test);
                        pole4.setName(String.format("%s%d", i, j));
                        g2.setPaint(Color.black);
                        g2.fill(pole4.getSquare());
                        break;
                    case 2:
                        MySquare pole2 = new MySquare(j*70, i*70, 70 , 70 , test);
                        pole2.setName(String.format("%s%d", i, j));
                        g2.setPaint(Color.black);
                        g2.fill(pole2.getSquare());
                        g2.setPaint(Color.white);
                        g2.drawOval(j*70+10, i*70+10, 45, 45);
                        break;
                    case 3:
                        MySquare pole3 = new MySquare(j*70, i*70, 70 , 70 , test);
                        pole3.setName(String.format("%s%d", i, j));
                        g2.setPaint(Color.black);
                        g2.fill(pole3.getSquare());
                        g2.setPaint(Color.yellow);
                        g2.drawOval(j*70+10, i*70+10, 45, 45);
                        break;
                    case 4:
                        MySquare pole5 = new MySquare(j*70, i*70, 70 , 70 , test);
                        pole5.setName(String.format("%s%d", i, j));
                        g2.setPaint(Color.yellow);
                        g2.fill(pole5.getSquare());
                        break;

                    case 5:
                        MySquare pole6 = new MySquare(j*70, i*70, 70 , 70 , test);
                        pole6.setName(String.format("%s%d", i, j));
                        g2.setPaint(Color.black);
                        g2.fill(pole6.getSquare());
                        g2.setPaint(Color.yellow);
                        g2.fillOval(j*70+10, i*70+10, 45, 45);
                        break;
                    case 6:
                        MySquare pole7 = new MySquare(j*70, i*70, 70 , 70 , test);
                        pole7.setName(String.format("%s%d", i, j));
                        g2.setPaint(Color.black);
                        g2.fill(pole7.getSquare());
                        g2.setPaint(Color.white);
                        g2.fillOval(j*70+10, i*70+10, 45, 45);
                        break;
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Rysuj(g2);
    }

    String getField(int xx , int yy){
        String moje_pole = null;
        for(int i = 0 ; i < 8 ; i++){
            for(int j=0 ; j< 8 ; j++){
                if(xx <= (j+1)*70 && yy <= (i+1)*70){
                    moje_pole = planszaS[i][j];
                    return moje_pole;
                }
            }
        }
        return null;
    }

    int getWiersz(int xx , int yy){
        int wiersz = 0;
        for(int i = 0 ; i < 8 ; i++){
            for(int j=0 ; j< 8 ; j++){
                if(xx <= (j+1)*70 && yy <= (i+1)*70){
                    wiersz = i;
                    return wiersz;
                }
            }
        }
        return 99;
    }

    int getKolumna(int xx , int yy){
        int kolumna = 0;
        for(int i = 0 ; i < 8 ; i++){
            for(int j=0 ; j< 8 ; j++){
                if(xx <= (j+1)*70 && yy <= (i+1)*70){
                    kolumna = j;
                    return kolumna;
                }
            }
        }
        return 99;
    }

    void reset_podswietlenia(){
        for(int i = 0 ; i < 8 ; i++){
            for (int j = 0 ; j < 8 ; j++){
                if(plansza[i][j] == 4 ) plansza[i][j] =1;
            }
        }
    }

    void sprawdz_ruch(int xx , int yy){
        boolean flag = xx-2 >=0;
        boolean flag2 = yy+2 < 8;
        boolean flag3 = yy-2 >=0;
        int x1 = xx-1; // wiersz wyzej
        int x2 = xx-2; // 2 wiersze wyzej;

        int y1_prawo = yy+1; // kolumna prawo
        int y1_lewo = yy-1; // kolumna lewo
        int y2_prawo = yy+2;
        int y2_lewo = yy-2;

        if(y1_lewo >= 0){  // sprawdz lewa strone
            if(plansza[x1][y1_lewo] == 1) plansza[x1][y1_lewo]=4;
            if(plansza[x1][y1_lewo] == 2 || plansza[x1][y1_lewo] == 6){
                if(flag && flag3){
                    zbity_lewy_x = x1;
                    zbity_lewy_y = y1_lewo;
                    if(plansza[x2][y2_lewo] == 1) plansza[x2][y2_lewo]=4;
                }
            }
        }
        if(y1_prawo < 8){
            if(plansza[x1][y1_prawo] == 1) plansza[x1][y1_prawo] = 4;
            if(plansza[x1][y1_prawo] == 2 || plansza[x1][y1_prawo] == 6){
                if (flag && flag2){
                    zbity_prawy_x = x1;
                    zbity_prawy_y = y1_prawo;
                    if(plansza[x2][y2_prawo] == 1) plansza[x2][y2_prawo] =4;
                }
            }
        }


    }

    void sprawdz_ruch2(int xx , int yy){
        boolean flag = xx+2 < 8;
        boolean flag2 = yy+2 < 8;
        boolean flag3 = yy-2 >=0;
        int x1 = xx+1; // wiersz wyzej
        int x2 = xx+2; // 2 wiersze wyzej;

        int y1_lewo = yy-1;
        int y1_prawo = yy+1;
        int y2_lewo = yy-2;
        int y2_prawo = yy+2;

        if(y1_lewo >= 0){
            if(plansza[x1][y1_lewo] == 1) plansza[x1][y1_lewo]=4;
            if(plansza[x1][y1_lewo] == 3  || plansza[x1][y1_lewo] == 5){
                if(flag && flag3){
                    zbity_lewy_x2 = x1;
                    zbity_lewy_y2 = y1_lewo;
                    if(plansza[x2][y2_lewo] == 1) plansza[x2][y2_lewo]=4;
                }
            }
        }

        if(y1_prawo < 8){
            if(plansza[x1][y1_prawo] == 1) plansza[x1][y1_prawo] = 4;
            if(plansza[x1][y1_prawo] == 3 || plansza[x1][y1_prawo] == 5){
                if(flag && flag2){
                    zbity_prawy_x2 = x1;
                    zbity_prawy_y2 = y1_prawo;
                    if(plansza[x2][y2_prawo] == 1) plansza[x2][y2_prawo]=4;
                }
            }
        }

    }

    void sprawdz_ruch_krolowka(int xx , int yy , int gracz){
        int enemy;
        int enemy2;
        if(gracz == 1){
            enemy = 2;
            enemy2 = 6;
        }else{
            enemy = 3;
            enemy2 = 5;
        }
        int x_1_gora = xx-1;
        int x_2_gora = xx-2;
        int x_1_dol = xx+1;
        int x_2_dol = xx+2;
        int y_1_lewo = yy-1;
        int y_2_lewo = yy-2;
        int y_1_prawo = yy+1;
        int y_2_prawo = yy+2;

        boolean flag_gora = x_2_gora >=0;
        boolean flag_dol = x_2_dol < 8;
        boolean flaga_lewo = y_2_lewo >=0;
        boolean flaga_prawo = y_2_prawo < 8;

        if(y_1_lewo >=0 && x_1_gora >= 0){
            if(plansza[x_1_gora][y_1_lewo] == 1) plansza[x_1_gora][y_1_lewo] =4;
            if(plansza[x_1_gora][y_1_lewo] == enemy || plansza[x_1_gora][y_1_lewo] == enemy2){ // przeciwnik
                if(flag_gora && flaga_lewo){
                    zbity_lewy_x = x_1_gora;
                    zbity_lewy_y = y_1_lewo;
                    if(plansza[x_2_gora][y_2_lewo] == 1) plansza[x_2_gora][y_2_lewo] =4;
                }
            }
        }
        if(y_1_prawo < 8 && x_1_gora >= 0){
            if(plansza[x_1_gora][y_1_prawo] == 1) plansza[x_1_gora][y_1_prawo] = 4;
            if(plansza[x_1_gora][y_1_prawo] == enemy || plansza[x_1_gora][y_1_prawo] == enemy2){ // przeciwnik
                if(flag_gora && flaga_prawo){
                    zbity_prawy_x = x_1_gora;
                    zbity_prawy_y = y_1_prawo;
                    if(plansza[x_2_gora][y_2_prawo] == 1) plansza[x_2_gora][y_2_prawo] =4;
                }
            }
        }

        if(y_1_lewo >= 0 && x_1_dol < 8){
            if(plansza[x_1_dol][y_1_lewo]==1) plansza[x_1_dol][y_1_lewo]=4;
            if(plansza[x_1_dol][y_1_lewo]==enemy || plansza[x_1_dol][y_1_lewo]==enemy2){
                if(flag_dol && flaga_lewo){
                    zbity_lewy_x_dol = x_1_dol;
                    zbity_lewy_y_dol = y_1_lewo;
                    if(plansza[x_2_dol][y_2_lewo] == 1)plansza[x_2_dol][y_2_lewo] = 4;
                }
            }
        }

        if(y_1_prawo <8 && x_1_dol <8){
            if(plansza[x_1_dol][y_1_prawo] == 1 )plansza[x_1_dol][y_1_prawo] =4;
            if(plansza[x_1_dol][y_1_prawo] == enemy || plansza[x_1_dol][y_1_prawo] == enemy2){
                if(flag_dol && flaga_prawo){
                    zbity_prawy_x_dol = x_1_dol;
                    zbity_prawy_y_dol = y_1_prawo;
                    if(plansza[x_2_dol][y_2_prawo] == 1)plansza[x_2_dol][y_2_prawo] =4;
                }
            }
        }


    }


    @Override
    public Point getToolTipLocation(MouseEvent event) {
        return super.getToolTipLocation(event);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        sekwencja_klikania++;
        if(gracz1){
           switch (sekwencja_klikania){
               case 1:
                   System.out.println("Gracz 1");
                   System.out.println(getField(e.getX(),e.getY()));  // pobranie lokalizacji pola
                   x = getWiersz(e.getX(),e.getY()); // pobranie grida
                   y = getKolumna(e.getX(),e.getY()); // pobranie grida

                   // wybierz pionek
                   if(plansza[x][y] == 3) {         // to jest jego pionek gracza 1
                       sprawdz_ruch(x,y);           // ustaw mozliwe ruchy na kolor zolty
                       repaint();                   // odswiez wyglad mapy
                   }else if(plansza[x][y] == 5){
                       sprawdz_ruch_krolowka(x,y,1);
                       repaint();
                   }else{
                       sekwencja_klikania--; // blokada klikniecia nieswojego
                   }

                   break;
               case 2:
                   // wybierz miejsce
                   System.out.println(getField(e.getX(),e.getY())); // wypisanie grida string
                   xx = getWiersz(e.getX(),e.getY());
                   yy = getKolumna(e.getX(),e.getY());
                   if(plansza[xx][yy] != 4){
                       System.out.println("Złe pole");
                       sekwencja_klikania--;
                   }else{
                       tempx = plansza[x][y]; // zapis dla pozniejszej podmiany
                       tempy = plansza[xx][yy];

                       plansza[x][y] = tempy;
                       plansza[xx][yy] = tempx;

                       if(xx == 0){
                           plansza[xx][yy] = 5;
                           System.out.println("Czas na krolowke");
                       }

                       if(x == xx && y == yy){  // blokada ze mozesz przycisnac zobaczyc ruch i przycisnac ponownie i nastepny pionek
                           sekwencja_klikania--;
                           gracz1 = true;
                           gracz2 = false;
                       }else{
                           gracz1 =false;
                           gracz2 = true;
                       }

                       // bicie
                       if(y == yy-2 && x == xx+2){ // zbijam prawego gornego
                           plansza[zbity_prawy_x][zbity_prawy_y] = 1;
                           zbity_prawy_x = 0;
                           zbity_prawy_y = 0;

                       }
                       if(y == yy+2 && x == xx+2) { // zbijam lewy gornego
                           plansza[zbity_lewy_x][zbity_lewy_y] = 1;
                           zbity_lewy_y = 0;
                           zbity_lewy_x = 0;
                       }

                       if(y == yy+2 && x == xx-2){
                           plansza[zbity_lewy_x_dol][zbity_lewy_y_dol]=1;
                           zbity_lewy_x_dol=0;
                           zbity_lewy_y_dol=0;
                       }
                       if(y == yy-2 && x == xx-2){
                           plansza[zbity_prawy_x_dol][zbity_prawy_y_dol]=1;
                           zbity_prawy_x_dol =0;
                           zbity_prawy_y_dol =0;
                       }

                       sekwencja_klikania = 0;
                       // resetujemy zamienione podswietlone
                       reset_podswietlenia();
                       repaint();
                   }

                   break;
           }

       }else if(gracz2){
           switch (sekwencja_klikania){
               case 1:
                   System.out.println("Gracz 2");
                   System.out.println(getField(e.getX(),e.getY()));
                   x = getWiersz(e.getX(),e.getY());
                   y = getKolumna(e.getX(),e.getY());
                   // wybierz pionek
                   if(plansza[x][y] == 2) { // to jest jego pionek
                       sprawdz_ruch2(x,y);
                       repaint();
                   }else if(plansza[x][y] == 6){
                       sprawdz_ruch_krolowka(x,y,2);
                       repaint();
                   }
                   else{
                       sekwencja_klikania--;
                   }
                   break;
               case 2:
                   // wybierz miejsce
                   System.out.println(getField(e.getX(),e.getY()));
                   xx = getWiersz(e.getX(),e.getY());
                   yy = getKolumna(e.getX(),e.getY());
                   if(plansza[xx][yy] !=4){
                       System.out.println("Złe pole");
                       sekwencja_klikania--;
                   }else{
                       tempx = plansza[x][y];
                       tempy = plansza[xx][yy];

                       plansza[x][y] = tempy;
                       plansza[xx][yy] = tempx;

                       if(xx == 7){
                           plansza[xx][yy] = 6;
                       }

                       if(x == xx && y == yy){
                           gracz2 = true;
                           gracz1 = false;
                           sekwencja_klikania--;
                       }else{
                           gracz2 = false;
                           gracz1 = true;
                       }

                       if(plansza[xx][yy] ==6) {
                           // bicie
                           if (y == yy - 2 && x == xx + 2) { // zbijam prawego gornego
                               plansza[zbity_prawy_x][zbity_prawy_y] = 1;
                               zbity_prawy_x = 0;
                               zbity_prawy_y = 0;


                           }
                           if (y == yy + 2 && x == xx + 2) { // zbijam lewy gornego
                               plansza[zbity_lewy_x][zbity_lewy_y] = 1;
                               zbity_lewy_y = 0;
                               zbity_lewy_x = 0;
                           }
                           if(y == yy+2 && x == xx-2){
                               plansza[zbity_lewy_x_dol][zbity_lewy_y_dol]=1;
                               zbity_lewy_x_dol=0;
                               zbity_lewy_y_dol=0;
                           }
                           if(y == yy-2 && x == xx-2){
                               plansza[zbity_prawy_x_dol][zbity_prawy_y_dol]=1;
                               zbity_prawy_x_dol =0;
                               zbity_prawy_y_dol =0;
                           }
                       }else{
                           // dla pionka
                           if(y == yy+2 ){
                               plansza[zbity_lewy_x2][zbity_lewy_y2]=1;
                               zbity_lewy_x2=0;
                               zbity_lewy_y2=0;

                           }
                           if(y == yy-2 ){
                               plansza[zbity_prawy_x2][zbity_prawy_y2]=1;
                               zbity_prawy_x2 =0;
                               zbity_prawy_y2 =0;
                           }
                       }
                       sekwencja_klikania =0;
                       reset_podswietlenia();
                       repaint();
                   }


                   break;
           }
       }



    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}

class MyFrame extends JFrame {



    public MyFrame() {
        super("Warcaby by Dybek");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setBackground(Color.gray);
        setSize(560,580);
        setLocation(250,250);
        JPanel panel = new MyPanel();
        add(panel);
    }


}

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MyFrame();
            }
        });
    }
}
