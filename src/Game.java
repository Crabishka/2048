import java.util.Random;

public class Game {

    private final Random rnd = new Random();
    public int[][] field = null;   // сделано public, чтобы проще было писать тест
    private int colorCount = 0;


    public Game() {
    }

    public void newGame(int rowCount, int colCount, int colorCount) {
        // создаем поле
        field = new int[rowCount][colCount];
        this.colorCount = colorCount;
    }

    public enum Direction {
        RIGHT,
        LEFT,
        UP,
        DOWN
    }

    // каждая функция меняет поле в зависимости от нажатой клавиши
// 1. Передвигает все клеточки
// 2. Проверяет возможность добавления новой клеточки
// если да - добавляет
// если нет - игра проиграна




    public boolean doShift(Direction direction) {
        AccessThroughRotatedField absField = new AccessThroughRotatedField(field, direction);
        boolean wasAnyMoving = false;
        for (int j = 0; j < getColCount(); j++) {
            for (int i = 0; i < getRowCount(); i++)
            {
                for (int k = i + 1; k < getRowCount(); k++) {
                    if (absField.getCell(k,j) != 0)
                    {
                        if (absField.getCell(i,j) == 0)
                        {
                            absField.setCell(i,j,absField.getCell(k,j));
                            absField.setCell(k,j,0);
                            wasAnyMoving = true;
                        } else {
                            if (absField.getCell(i,j) == absField.getCell(k,j)) {
                                absField.setCell(i,j,absField.getCell(k,j)*2);
                                absField.setCell(k,j,0);
                                wasAnyMoving = true;
                            }
                            break;
                        }
                    }
                }
            }
        }
        return wasAnyMoving;
    }



    public boolean addRandomCell() { // возвращает false, если нельзя добавить
        int countOfZeroElement = 0;
        for (int i = 0; i < getRowCount(); i++) {
            for (int j = 0; j < getColCount(); j++) {
                if (field[i][j] == 0) countOfZeroElement++;
            }
        }
        if (countOfZeroElement == 0) return false;
        int index = rnd.nextInt(countOfZeroElement);
        for (int i = 0; i < getRowCount(); i++) {
            for (int j = 0; j < getColCount(); j++) {
                if (field[i][j] == 0) {
                    if (index == 0) {
                        field[i][j] = 2;
                        return true;
                    } else index--;
                }

            }
        }
        return true;

    }

    public boolean isAnyTurnAvailable(int[][] field){   // я не придумал ничего лучше
        for (int i = 0; i < getRowCount(); i++)
            for (int j = 0; j < getColCount(); j++){
                if (i - 1 < 0) {} // сверху
                    else{
                        if (field[i][j] == field[i-1][j] || field[i-1][j] == 0) return true;
                }
                if (j - 1 < 0) {}  //  слева
                    else{
                        if (field[i][j] == field[i][j-1] || field[i][j-1] == 0) return true;
                }
                if (j + 1 > getColCount() - 1) {}   // справа
                else{
                    if (field[i][j] == field[i][j+1] || field[i][j+1] == 0) return true;
                }
                if (i + 1 > getRowCount() - 1) {} // сниху
                else{
                    if (field[i+1][j] == field[i][j] || field[i+1][j] == 0) return true;
                }
            }
        return false;
    }


    public int getRowCount() {
        return field == null ? 0 : field.length;
    }

    public int getColCount() {
        return field == null ? 0 : field[0].length;
    }

    public int getColorCount() {
        return colorCount;
    }

    public int getCell(int row, int col) {
        return (row < 0 || row >= getRowCount() || col < 0 || col >= getColCount()) ? 0 : field[row][col];
    }


}
