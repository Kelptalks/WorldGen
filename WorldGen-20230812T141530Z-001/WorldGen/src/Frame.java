import java.awt.Canvas;
import javax.swing.JFrame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Frame extends JFrame{
    private Game game;
    public Canvas mainCanvas;
    Frame(Game game){
        this.game = game;

        //initilise the size of screen.
        System.out.println(game.frameWidth + " | " + game.frameHeight);
        super.setVisible(true);
        super.setSize(game.frameWidth, game.frameHeight);

        //adds a canvas component to the class.
        MainCanvas mainCanvas = new MainCanvas(game);
        this.mainCanvas = mainCanvas;
        super.getContentPane().add(mainCanvas);

        super.validate();

        //Allows the frame to be closed     
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();

    }

    public void drawPixel(int x, int y){
        ((MainCanvas) mainCanvas).drawPixel(x, y);
    }

    public void drawTile(Tile tile, int x, int y){
        ((MainCanvas) mainCanvas).drawTile(tile, x, y);
    }

    public void drawChunk(Chunk chunk, int xCor, int yCor){
        int x = 0;
        int y = 0;
        for (int t = 0; t<chunk.chunkArray.length; t++){
            drawTile(chunk.chunkArray[t], x+xCor, y+yCor);

            x+=game.tileDimension;
            if (x == game.chunkDimension*game.tileDimension){
                    x = 0;
                    y+=game.tileDimension;
        }
        }
    }
    
    public void clear(){
        ((MainCanvas) mainCanvas).clear();
    }

    public void refresh(){
        ((MainCanvas) mainCanvas).refresh();;
    }
}
