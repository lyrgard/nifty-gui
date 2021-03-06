package de.lessvoid.nifty.examples.slick2d.menu;

import de.lessvoid.nifty.examples.menu.MenuStartScreen;
import de.lessvoid.nifty.examples.slick2d.SlickExampleLoader;

/**
 * Demo class to execute the menu demonstration.
 *
 * @author Martin Karing &lt;nitram@illarion.org&gt;
 */
public class MenuDemoMain {
  /**
   * Execute the demonstration.
   *
   * @param args call arguments - have no effect
   */
  public static void main(final String[] args) {
    SlickExampleLoader.createGame(new SlickExampleLoader(new MenuStartScreen()));
  }
}
