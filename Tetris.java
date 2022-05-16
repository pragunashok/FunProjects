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
class Block{
    int x,y;
    Block(int x,int y){
        this.x=x;
        this.y=y;
    }
    Block(){}
}
class Piece{
    int[] x = new int[4];
    int[] y = new int[4];
    char type;
}

public class Tetris implements KeyListener{
    static final int startx=5,starty=10;
    public static void clrscr()
    {
        System.out.print('\u000C');
    }
    static void display(char[][]grid){
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
            System.out.println("|");
        }
        System.out.print("+");
        for(int i=0;i<10;i++){
            System.out.print("-");
        }
        System.out.print("+");
    }
    static Piece newPiece(int type){
        Piece obj = new Piece();
        int j=0;
        int i=0;
        switch(type){
            case 1: // Line
                    
                    for(i=-1;i<3;i++,j++){
                        obj.x[j]=startx;
                        obj.y[j]=starty+i;
                    }
                    obj.type='L';
                    break;
            case 2: // Square   SS
                    //          SS
                    //          
                    obj.type = 'S';
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
            case 3: // T piece       T
                    //              TTT
                    //             
                    obj.type='T';
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
            case 4: //S piece   S
                    //          SS
                    //           S
                    obj.type='S';
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
                    obj.type='Z';
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
                    obj.type='L';
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
                    obj.type='1';
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
    static void clearGrid(Piece obj, char [][] grid){//This doesnt actually clear the whole grid, just clears active piece
        for(int i=0;i<4;i++){                        // This method is used to temporarily suspend the active piece, while it moves\rotates etc
            grid[obj.x[i]][obj.y[i]]='x';
        }    
    }
    static void updateGrid(Piece obj, char [][] grid){
        for(int i=0;i<4;i++){
            grid[obj.x[i]][obj.y[i]]=obj.type;
        }
    }
    static boolean isAtBottom(Piece obj,char [][] grid){
        boolean a=false;
        char x;
        for(int i=0;i<4;i++){
            if(obj.y[i]==0){
                return true;
            }
        }
        for(int i=0;i<4;i++){
            x=grid[obj.x[i]][obj.y[i]-1];
            if(x!='x'&&x!=obj.type){
                return true;
            }
        }
        return a;
    }
    
    static void moveDown(Piece obj, char [][] grid){
        if(isAtBottom(obj,grid)) return;
        clearGrid(obj,grid);
        for(int i=0;i<4;i++){
            obj.y[i]--;
        }
        updateGrid(obj,grid);
    }
    public static void moveLeft(Piece obj, char [][] grid){
        for(int i=0;i<4;i++){
            if(obj.x[i]==0) return;
        }
        clearGrid(obj,grid);
        for(int i=0;i<4;i++){
            obj.x[i]--;
        }
        updateGrid(obj,grid);
    }
    public static void moveRight(Piece obj, char [][] grid){
        for(int i=0;i<4;i++){
            if(obj.x[i]==9) return;
        }
        clearGrid(obj,grid);
        for(int i=0;i<4;i++){
            obj.x[i]++;
        }
        updateGrid(obj,grid);
    }
    public static int Quadrant(int rootx,int rooty,int x,int y){
        if(x>rootx&&y>=rooty) return 1;
        if(x>=rootx&&y<rooty) return 2;
        if(x<rootx&&y<=rooty) return 3;
        if(x<=rootx&&y>rooty) return 4;
        return 0;
    }
    public static void Rotate(Piece obj, char [][] grid){
        int temp;
        int x,y;
        clearGrid(obj,grid);
        for(int i=1;i<4;i++){
            x=obj.x[i]-obj.x[0];
            y=obj.y[i]-obj.y[0];
            switch(x){
                case -2:switch(y){
                            case -2:x=-2;y=2;
                                    break;
                            case -1:x=-1;y=2;
                                    break;
                            case 0: x=0;y=2;
                                    break;
                            case 1: x=1;y=2;
                                    break;
                            case 2: x=2;y=2;
                                    break;
                            default:
                        }
                        break;
                case -1:switch(y){
                            case -2:x=-2;y=1;
                                    break;
                            case -1:x=-1;y=1;
                                    break;
                            case 0:x=0;y=1;
                                    break;
                            case 1:x=1;y=1;
                                    break;
                            case 2:x=2;y=1;
                                    break;
                            default:
                        }
                        break;
                
                case 0: switch(y){
                            case -2:x=-2;y=0;
                                    break;
                            case -1:x=-1;y=0;
                                    break;
                            case 0:x=0;y=0;
                                    break;
                            case 1:x=1;y=0;
                                    break;
                            case 2:x=2;y=0;
                                    break;
                            default:
                        }
                        break;
                        
                case 1: switch(y){
                            case -2:x=-2;y=-1;
                                    break;
                            case -1:x=-1;y=-1;
                                    break;
                            case 0:x=0;y=-1;
                                    break;
                            case 1:x=1;y=-1;
                                    break;
                            case 2:x=2;y=-1;
                                    break;
                            default:
                        }
                        break;
                case 2: switch(y){
                            case -2:x=-2;y=-2;
                                    break;
                            case -1:x=-1;y=-2;
                                    break;
                            case 0:x=0;y=-2;
                                    break;
                            case 1:x=1;y=-2;
                                    break;
                            case 2:x=2;y=-2;
                                    break;
                            default:
                        }
                        break;
                
                default:
            }
            obj.x[i]=obj.x[0]+x;
            obj.y[i]=obj.y[0]+y;
        }
        updateGrid(obj,grid);
    }
    public static void checkForCompletion(char[][] grid){
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
            }
        }
    }
    public static void Solidify(char[][]grid){
        for(int i=0;i<10;i++){
            for(int j=0;j<20;j++){
                if(grid[i][j]!='x'){
                    grid[i][j]=Character.toLowerCase(grid[i][j]);
                }
            }
        }
    }
    static char grid[][] = new char [10][20];
    static Piece ActivePiece=new Piece();
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
        
        
        display(grid);
        while(true){
            while(!isAtBottom(ActivePiece,grid)){    
                try{
                    Thread.sleep(1000);   
                }catch(Exception e){
                    
                }
                moveDown(ActivePiece,grid);
                display(grid);
            }
            checkForCompletion(grid);
            ActivePiece = newPiece(rand.nextInt(8));
        }
    }
    public void keyTyped(KeyEvent e){
        char x = e.getKeyChar();
        switch(x){
            case 'a':
                    moveLeft(ActivePiece,grid);
                    display(grid);
                    break;
                    
            case 'd':
                    moveRight(ActivePiece,grid);
                    display(grid);
                    break;
            case 's':
                    moveDown(ActivePiece,grid);
                    display(grid);
                    break;
            case 'r':
                    Rotate(ActivePiece,grid);
                    display(grid);
                    break;
            default:
        }
    }
    public void keyPressed(KeyEvent e){
        
    }
    public void keyReleased(KeyEvent e){
        
    }
}
/*class KeyDemo implements KeyListener{
    public void keyTyped(KeyEvent e){
    
    }
    public void keyPressed(KeyEvent e){
        
    }
    public void keyReleased(KeyEvent e){
        
    }
    
}*/
