package Game2048;

import com.googlecode.lanterna.input.KeyStroke;
import java.io.IOException;

public class TerminalGameView implements GameView {
    private final GameEngine gameEngine;
    private final TerminalBackend backend;

    public TerminalGameView(GameEngine gameEngine, TerminalBackend backend) {
        this.gameEngine = gameEngine;
        this.backend = backend;
    }

    private void printScore() throws IOException {
        backend.printString("      Score: ", Color.GREY);
        backend.printLine(String.valueOf(gameEngine.getScore()), Color.CYAN);
    }

    private void printGrid() throws IOException {
        backend.printLine(" -------------------");
        for (Tile[] row : gameEngine.grid) {
            backend.printCharacter('|');
            for (Tile tile : row) {
                backend.printString(tile.toString(), Color.CYAN);
                backend.printCharacter('|');
            }
            backend.printNewLine();
            backend.printLine(" -------------------");
        }
    }

    @Override
    public void printGameOverMessage() {
        try {
            backend.printLine("      Game Over!", Color.RED);
            backend.flushChanges();
        } catch (IOException e) {
           throw new RuntimeException(e);
        }
    }

    public void updateDisplay() {
        try {
            backend.resetCursorPosition();
            printScore();
            printGrid();
            backend.flushChanges();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
