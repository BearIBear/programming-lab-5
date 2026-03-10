package models;
/**
* Возможные музыкальные жанры
*/
public enum MusicGenre {
    PSYCHEDELIC_ROCK("PSYCHEDELIC_ROCK"),
    POP("POP"),
    MATH_ROCK("MATH_ROCK"),
    PUNK_ROCK("PUNK_ROCK");

    private final String name;


    private MusicGenre(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}