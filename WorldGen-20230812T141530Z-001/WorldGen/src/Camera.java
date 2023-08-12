public class Camera {

    private Game game;
    private Frame frame;
    private World world;
    private int chunkPixelDimension;

    Camera(Game game, Frame frame, World world){
        this.game = game;
        this.frame = frame;
        this.world = world;
        this.chunkPixelDimension = (game.tileDimension*game.chunkDimension);
    }

    public void moveCam(char direction){
        if (direction == 'l'){
            game.xOffset-=24;
        }

        if (direction == 'r'){
            game.xOffset+=24;
        }
        if (direction == 'u'){
            game.yOffset-=24;
        }

        if (direction == 'd'){
            game.yOffset+=5;
        }
    }
    
    public void renderFrame(){
        int chunkToLoad = (game.xOffset/(game.tileDimension*game.chunkDimension));
        int relativeXCor = -1;

        for (int xCor = chunkToLoad-2; xCor < chunkToLoad+2; xCor++){
            relativeXCor++;
            int relativeYCor = -1;

            for (int yCor = chunkToLoad-1; yCor<chunkToLoad+2; yCor++){
                long T1 = System.nanoTime();
                relativeYCor++;

                int packedCords = 0;

                if (xCor<0){
                    packedCords  |= 1 & 0b0000;
                }
                if (yCor<0){
                    packedCords  |= 1 & 0b0000 << 16;
                }

                packedCords |= (xCor & 0xFFFF) << 1;
                packedCords |= (yCor & 0xFFFF) << 17;

                Chunk chunkToDraw = world.hashMap.get(packedCords);

                if (chunkToDraw == null){
                    world.generateChunk(packedCords);
                    chunkToDraw = world.hashMap.get(packedCords);
                }
                long T2 = System.nanoTime();
                System.out.println("time 1 : " + (T2-T1)/1000);

                T1 = System.nanoTime();
                frame.drawChunk(chunkToDraw, (relativeXCor*chunkPixelDimension)+game.xOffset%(chunkPixelDimension), (relativeYCor*chunkPixelDimension)+game.yOffset%(chunkPixelDimension));
                T2 = System.nanoTime();
                System.out.println("time 2 : " + (T2-T1)/1000000);
            }
        }
    }
}
