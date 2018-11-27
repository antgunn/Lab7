public class Player {

    private String name;
    private int chips;
    private int id;

    public Player(String name, int id) {
        this.name = name;
        chips = 3;
        this.id = id;
    }

    public String getName() {
        return name;
    }


    public int getChips() {
        return chips;
    }

    public void setChips(int chips) {
        this.chips = chips;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name  + " (" + chips + " chips) ";
    }
}
