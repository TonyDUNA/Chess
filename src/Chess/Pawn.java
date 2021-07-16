package Chess;

//Создайте класс Pawn (пешка), который так же, как и Horse, должен быть наследником класса ChessPiece.
//
//        Реализуйте в классе Pawn, всё тоже самое, что и в классе Horse.
//
//        В классе Pawn:
//
//        Реализуйте конструктор, который будет принимать цвет фигуры.
//        Реализуйте метод getColor() так, чтобы он возвращал цвет фигуры.
//        Реализуйте метод canMoveToPosition() так, чтобы пешка не могла выйти за доску и могла ходить только вперед.
//        Помните, что первый ход пешка может сдвинуться на 2 поля вперед, сделать это можно, например,
//        сравнив координаты. То есть, если пешка белая (color == "White") и находится в line == 1, то она
//        может пойти на 2 поля вперед, иначе — нет, аналогично с черными пешками. Также фигура не может сходить в точку,
//        в которой она сейчас находится. Если пешка может пройти от точки (line, column) до точки (toLine, toColumn)
//        по всем правилам (указанным выше), то функция вернет true, иначе — false.
//        Реализуйте метод getSymbol так, чтобы он возвращал символ фигуры, в нашем случае пешка — это P.

public class Pawn extends ChessPiece {
    public Pawn(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public String getSymbol() {
        return "P";
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // проверили все ли координаты сущ-т (проверка от 0 до 7)
        if ((checkPos(line) && checkPos(column) && checkPos(toLine) && checkPos(toColumn)) &&

                // стартовая клетка не пустая
                chessBoard.board[line][column] != null) {

            // если ходим прямо (колонна не меняется)
            if (column == toColumn) {

                int dir;
                int start;

                if (color.equals("White")) {
                    // для белых фигур - направление хода и стартовая линия
                    dir = 1;
                    start = 1;
                } else {
                    // для черных фигур - направление хода и стартовая линия
                    dir = -1;
                    start = 6;
                }
                // проверяем, dest клетка свободна? если - да - true
                if (line + dir == toLine) {
                    return chessBoard.board[toLine][toColumn] == null;
                }

                // если ходим со стартовой линии на 2 клетки
                if (line == start && line + 2 * dir == toLine) {
                    // если две клетки впереди свободны
                    return chessBoard.board[toLine][toColumn] == null &&
                            chessBoard.board[toLine + dir][toColumn] == null;
                }

            } else { // в случае взятия пешкой фигуры - ход по диагонали
                // если сдвиг и в линии и в колонне
                if ((column - toColumn == 1 || column - toColumn == -1) &&
                        (line - toLine == 1 || line - toLine == -1) &&

                        // фигура есть для взятия
                        chessBoard.board[toLine][toColumn] != null) {

                    // цвет фигуры для взятия - противоположен текущей
                    return !chessBoard.board[toLine][toColumn].getColor().equals(color);
                } else return false;
            }
        }
        return false;
    }
}

