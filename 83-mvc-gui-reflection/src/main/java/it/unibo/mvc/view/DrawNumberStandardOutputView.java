package it.unibo.mvc.view;

//import javax.swing.JOptionPane;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.api.DrawResult;

/**
 * Graphical {@link DrawNumberView} implementation (terminal output version).
 */
public final class DrawNumberStandardOutputView implements DrawNumberView {

    private static final String NEW_GAME = ": a new game starts!";

    @Override
    public void setController(final DrawNumberController observer) { }

    @Override
    public void start() { }

    @Override
    public void result(final DrawResult res) {
        switch (res) {
            case YOURS_HIGH, YOURS_LOW -> {
                System.out.println(res.getDescription()); // NOPMD
            }
            default -> System.out.println(res.getDescription() + NEW_GAME); // NOPMD
        }
    }
}
