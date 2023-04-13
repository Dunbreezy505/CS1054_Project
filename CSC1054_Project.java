import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import java.util.*;
import javafx.geometry.*;
import javafx.scene.paint.*;
//imports
import javafx.scene.image.*;
import javafx.event.*;
import javafx.animation.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import java.util.*;
import javafx.geometry.*;
import javafx.scene.paint.*;
import javafx.scene.image.*;
import javafx.scene.canvas.*;



public class CSC1054_Project extends Application
{
   //Private Variables
   private BorderPane root = new BorderPane();   
   private HBox top = new HBox();
   private FlowPane fp = new FlowPane();
   private Button[][] topBArray=new Button[4][4];
   private Button[][] bottomBArray=new Button[4][4];
   private Button[][] leftBArray=new Button[4][4];
   private Button[][] rightBArray=new Button[4][4];
   private Canvas[][] Circles = new Canvas[4][4];
   private Boolean[][] vis=new Boolean[4][4];
   private Label header;
   private HBox hb1 = new HBox();
   private HBox hb2 = new HBox();
   private HBox hb3 = new HBox();
   private HBox hb4 = new HBox();
   private GamePane[][] gp = new GamePane[4][4];
   private Boolean[][] tVis = new Boolean[4][4];
   private Boolean[][] bVis = new Boolean[4][4];
   private Boolean[][] lVis = new Boolean[4][4];
   private Boolean[][] rVis = new Boolean[4][4];
   private int ballz=0;
   private int moves=0;
   private Label counter= new Label("");
   private Label winr= new Label("YOU WIN!!!!!!!!");
   private Label loozr= new Label("YOU LOSE!!!!");
   private AnimationHandler ah = new AnimationHandler();
   
   public void start(Stage stage)
   {
      //Create a root Flow Pane
      root.setMaxHeight(600);
      root.setMaxWidth(600);
      //make header and add to top of Border Pane
      header=new Label("The Text Field!");
      top.getChildren().add(header);
      
      
      //Add color
      top.setBackground(new Background(new BackgroundFill(Color.CYAN, CornerRadii.EMPTY, Insets.EMPTY)));
      fp.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
      root.setTop(top);
      root.setCenter(fp);
      
      //initialize variables
      for (int i=0; i<4; i++)
      {
         for (int j=0; j<4;j++)
         {
            topBArray[i][j]=new Button();
            bottomBArray[i][j]= new Button();
            leftBArray[i][j]= new Button();
            rightBArray[i][j]= new Button();
            vis[i][j] = true;
            topBArray[i][j].setPrefWidth(100);
            topBArray[i][j].setPrefHeight(100);
            gp[i][j] = new GamePane(topBArray[i][j],bottomBArray[i][j],leftBArray[i][j], rightBArray[i][j], i, j);
            tVis[i][j] = true;
            bVis[i][j] = true;
            lVis[i][j] = true;
            rVis[i][j] = true;
            vis[i][j] = true;
            
         }
      }
      //add GamePane/GridPane
      for (int i=0; i<4; i++)
      {
         hb1.getChildren().add(gp[i][0]);
         hb2.getChildren().add(gp[i][1]);
         hb3.getChildren().add(gp[i][2]);
         hb4.getChildren().add(gp[i][3]);
      }
      //add to fp
      fp.getChildren().add(hb1);
      fp.getChildren().add(hb2);
      fp.getChildren().add(hb3);
      fp.getChildren().add(hb4);
      
      //set gap
      int gap = 10;
      fp.setVgap(gap);
      fp.setHgap(gap);
      hb1.setSpacing(gap);
      hb2.setSpacing(gap);
      hb3.setSpacing(gap);
      hb4.setSpacing(gap);
      
      //set Allignment
      fp.setAlignment(Pos.CENTER);
      top.setAlignment(Pos.CENTER);
      
      //Falsify starting ball
      int startX=0;
      int startY=2;
      tVis[startX][startY] = false;
      bVis[startX][startY] = false;
      lVis[startX][startY] = false;
      rVis[startX][startY] = false;
      vis[startX][startY] = false;
      gp[startX][startY].setVisible(false);
      
      
      //Set up the start
      for (int i=0; i<4; i++)
      {
         for (int j=0; j<4;j++)
         {
            if(i<2)
            {
               rightBArray[i][j].setVisible(false);
               rVis[i][j] = false;
            }
            if (i>1)
            {
               leftBArray[i][j].setVisible(false);
               lVis[i][j] = false;
            }
            if (j<2)
            {
               bottomBArray[i][j].setVisible(false);
               bVis[i][j] = false;
            }
            if (j>1)
            {
               topBArray[i][j].setVisible(false);
               tVis[i][j] = false;
            }
            if (i-2<0 || vis[i-2][j])
            {
               rightBArray[i][j].setVisible(false);
               rVis[i][j] = false;
            }
            if (i+2>3 || vis[i+2][j])
            {
               leftBArray[i][j].setVisible(false);
               lVis[i][j] = false;
            }   
            if (j-2<0 || vis[i][j-2])
            {
               bottomBArray[i][j].setVisible(false);
               bVis[i][j] = false;
            }
            if (j+2>3 || vis[i][j+2])
            {
               topBArray[i][j].setVisible(false);
               tVis[i][j] = false;
            }           
            
         }
      } 
      for (int i=0; i<4; i++)
      {
         for (int j=0; j<4; j++)
         {
            if (tVis[i][j] ||bVis[i][j] ||lVis[i][j]||rVis[i][j])
            {
               moves++;
            }
            if (vis[i][j])
            {
               ballz++;
            }
         }
      }
      //Remove Header from part 1, add counter
      counter.setText("Balls Left: "+ballz+"    Moves Left: "+moves);
      top.getChildren().remove(header);
      top.getChildren().add(counter);
      
      
      

      


      
      


      //Set the scene
      Scene scene = new Scene(root, 600, 600);
      stage.setScene(scene);
      stage.setTitle("Ball Game");
      stage.show();
      
   }
 

     
     //set up a GamePane class that extends from gridpane
     
