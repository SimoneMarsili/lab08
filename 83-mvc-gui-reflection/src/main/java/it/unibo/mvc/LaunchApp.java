package it.unibo.mvc;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.controller.DrawNumberControllerImpl;
import it.unibo.mvc.model.DrawNumberImpl;
//import it.unibo.mvc.view.DrawNumberStandardOutputView;
//import it.unibo.mvc.view.DrawNumberSwingView;

/**
 * Application entry-point.
 */
public final class LaunchApp {

    private LaunchApp() { }

    /**
     * Runs the application.
     *
     * @param args ignored
     * @throws ClassNotFoundException if the fetches class does not exist
     * @throws NoSuchMethodException if the 0-ary constructor do not exist
     * @throws InvocationTargetException if the constructor throws exceptions
     * @throws InstantiationException if the constructor throws exceptions
     * @throws IllegalAccessException in case of reflection issues
     * @throws IllegalArgumentException in case of reflection issues
     */
    public static void main(final String... args) {
        final var model = new DrawNumberImpl();
        final DrawNumberController app = new DrawNumberControllerImpl(model);
        /*
        app.addView(new DrawNumberSwingView());
        app.addView(new DrawNumberSwingView());
        app.addView(new DrawNumberStandardOutputView());
        */
        final String[] guiClasses = {"it.unibo.mvc.view.DrawNumberStandardOutputView", "it.unibo.mvc.view.DrawNumberSwingView"};
        for (final String className : guiClasses) {
            try {
                final Class<?> c = Class.forName(className);
                final Constructor<?> constructor = c.getConstructor();
                for (int i = 0; i < 3; i++) {
                    app.addView((DrawNumberView) constructor.newInstance());
                }
            } catch (final ClassNotFoundException e) {
                System.out.println("Classe non trovata: " + e.getMessage()); // NOPMD
            } catch (final ReflectiveOperationException e) {
                System.out.println(e.getMessage()); // NOPMD
            } 
        }
    }
}
