package managers;

import models.MusicBand;
import java.util.PriorityQueue;
import java.time.LocalDateTime;

public class CollectionManager {
    private PriorityQueue<MusicBand> collection;
    private LocalDateTime initTime;

    public CollectionManager() {
        this.collection = new PriorityQueue<>();
        this.initTime = LocalDateTime.now(); 
    }

    public PriorityQueue<MusicBand> getCollection() {
        return collection;
    }

    public LocalDateTime getInitTime() {
        return initTime;
    }    

    public void addElement(MusicBand band) {
        collection.add(band);
    }

    public void clearCollection() {
        collection.clear();
    }
}