     public class GamePane extends GridPane // extends gridpane so we can use their class features
       {
           public GamePane(Button topB, Button bottomB, Button leftB, Button rightB, int x, int y)
           {
              super();
              topBArray[x][y]=topB;
              bottomBArray[x][y]=bottomB;
              leftBArray[x][y]=leftB;
              rightBArray[x][y]=rightB;
              
              
              //Set Button Sizes
              topBArray[x][y].setPrefSize(80,20);                   
              bottomBArray[x][y].setPrefSize(80,20);                  
              leftBArray[x][y].setPrefSize(20,80);  
              rightBArray[x][y].setPrefSize(20,80);  
                
              //Add Button Listener to buttons  
             topBArray[x][y].setOnAction(new ButtonListener()); 
             bottomBArray[x][y].setOnAction(new ButtonListener()); 
             leftBArray[x][y].setOnAction(new ButtonListener());                
             rightBArray[x][y].setOnAction(new ButtonListener());
                

              //Add Buttons to gridPane
              add(topBArray[x][y], 1, 0);
              add(bottomBArray[x][y], 1, 2);
              add(leftBArray[x][y], 0, 1); 
              add(rightBArray[x][y], 2, 1); 
   
              
              //Add Ballz
              Canvas can = new Canvas(80,80);
              GraphicsContext gc = can.getGraphicsContext2D();
              Random rand = new Random();
              gc.setFill(new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat(), 1));
              gc.fillOval(0,0,80,80);
              add(can, 1,1);
           }
        }
               


  public class ButtonListener implements EventHandler<ActionEvent>  
   {
      public void handle(ActionEvent e) 
      {
         //change visibility for each button pressed (invisible, invisible, visible)
         for (int i=0; i<4; i++)
         {
            for (int j=0; j<4; j++)
            {
               if (e.getSource().equals(topBArray[i][j]))
               {
                  if (j+2<3)
                  {
                     vis[i][j]=false;
                     vis[i][j+1]=false;
                     vis[i][j+2]=true;
                     
                  }
               }
               if (e.getSource().equals(bottomBArray[i][j]))
               {
                  if (j-2>-1)
                  {
                     vis[i][j]=false;
                     vis[i][j-1]=false;
                     vis[i][j-2]=true;
                  }
               }
               if (e.getSource().equals(leftBArray[i][j]))
               {
                  if(i+2<3)
                  {
                     vis[i][j]=false;
                     vis[i+1][j]=false;
                     vis[i+2][j]=true;
                  }
               }
               if (e.getSource().equals(rightBArray[i][j]))
               {
                  if(i-2>-1)
                  {
                     vis[i][j]=false;
                     vis[i-1][j]=false;
                     vis[i-2][j]= true;
                  }
               }

            }
         }
         
         
         //Reset Balls for next play
         for (int i=0; i<4; i++)
         {
            for (int j=0; j<4; j++)
            {
               gp[i][j].setVisible(vis[i][j]);
            }
         }
         
         //set new button values
         for (int i=0; i<4; i++)
         {
            for (int j=0; j<4; j++)
            {
               if (vis[i][j])
               {
                  if(i>1&&!vis[i-2][j])
                  {
                     rVis[i][j]=true;
                  }
                  if(i<2&&!vis[i+2][j])
                  {
                     lVis[i][j]=true;
                  }
                  if(j>1&&!vis[i][j-2])
                  {
                     bVis[i][j]=true;
                  }
                  if(j<2&&!vis[i][j+2])
                  {
                     tVis[i][j]=true;
                  }
                  //falsify bad moves
                  if(i>1&&!vis[i-1][j])
                  {
                     rVis[i][j]=false;
                  }
                  if(i<2&&!vis[i+1][j])
                  {
                     lVis[i][j]=false;
                  }
                  if(j>1&&!vis[i][j-1])
                  {
                     bVis[i][j]=false;
                  }
                  if(j<2&&!vis[i][j+1])
                  {
                     tVis[i][j]=false;
                  }
                  if(i>1&&vis[i-2][j])
                  {
                     rVis[i][j]=false;
                  }
                  if(i<2&&vis[i+2][j])
                  {
                     lVis[i][j]=false;
                  }
                  if(j>1&&vis[i][j-2])
                  {
                     bVis[i][j]=false;
                  }
                  if(j<2&&vis[i][j+2])
                  {
                     tVis[i][j]=false;
                  }
                  if(! vis[i][j])
                  {
                     tVis[i][j]=false;
                     bVis[i][j]=false;
                     lVis[i][j]=false;
                     rVis[i][j]=false;
                     
                  }
                  
               }
               
            }
         }
         
         //Ensure any invisible balls buttons are not adding to the count
         for (int i=0; i<4; i++)
         {
            for (int j=0; j<4; j++)
            {
               if(! vis[i][j])
               {
                  tVis[i][j]=false;
                  bVis[i][j]=false;
                  lVis[i][j]=false;
                  rVis[i][j]=false;
                  
               }
            }
         }
         
         //Set visibility for buttons
         for (int i=0; i<4; i++)
         {
            for (int j=0; j<4; j++)
            {
               topBArray[i][j].setVisible(tVis[i][j]);
               bottomBArray[i][j].setVisible(bVis[i][j]);
               leftBArray[i][j].setVisible(lVis[i][j]);
               rightBArray[i][j].setVisible(rVis[i][j]);
            }
         }
         
         // Update Counters
         ballz=0;
         moves=0;
         for (int i=0; i<4; i++)
         {
            for (int j=0; j<4; j++)
            {
               if (tVis[i][j])
               {
                  moves++;
               }
               if (bVis[i][j])
               {
                  moves++;
               }
               if (lVis[i][j])
               {
                  moves++;
               }
               if (rVis[i][j])
               {
                  moves++;
               }
               if (vis[i][j])
               {
                  ballz++;
               }
            }
         }
         counter.setText("Balls Left: "+ballz+"    Moves Left: "+moves);
         
         
         if(ballz>1&&moves==0)
         {
            fp.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
            top.getChildren().remove(counter);
            top.getChildren().add(loozr);

         }
         if (ballz==1)
         {
            top.getChildren().remove(counter);
            top.getChildren().add(winr);
            ah.start();
         }

         
         
         
         
         
         
      }
      
   }
   
   //Animations are for winners only nerd
   class AnimationHandler extends AnimationTimer
   {
      public void handle(long currentTimeInNanoSeconds) 
      {
         Random rand = new Random();
         fp.setBackground(new Background(new BackgroundFill((new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat(), 1)), CornerRadii.EMPTY, Insets.EMPTY)));

         
      }
      
   }
 
   
   
   //do the thang
   public static void main(String[] args)
   {
      launch(args);
   }
   
}












//love you :X