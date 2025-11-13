package it.unibo.mvc.view;

//import javax.swing.JOptionPane;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.api.DrawResult;

public class DrawNumberStandardOutputView implements DrawNumberView {

    private static final String NEW_GAME = ": a new game starts!";

    @Override
    public void setController(DrawNumberController observer) {}

    @Override
    public void start() {}

    @Override
    public void result(DrawResult res) {
        switch (res) {
            case YOURS_HIGH, YOURS_LOW -> {
                System.out.println(res.getDescription());
                //return;
            }
            case YOU_WON -> System.out.println(res.getDescription() + NEW_GAME);
            case YOU_LOST -> System.out.println(res.getDescription() + NEW_GAME);
        }
    }

}