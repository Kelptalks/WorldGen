import java.util.concurrent.*;
public class App {

    //can you change the thickness of a line(for spider legs)
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        Game game = new Game();

        Frame frame = new Frame(game);
        World world = new World();
        Camera camera = new Camera(game, frame, world);
        Chunk chunk = new Chunk();

        

        int x = 0;
        while (true){
            long T1 = System.nanoTime();
            int  test = 24*16;
            camera.renderFrame();
            camera.moveCam('d');
            camera.moveCam('d');
            camera.moveCam('d');

            frame.refresh();
            
            long T2 =  System.nanoTime();

            System.out.println("time : " + (T2-T1)/1000000);
        }


        
    }
}
