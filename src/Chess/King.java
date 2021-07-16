package Chess;

public class King extends ChessPiece {
    public King(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public String getSymbol() {
        return "K";
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // проверяем: все ли координаты сущ-т (проверка от 0 до 7)
        if (checkPos(line) && checkPos(column) && checkPos(toLine) && checkPos(toColumn)) {
            // если ход более чем одна клетка
            if (Math.abs(line - toLine) > 1 || Math.abs(column - toColumn) > 1) return false;
            // если король под атакой
            if (isUnderAttack(chessBoard, toLine, toColumn)) return false;
            //если клетка не пустая -> true при наличии фигуры иного цвета
            if (chessBoard.board[toLine][toColumn] != null) {
                return !chessBoard.board[toLine][toColumn].getColor().equals(this.color);
            }

            return true;
        } else return false;
    }

    public boolean isUnderAttack(ChessBoard chessBoard, int line, int column) {
        // проход по всем клеткам доски
        if (checkPos(line) && checkPos(column)) {
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 7; j++) {
                    if (chessBoard.board[i][j] != null) {
                        if (!chessBoard.board[i][j].getColor().equals(this.color) &&
                                chessBoard.board[i][j].canMoveToPosition(chessBoard, i, j, line, column)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        } else return false;

    }


}
