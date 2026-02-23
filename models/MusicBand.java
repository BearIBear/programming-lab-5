package models;
import java.time.Instant;
import java.util.Date;

public class MusicBand {
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long numberOfParticipants; //Значение поля должно быть больше 0
    private long singlesCount; //Значение поля должно быть больше 0
    private String description; //Поле может быть null
    private MusicGenre genre; //Поле может быть null
    private Person frontMan; //Поле не может быть null
    private static long nextId = 1;
    
    public MusicBand(String name, Coordinates coordinates, long numberOfParticipants, long singlesCount,
            String description, MusicGenre genre, Person frontMan) {
        this.id = nextId++;
        if (name == null) {
            throw new RuntimeException("Значение аргумента name не может быть null"); // TODO: Заменить на Exception
        }
        if (name.isBlank()) {
            throw new RuntimeException("Значение аргумента name не может быть пустым");
        }
        this.name = name;
        if (coordinates == null) {
            throw new RuntimeException("Значение аргумента coordinates не может быть null");
        }
        this.coordinates = coordinates;
        this.creationDate = Date.from(Instant.now());
        if (numberOfParticipants < 1) {
            throw new RuntimeException("Значение аргумента numberOfParticipants должно быть больше 0");
        }
        this.numberOfParticipants = numberOfParticipants;
        if (singlesCount < 1) {
            throw new RuntimeException("Значение аргумента singlesCount должно быть больше 0");
        }
        this.singlesCount = singlesCount;
        this.description = description;
        this.genre = genre;
        if (frontMan == null) {
            throw new RuntimeException("Значение аргумента frontMan не может быть null");
        }
        this.frontMan = frontMan;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public java.util.Date getCreationDate() {
        return creationDate;
    }

    public long getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public long getSinglesCount() {
        return singlesCount;
    }

    public String getDescription() {
        return description;
    }

    public MusicGenre getGenre() {
        return genre;
    }

    public Person getFrontMan() {
        return frontMan;
    }
}