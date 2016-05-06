package chess.objects;

public class Piece implements java.io.Serializable {


    private int color;

    private String name;

    private String icon;

    private boolean hasMoved;

    private int score;

    public Piece(int color, String name, String icon, boolean hasMoved, int score) {
        this.color = color;
        this.name = name;
        this.icon = icon;
        this.hasMoved = hasMoved;
        this.score = score;
    }

    public Piece() {
        color = -1;
        name = "";
        icon = "";
        hasMoved = false;
        score = -1;
    }


    public String getIcon() {
        return icon;
    }

    public int getColor() {
        return color;
    }

    public String getName() {
        return name;
    }
}
