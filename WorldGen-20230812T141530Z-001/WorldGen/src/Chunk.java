public class Chunk {
    public Tile[] chunkArray;
    public Game game;
    private int chunkDimension = 24;

    Chunk(){
        this.chunkArray = new Tile[chunkDimension*chunkDimension];
        //bitpack the cords

        for (int t = 0; t<chunkArray.length; t++){
            chunkArray[t] = new Tile("string");        }
    }

    public void setTile(int xCor , int yCor){
        chunkArray[xCor+(yCor*chunkDimension)] = new Tile("grass");
    }
}
