//NOTE : This code is Intended for BlueJ java IDE
// Author : Pragun Ashok
// Tetris Game in Java Console
import java.util.*;
import java.lang.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.util.Random;
class Piece{// every piece has 4 blocks
    
    int[] x = new int[4];//array to store the x coordinates of the blocks
    int[] y = new int[4];//array to store the y coordinates of the blocks
    char type; // type can be A/B/C/D/E/F/G, each type signifies a different kind of piece
}

public class Tetris extends JFrame implements KeyListener{
    static final int startx=5,starty=17;//The coordinates where a new piece is spawned
    static char grid[][] = new char [10][20];//the tetris grid
    static Piece ActivePiece=new Piece();//ActivePiece refers to the piece that is currently floating, and has not reached the bottom yet
    static Piece PieceOnHold = newPiece(0);//this is the piece thats on hold
    static boolean GameOver = false;
    static int Tetris=0;
    //Hold is a storage space that can store 1 piece.
    //if the player encounters a piece that they dont wanna use, they can put it in hold, and then hold will be full
    
    static int score = 0;
    public static void clrscr()// Function to clear screen
    {
        System.out.print('\u000C');
    }
    static void display()
    {//function to display the grid,hold,and score
        clrscr();
        System.out.print("+");
        for(int i=0;i<10;i++){
            System.out.print("-");
        }
        System.out.print("+");
        System.out.println();
        for(int j=19;j>=0;j--){
            System.out.print("|");
            for(int i=0;i<10;i++){
                if(grid[i][j]=='x') System.out.print(" ");
                else System.out.print(grid[i][j]);
            }
            System.out.print("|");
            if(j==19)
                System.out.print("      Hold : "+PieceOnHold.type);
            if(j==11 && GameOver())
                System.out.print("      GAME OVER");
            if(j==10)
                System.out.print("      Score : "+score);
            if(j==2){
                if(Tetris>0){
                    System.out.print("      TETRIS!!!!");
                    Tetris--;
                }
            }
                
            System.out.println();
        }
        System.out.print("+");
        for(int i=0;i<10;i++){
            System.out.print("-");
        }
        System.out.println("+");
        System.out.println("Instructions :");
        System.out.println(" a : Move Left ");
        System.out.println(" s : Move Down Faster ");
        System.out.println(" d : Move Right ");
        System.out.println(" r : Rotate Piece Clockwise ");
        System.out.println(" h : Move Piece to Hold, and call the piece from Hold");
        System.out.println(" NOTE: Do not attempt to rotate a piece when it its close to edge... program will crash ");
    }
    static Piece newPiece(int type){//function to return a piece object depending of a certain type
        Piece obj = new Piece();
        int j=0;
        int i=0;
        switch(type){
            case 0:
                    obj.type='0';
                    for(i=0;i<4;i++){
                        obj.x[i]=0;
                        obj.y[i]=0;
                    }
                    break;
            case 1: // Line
                    
                    
                    obj.type='A';
                    obj.x[0]=startx+i;
                    obj.y[0]=starty+j;
                    j--;
                    obj.x[1]=startx+i;
                    obj.y[1]=starty+j;
                    j+=2;
                    obj.x[2]=startx+i;
                    obj.y[2]=starty+j;
                    j++;
                    obj.x[3]=startx+i;
                    obj.y[3]=starty+j;
                    break;
            case 2: // Square   BB
                    //          BB
                    //          
                    obj.type = 'B';
                    obj.x[0]=startx+i;
                    obj.y[0]=starty+j;
                    i++;
                    obj.x[1]=startx+i;
                    obj.y[1]=starty+j;
                    j++;
                    obj.x[2]=startx+i;
                    obj.y[2]=starty+j;
                    i--;
                    obj.x[3]=startx+i;
                    obj.y[3]=starty+j;
                    break;
            case 3: // T piece       C
                    //              CCC
                    //             
                    obj.type='C';
                    obj.x[0]=startx+i;
                    obj.y[0]=starty+j;
                    i--;
                    obj.x[1]=startx+i;
                    obj.y[1]=starty+j;
                    i++;
                    j++;
                    obj.x[2]=startx+i;
                    obj.y[2]=starty+j;
                    j--;
                    i++;
                    obj.x[3]=startx+i;
                    obj.y[3]=starty+j;
                    break;
            case 4: //S piece   D
                    //          DD
                    //           D
                    obj.type='D';
                    obj.x[0]=startx+i;
                    obj.y[0]=starty+j;
                    i--;
                    obj.x[1]=startx+i;
                    obj.y[1]=starty+j;
                    j++;
                    obj.x[2]=startx+i;
                    obj.y[2]=starty+j;
                    i++;
                    j-=2;
                    obj.x[3]=startx+i;
                    obj.y[3]=starty+j;
                    break;
            case 5: // Z piece
                    obj.type='E';
                    obj.x[0]=startx+i;
                    obj.y[0]=starty+j;
                    i++;
                    obj.x[1]=startx+i;
                    obj.y[1]=starty+j;
                    j++;
                    obj.x[2]=startx+i;
                    obj.y[2]=starty+j;
                    i--;
                    j-=2;
                    obj.x[3]=startx+i;
                    obj.y[3]=starty+j;
                    break;
            case 6: //L piece
                    obj.type='F';
                    obj.x[0]=startx+i;
                    obj.y[0]=starty+j;
                    i++;
                    obj.x[1]=startx+i;
                    obj.y[1]=starty+j;
                    i--;
                    j++;
                    obj.x[2]=startx+i;
                    obj.y[2]=starty+j;
                    j++;
                    obj.x[3]=startx+i;
                    obj.y[3]=starty+j;
                    break;
            case 7: //Backwards L piece
                    obj.type='G';
                    obj.x[0]=startx+i;
                    obj.y[0]=starty+j;
                    i--;
                    obj.x[1]=startx+i;
                    obj.y[1]=starty+j;
                    i++;
                    j++;
                    obj.x[2]=startx+i;
                    obj.y[2]=starty+j;
                    j++;
                    obj.x[3]=startx+i;
                    obj.y[3]=starty+j;
                    break;
            default:
        }
        return obj;
    }
    static Piece newPiece(char type)
    {//same function to return a piece object but with overloading
        Piece obj = new Piece();
        switch(type){
            case '0': obj = newPiece(0);
                    break;
            case 'A': obj = newPiece(1);
                    break;
            case 'B': obj = newPiece(2);
                    break;
            case 'C': obj = newPiece(3);
                    break;
            case 'D': obj = newPiece(4);
                    break;
            case 'E': obj = newPiece(5);
                    break;
            case 'F': obj = newPiece(6);
                    break;
            case 'G': obj = newPiece(7);
                    break;
            default:
        }
        
        
        return obj;
    }
    
