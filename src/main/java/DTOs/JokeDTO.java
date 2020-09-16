package DTOs;

import entities.Joke;

public class JokeDTO {

    private long id;
    private String joke;
    private String reference;
    private String type;

    public JokeDTO(Joke jokes) {
        this.id = jokes.getId();
        this.joke = jokes.getJoke();
        this.reference = jokes.getReference();
        this.type = jokes.getType();
    }
}
