package Chess;

//Напишите класс Queen и класс King.
//
//        В этих классах:
//
//        Реализуйте конструктор, который будет принимать лишь цвет фигуры.
//        Реализуйте метод getColor() так, чтобы он возвращал цвет фигуры.
//        Реализуйте метод canMoveToPosition() так, чтобы фигуры не могли выйти за доску (доска в нашем случае — это
//        двумерный массив размером 8 на 8, напоминаем, что индексы начинаются с 0) и могли ходить так, как ходят
//        в шахматах (Королева ходит и по диагонали и по прямой, Король — в любое поле вокруг себя), также фигура
//        не может сходить в точку, в которой она сейчас находится. Если фигура может пройти от точки (line, column)
//        до точки (toLine, toColumn) по всем правилам (указанным выше), то функция вернет true, иначе — false.
//        Реализуйте метод getSymbol так, чтобы он возвращал строку — символ фигуры, для короля — K, для ферзя — Q.

public class Queen extends ChessPiece {
    public Queen(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public String getSymbol() {
        return "Q";
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
            if ((column == getMin(column, toColumn) && line == getMax(line, toLine)) ||
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
                // число колонок = числу линий пройденных, поэтому
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
        } else if (checkPos(line) && checkPos(column) && checkPos(toLine) && checkPos(toColumn)) { // ходит как ладья
            // если ходим по вертикали
            if (column == toColumn) {
                // перебираем все клетки пути
                for (int i = getMin(line, toLine); i < getMax(line, toLine); i++) {
                    // если занята клетка
                    if (chessBoard.board[i][column] != null) {
                        // если клетка занята собой
                        if (chessBoard.board[i][column] == this && i == getMax(line, toLine)) return false;
                            // если на клетке фигура твоего цвета
                        else if (chessBoard.board[i][column].getColor().equals(this.color) && i == toLine)
                            return false;
                            // если на клетке фигура иного цвета
                        else if (!chessBoard.board[i][column].getColor().equals(this.color) && i == toLine)
                            return true;
                        else if (i != toLine && i != line) return false;
                    }
                }
                // если на конечной клетке фигура
                if (chessBoard.board[toLine][toColumn] != null) {
                    // если на dest клетке фигура твоего цвета, точка не совпадает с начальной
                    if (chessBoard.board[toLine][toColumn].getColor().equals(this.color) &&
                            chessBoard.board[toLine][toColumn] != this)
                        return false;
                        // если на dest клетке фигура иного цвета, точка не совпадает с начальной
                    else return !chessBoard.board[toLine][toColumn].getColor().equals(this.color) &&
                            chessBoard.board[toLine][toColumn] != this;
                } else return true;

                // если ходим по горизонтали
            } else if (line == toLine) {
                // перебираем все клетки пути
                for (int i = getMin(toColumn, column); i < getMax(column, toColumn); i++) {
                    // если занята клетка
                    if (chessBoard.board[line][i] != null) {
                        // если клетка занята собой
                        if (chessBoard.board[line][i] == this && i == getMax(column, toColumn)) return false;
                            // если на клетке фигура твоего цвета
                        else if (chessBoard.board[line][i].getColor().equals(this.color) && i == toColumn)
                            return false;
                            // если на клетке фигура иного цвета
                        else if (!chessBoard.board[line][i].getColor().equals(this.color) && i == toColumn)
                            return true;
                    }
                }

                // если на конечной клетке фигура
                if (chessBoard.board[toLine][toColumn] != null) {
                    // если на dest клетке фигура твоего цвета, точка не совпадает с начальной
                    if (chessBoard.board[toLine][toColumn].getColor().equals(this.color) &&
                            chessBoard.board[toLine][toColumn] != this)
                        return false;
                        // если на dest клетке фигура иного цвета, точка не совпадает с начальной
                    else return (!chessBoard.board[toLine][toColumn].getColor().equals(this.color) &&
                            chessBoard.board[toLine][toColumn] != this);
                } else return true;
            } else return false;
        } else return false;
    }
}


