package entity;

public class Post implements EntityInterface {
    private int id;
    private String name;
    private String text;
    private int ownerId;
    private User owner;
    // данные записи...

    public Post() {}

    public Post(String name, String text, int ownerId, User owner) {
        this.name = name;
        this.text = text;
        this.ownerId = ownerId;
        this.owner = owner;
    }

    public Post(int id, String name, String text, int ownerId, User owner) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.ownerId = ownerId;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
