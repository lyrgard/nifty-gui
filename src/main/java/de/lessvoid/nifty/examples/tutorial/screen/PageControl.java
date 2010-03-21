package de.lessvoid.nifty.examples.tutorial.screen;

import java.util.Properties;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.Controller;
import de.lessvoid.nifty.controls.FocusHandler;
import de.lessvoid.nifty.controls.dropdown.controller.DropDownControl;
import de.lessvoid.nifty.elements.ControllerEventListener;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.xml.xpp3.Attributes;

public class PageControl implements Controller {
  private Element element;
  private FocusHandler focusHandler;
  private Screen screen;

  public void bind(
      final Nifty nifty,
      final Screen screenParam,
      final Element newElement,
      final Properties parameter,
      final ControllerEventListener listener,
      final Attributes controlDefinitionAttributes) {
    element = newElement;
    screen = screenParam;
    screen.findControl("dropDownControl", DropDownControl.class).addItem("a");
    screen.findControl("dropDownControl", DropDownControl.class).addItem("b");
    screen.findControl("dropDownControl", DropDownControl.class).addItem("c");
  }

  public void onStartScreen() {
    focusHandler = screen.getFocusHandler();
  }

  public void onFocus(final boolean getFocus) {
  }

  public void inputEvent(final NiftyInputEvent inputEvent) {
    if (inputEvent == NiftyInputEvent.NextInputElement) {
      focusHandler.getNext(element).setFocus();
    } else if (inputEvent == NiftyInputEvent.PrevInputElement) {
      focusHandler.getPrev(element).setFocus();
    } else if (inputEvent == NiftyInputEvent.Activate) {
      element.onClick();
    } else if (inputEvent == NiftyInputEvent.MoveCursorDown) {
      if (focusHandler != null) {
        Element nextElement = focusHandler.getNext(element);
        if (nextElement.getParent().equals(element.getParent())) {
          nextElement.setFocus();
        }
      }
    } else if (inputEvent == NiftyInputEvent.MoveCursorUp) {
      if (focusHandler != null) {
        Element prevElement = focusHandler.getPrev(element);
        if (prevElement.getParent().equals(element.getParent())) {
          prevElement.setFocus();
        }
      }
    }
  }
}
