package Chess;

//Аналогично предыдущим фигурам создайте класс Rook (ладья).
//
//        В классе Rook:
//
//        Реализуйте метод getColor() так, чтобы он возвращал цвет фигуры.
//        Реализуйте метод canMoveToPosition() так, чтобы ладья не могла выйти за доску (доска в нашем случае —
//        это двумерный массив размером 8 на 8, напоминаем, что индексы начинаются с 0) и мог ходить только по прямой,
//        также фигура не может сходить в точку, в которой она сейчас находится. Если ладья может пройти от точки
//        (line, column) до точки (toLine, toColumn) по всем правилам (указанным выше), то функция вернет true,
//        иначе — false.
//        Реализуйте метод getSymbol так, чтобы он возвращал символ фигуры, в нашем случае ладья — R.

public class Rook extends ChessPiece {
    public Rook(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // проверяем: все ли координаты сущ-т (проверка от 0 до 7)
        if (checkPos(line) && checkPos(column) && checkPos(toLine) && checkPos(toColumn)) {

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

    @Override
    public String getSymbol() {
        return "R";
    }
}
