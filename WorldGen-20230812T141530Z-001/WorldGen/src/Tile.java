import java.util.LinkedList;
public class Tile {
    public LinkedList<Integer> PackedTileLinkList = new LinkedList<>();
    private int tileDimension;

    Tile(String tileType){
        this.tileDimension = 24;
        if (tileType == "grass"){
            grass();
        }
        else{
            water();
        }
    }

    private void grass(){ 
        int pixelArray[][] = new int[24][24];
        for (int x = 0; x < tileDimension; x++){
            for (int y = 0; y < tileDimension; y++){
                if (Math.random() < 0.5){
                    pixelArray[x][y] = 4;
                }
                else{
                    pixelArray[x][y] = 12;
                }
            }
        }
        
        for (int x = 0; x < tileDimension; x++) {
            int bitInIntCount = 0;
            int tempPackingInt = 0;

            for (int y = 0; y < tileDimension; y++) {
                tempPackingInt |= (pixelArray[x][y] & 0xFF) << (8 * bitInIntCount);
                bitInIntCount++;

                if (bitInIntCount == 4) {
                    PackedTileLinkList.add(tempPackingInt);
                    bitInIntCount = 0;
                    tempPackingInt = 0;
                }
            }
        }
    }

    private void water(){ 
        int pixelArray[][] = new int[24][24];
        for (int x = 0; x < tileDimension; x++){
            for (int y = 0; y < tileDimension; y++){
                if (Math.random() < 0.5){
                    pixelArray[x][y] = 48;
                }
                else{
                    pixelArray[x][y] = 16;
                }
            }
        }
        
        for (int x = 0; x < tileDimension; x++) {
            int bitInIntCount = 0;
            int tempPackingInt = 0;

            for (int y = 0; y < tileDimension; y++) {
                tempPackingInt |= (pixelArray[x][y] & 0xFF) << (8 * bitInIntCount);
                bitInIntCount++;

                if (bitInIntCount == 4) {
                    PackedTileLinkList.add(tempPackingInt);
                    bitInIntCount = 0;
                    tempPackingInt = 0;
                }
            }
        }
    }

}
