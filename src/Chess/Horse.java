package Chess;

//В классе Horse:
//
//        Реализуйте конструктор, который будет принимать лишь цвет фигуры.
//        Реализуйте метод getColor() так, чтобы он возвращал цвет фигуры.
//        Реализуйте метод canMoveToPosition() так, чтобы конь не мог выйти за доску (доска в нашем случае —
//        это двумерный массив размером 8 на 8, напоминаем, что индексы начинаются с 0) и мог ходить только буквой «Г».
//        Также фигура не может сходить в точку, в которой она сейчас находится.
//        Если конь может пройти от точки (line, column) до точки (toLine, toColumn) по всем правилам (указанным выше),
//        то функция вернет true, иначе — false.
//        Реализуйте метод getSymbol так, чтобы он возвращал символ фигуры, в нашем случае конь — это  H.
//        Также вы можете добавить и свои методы для удобства.

public class Horse extends ChessPiece {
    public Horse(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public String getSymbol() {
        return "H";
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // проверили все ли координаты сущ-т (проверка от 0 до 7)
        if (checkPos(line) && checkPos(column) && checkPos(toLine) && checkPos(toColumn)) {

            // стартовая координата не равна конечной
            if (line != toLine && column != toColumn &&

                    // конечная клетка пустая или цвет фигуры в конечной клетке не равен текущему
                    (chessBoard.board[toLine][toColumn] == null ||
                            !chessBoard.board[toLine][toColumn].color.equals(this.color)) &&

                    // стартовая клетка не пустая
                    chessBoard.board[line][column] != null) {

                // возможные конечные позиции коня в виде массива значений
                int[][] positions = new int[][]{
                        {line - 2, column - 1}, {line - 2, column + 1},
                        {line + 2, column - 1}, {line + 2, column + 1},
                        {line - 1, column - 2}, {line - 1, column + 2},
                        {line + 1, column - 2}, {line + 1, column + 2}
                };

                // перебираем массив, проверяем соответствуют ли конечные координаты позиции фигуры
                // значениям массива positions {line, column}
                for (int i = 0; i < positions.length; i++) {
                    if (positions[i][0] == toLine && positions[i][1] == toColumn)
                        return true;
                }
            }
        } else return false;
        return false;
    }

}