    static void clearGrid(){//This doesnt actually clear the whole grid, just clears active piece
        for(int i=0;i<4;i++){                        // This method is used to temporarily suspend the active piece, while it moves\rotates etc
            grid[ActivePiece.x[i]][ActivePiece.y[i]]='x';            
        }    
    }
    static void updateGrid(){//Function to update the grid after every move
        for(int i=0;i<4;i++){
            grid[ActivePiece.x[i]][ActivePiece.y[i]]=ActivePiece.type;
        }
    }
    static boolean isBlockedRight(){//check whether the ActivePiece has free space on its right
        boolean a = false;
        char x;
        for(int i=0;i<4;i++){
            x=grid[ActivePiece.x[i]+1][ActivePiece.y[i]];
            if(x!='x'&&x!=ActivePiece.type){
                return true;
            }
        }
        
        
        return a;
    }
    static boolean isBlockedLeft(){//check for free space on the left of activepiece
        boolean a = false;
        char x;
        for(int i=0;i<4;i++){
            x=grid[ActivePiece.x[i]-1][ActivePiece.y[i]];
            if(x!='x'&&x!=ActivePiece.type){
                return true;
            }
        }
        
        
        return a;
    }
    static boolean isAtBottom(){//check whether the active piece has reached the bottom, or has landed on top of another piece
        boolean a=false;
        char x;
        for(int i=0;i<4;i++){
            if(ActivePiece.y[i]==0){
                return true;
            }
        }
        for(int i=0;i<4;i++){
            x=grid[ActivePiece.x[i]][ActivePiece.y[i]-1];
            if(x!='x'&&x!=ActivePiece.type){
                return true;
            }
        }
        return a;
    }
    static boolean GameOver(){
        int j = 16;
        for(int i=0;i<10;i++){
            if(grid[i][j]!='x'&& Character.isLowerCase(grid[i][j]))return true;    
        }
        return false;
    }
    static void moveDown(){//move active piece one step down
        if(isAtBottom()) return;
        clearGrid();
        for(int i=0;i<4;i++){
            ActivePiece.y[i]--;
        }
        updateGrid();
    }
    public static void moveLeft(){//move to the left
        if(isBlockedLeft()){return;}
        for(int i=0;i<4;i++){
            if(ActivePiece.x[i]==0) return;
        }
        clearGrid();
        for(int i=0;i<4;i++){
            ActivePiece.x[i]--;
        }
        updateGrid();
    }
    public static void moveRight(){//Function to move the active Piece one space to the right
        if(isBlockedRight()){return;}
        for(int i=0;i<4;i++){
            if(ActivePiece.x[i]==9) return;
        }
        clearGrid();
        for(int i=0;i<4;i++){
            ActivePiece.x[i]++;
        }
        updateGrid();
    }
    static void Hold(){//function to exchange the active piece with the hold piece
                        // if hold is empty, active piece is just a new random piece
        Random rand = new Random();
        clearGrid();
        if(PieceOnHold.type=='0'){
            PieceOnHold = newPiece(ActivePiece.type);
            ActivePiece = newPiece(rand.nextInt(8));
        }else{
            Piece temp = newPiece(ActivePiece.type);
            ActivePiece = newPiece(PieceOnHold.type);
            PieceOnHold = newPiece(temp.type);
        }
        
        
    }
    public static void Rotate(){//function to rotate activepiece clockwise
        int temp;
        int[] x = new int[4];
        int[] y = new int[4];
        clearGrid();
        for(int i=0;i<4;i++){
            x[i]=ActivePiece.x[i]-ActivePiece.x[0];
            y[i]=ActivePiece.y[i]-ActivePiece.y[0];
            temp=x[i];
            x[i]=y[i];
            y[i]=(-1)*temp;
            
            
            x[i]=ActivePiece.x[0]+x[i];
            y[i]=ActivePiece.y[0]+y[i];
        }
        for(int i=0;i<4;i++){
            if(x[i]<0){
                for(int j=0;j<4;j++)x[j]++;
                i=0;
            }
            
            if(x[i]>9){
                for(int j=0;j<4;j++)x[j]--;
                i=0;
            }
        }
        for(int i=0;i<4;i++){
            ActivePiece.x[i]=x[i];
            ActivePiece.y[i]=y[i];
        }
        updateGrid();
    }
    public static void checkForCompletion(){//this function checks the grid to see if there are any completed rows
        int count=0;                // and it deletes the row if it is full
        for(int j=0;j<20;j++){
            boolean flag = true;
            for(int i=0;i<10;i++){
                if(grid[i][j]=='x'){
                    flag = false;
                }
            }
            if(flag){
                for(int k=j;k<20-1;k++){
                    for(int i=0;i<10;i++){
                        grid[i][k]=grid[i][k+1];
                    }
                }
                j--;
                count++;// count shows how many rows are removed simultaneously, to calculate scorebonus
            }
        }
        switch(count){
            case 0:
                    break;
            case 1: score+=1000;//only 1 row removed
                    break;
            case 2: score+=4000;//2 rows filled in the same move
                    break;
            case 3: score+=9000;// 3 rows filled in the same move
                    break;
            case 4: score+=16000;// 4 rows
                    Tetris=2;
            default:
        }
    }
    public static void Solidify(){//this function is called whenever active piece reaches bottom
        for(int i=0;i<10;i++){
            for(int j=0;j<20;j++){
                if(grid[i][j]!='x'){
                    grid[i][j]=Character.toLowerCase(grid[i][j]);
                }
            }
        }// so what this function does is it converts the characters to lower case, so that when 
        //when the active piece changes, the finishedpieces are retained
    }
    public void paint(Graphics g){
        g.drawLine(20,40,100,90);
        g.drawRect(20,150,60,50);
    }
    public static void main(String [] args){
        Scanner in = new Scanner(System.in);
        Random rand = new Random();
        char ch;
        
        JFrame frame = new JFrame();
        JPanel panel = new JPanel(new FlowLayout());
        JTextField field = new JTextField(20);
        field.addKeyListener(new Tetris());
        panel.add(field);
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500,500);
        frame.setVisible(true);
        for(int i=0;i<10;i++){
            for(int j=0;j<20;j++){
                grid[i][j]='x';
            }
        }
        int randint = rand.nextInt(8);
        ActivePiece = newPiece(randint);
        for(int i=0;i<4;i++){
            System.out.print(" "+ActivePiece.x[i]);
            System.out.print(ActivePiece.y[i]);
            
        }System.out.println();
        for(int i=0;i<4;i++){
            grid[ActivePiece.x[i]][ActivePiece.y[i]]=ActivePiece.type;
        }
        
