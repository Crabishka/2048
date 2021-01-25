public class AccessThroughRotatedField {

    public int[][] field = null;
    Game.Direction direction = null;
    int colCount = 0;
    int rowCount = 0;

    public AccessThroughRotatedField(int[][] field, Game.Direction direction) {
        this.field = field;
        this.direction = direction;
        colCount = this.field[0].length; // у нас квадратное поле, но вдруг мы захотим его сделать прямоугольным
        rowCount = this.field.length;
    }

    public void setCell(int y, int x, int value) {  // сначала y, потому что он отвечает за ряды
        if (direction == Game.Direction.UP) {
            field[y][colCount - 1 - x] = value;
        } else if (direction == Game.Direction.DOWN) {
            field[colCount - 1 - y][x] = value;
        } else if (direction == Game.Direction.LEFT) {
            field[x][y] = value;
        } else if (direction == Game.Direction.RIGHT) {
            field[colCount - 1 - x][colCount - 1 - y] = value;

        }

    }

    public int getCell(int y, int x) {
        if (this.direction == Game.Direction.UP) {
            return field[y][colCount - 1 - x];
        } else if (direction == Game.Direction.DOWN) {
            return field[colCount - 1 - y][x];
        } else if (direction == Game.Direction.LEFT) {
            return field[x][y];
        } else if (direction == Game.Direction.RIGHT) {
            return field[colCount - 1 - x][colCount - 1 - y];

        }
        return -1;
    }
}
