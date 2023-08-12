import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class MainCanvas extends Canvas{
    private Game game;
    private BufferedImage bufferedImage;
    private BufferedImage offScreenBuffer;

    MainCanvas(Game game){
        this.game = game;
        System.out.println(game.frameWidth + " | " + game.frameHeight);
        bufferedImage = new BufferedImage(game.frameWidth, game.frameHeight, BufferedImage.TYPE_INT_ARGB);
        offScreenBuffer = new BufferedImage(game.frameWidth, game.frameHeight, BufferedImage.TYPE_INT_ARGB);

    }

    //updates a single pixel
    public void drawPixel(int xCor,int yCor){
        Graphics g2d = bufferedImage.createGraphics();
        g2d.setColor(Color.RED);
        g2d.fillRect(xCor, yCor, 1, 1);
        g2d.dispose();
    }

    //draws multible pixels simotainiusly using the same graphics object
    public void drawTile(Tile tile, int xCor, int yCor){
        //Create graphics object
        Graphics g2d = bufferedImage.createGraphics();

        int bitesPerInt = 4;
        int x = 0;
        int y = 0;
        
        for (Integer item : tile.PackedTileLinkList) {
            //Extract color value of pixel

            for (int t = 0; t<bitesPerInt; t++){
                int colorBite = (item >> 8*t) & 0xFF;

                //split color value into RGB channels and multibly by 63 to correct for limmited values
                int red = ((colorBite >> 0) & 0b00000011) *85;
                int green = ((colorBite >> 2) & 0b00000011) *85;
                int blue = ((colorBite >> 4) & 0b00000011) *85;
                
                //System.out.println("red : " + red);
                //System.out.println("Blue : " + blue);
                //System.out.println("red : " + green);

                Color color = new Color(red, green, blue);

                if (xCor+x <game.frameHeight || yCor+y < game.frameWidth){
                    g2d.setColor(color);
                    g2d.fillRect(xCor+x, yCor+y, 1, 1);
                }

                //Manage the tile size
                x++;
                if (x == game.tileDimension){
                    x=0;
                    y++;
                }
            }
        }
        g2d.dispose();
    }

    public void refresh(){
        paint(getGraphics());
    }

    public void clear(){
        Graphics g2d = bufferedImage.createGraphics();
        g2d.setColor(Color.RED);
        g2d.fillRect(0, 0, game.frameWidth, game.frameHeight);
        g2d.dispose();
    }
    
    //repaints the buffered immage
    @Override
    public void paint(Graphics g) {
        g.drawImage(bufferedImage, 0, 0, this);
    }

}