        int delay = 1000;
        display();
        while(!GameOver()){
            while(!isAtBottom()){    
                try{
                    Thread.sleep(delay);   
                }catch(Exception e){
                    
                }
                moveDown();
                display();
            }
            try{
                Thread.sleep(delay/3);   
            }catch(Exception e){
                    
            }
            checkForCompletion();
            Solidify();
            ActivePiece = newPiece(rand.nextInt(8));
            frame.repaint();
            delay-=15;//decremening the delay, so that the game gets faster as you play
        }
        display();
        if(GameOver) return;
    }
    public void keyTyped(KeyEvent e){//keyboard input
        char x = e.getKeyChar();
        switch(x){
            case 'a':
                    moveLeft();
                    break;
            case 'd':
                    moveRight();
                    break;
            case 's':
                    moveDown();
                    score+=14;
                    break;
            case 'r':
                    Rotate();
                    break;
            case 'h':
                    Hold();
            default:
                    int Key= e.getKeyCode();
                    switch(Key){
                        case KeyEvent.VK_LEFT:
                            moveLeft();
                            break;
                        case KeyEvent.VK_RIGHT:
                            moveRight();
                            break;
                        case KeyEvent.VK_DOWN:
                            moveDown();
                            break;
                        case KeyEvent.VK_A:
                            moveLeft();
                            break;
                        case KeyEvent.VK_S:
                            moveDown();
                            break;
                        case KeyEvent.VK_D:
                            moveRight();
                            break;
                        case KeyEvent.VK_R:
                            Rotate();
                            break;
                        case KeyEvent.VK_H:
                            Hold();
                            break;
                        default:
                    }
        }
        display();
    }
    public void keyPressed(KeyEvent e){}
    public void keyReleased(KeyEvent e){}
}
