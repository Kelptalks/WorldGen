import java.util.HashMap;
public class World {
    private Game game;

    public HashMap<Integer, Chunk> hashMap = new HashMap<>();
    World(){
    }

    public void generateChunk(int packedCords){
        this.hashMap.put(packedCords, new Chunk());
    }
    
}
