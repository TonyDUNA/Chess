package Chess;

//Напишите класс Bishop (слон). Этот класс должен быть наследником от класса ChessPiece, который вы сделали в предыдущей задаче.
//
//        В классе Bishop:
//
//        Реализуйте метод getColor() так, чтобы он возвращал цвет фигуры.
//        Реализуйте метод canMoveToPosition() так, чтобы слон не мог выйти за доску (доска в нашем случае —
//        это двумерный массив размером 8 на 8, напоминаем, что индексы начинаются с 0) и мог ходить только по диагонали,
//        также фигура не может сходить в точку, в которой она сейчас находится. Если слон может пройти от точки
//        (line, column) до точки (toLine, toColumn) по всем правилам (указанным выше), то функция вернет true, иначе — false.
//        Реализуйте метод getSymbol так, чтобы он возвращал символ фигуры, в нашем случае слон —  B.

public class Bishop extends ChessPiece {
    public Bishop(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }


    @Override
    public String getSymbol() {
        return "B";
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // проверяем: все ли координаты сущ-т (проверка от 0 до 7)
        if (checkPos(line) && checkPos(column) && checkPos(toLine) && checkPos(toColumn) &&

                // стартовая координата не равна конечной
                (line != toLine && column != toColumn &&

                        // конечная клетка пустая или цвет фигуры в конечной клетке не равен текущему
                        (chessBoard.board[toLine][toColumn] == null ||
                                !chessBoard.board[toLine][toColumn].color.equals(this.color)) &&

                        // стартовая клетка не пустая
                        chessBoard.board[line][column] != null) &&

                // ходит по диагонали
                getMax(line, toLine) - getMin(line, toLine) == getMax(column, toColumn) - getMin(column, toColumn)) {
            // диагональ лево верх -> право низ
            if (
                    (column == getMin(column, toColumn) && line == getMax(line, toLine)) ||
                            (toColumn == getMin(column, toColumn) && line == getMax(line, toLine))) {
                // вводим переменные с макс/мин значениями
                int fromL = getMax(line, toLine);
                int fromC = getMin(column, toColumn);
                int toL = getMin(line, toLine);
                int toC = getMax(column, toColumn);
                // позицци по пути записываем -> в массив [max-min column] [toColumn]:
                int[][] positions = new int[toC - fromC][1];
                // число колонок = числу линий пройденных слоном, поэтому
                for (int i = 1; i < toC - fromC; i++) {
                    if (chessBoard.board[fromL - i][fromC + i] == null) {
                        // записываем в массив координаты пройденной точки,начиная с нулевого индекса
                        positions[i - 1] = new int[]{fromL - i, fromC + i};
                    }
                    // если фигура в точке назначения имеет иной цвет
                    else if (!chessBoard.board[fromL - i][fromC + i].color.equals(this.color) && fromL - i == toLine) {
                        positions[i - 1] = new int[]{fromL - i, fromC + i};
                    } else {
                        return false;
                    }
                }
                return true;
            } else { // диагональ  лево низ -> право верх
                // вводим переменные с макс/мин значениями
                int fromL = getMin(line, toLine);
                int fromC = getMin(column, toColumn);
                int toL = getMax(line, toLine);
                int toC = getMax(column, toColumn);
                // позицци по пути записываем -> в массив [max-min column] [toColumn]:
                int[][] positions = new int[toC - fromC][1];
                // число колонок = числу линий пройденных слоном, поэтому
                for (int i = 1; i < toC - fromC; i++) {
                    if (chessBoard.board[fromL + i][fromC + i] == null) {
                        // записываем в массив координаты пройденной точки,начиная с нулевого индекса
                        positions[i - 1] = new int[]{fromL + i, fromC + i};
                    }
                    // если фигура в точке назначения имеет иной цвет
                    else if (!chessBoard.board[fromL + i][fromC + i].color.equals(this.color) && fromL + i == toLine) {
                        positions[i - 1] = new int[]{fromL + i, fromC + i};
                    } else {
                        return false;
                    }
                }
                return true;
            }
        } else return false;
    }
}

