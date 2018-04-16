package com.github.toy.constructor.selenium.functions.target.locator.frame;

import com.github.toy.constructor.selenium.functions.searching.SearchSupplier;
import com.github.toy.constructor.selenium.functions.target.locator.SwitchesToItself;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsElement;

import static java.lang.String.format;

public class Frame implements SwitchesToItself  {
    private final WebDriver webDriver;
    private final Object frame;

    Frame(WebDriver webDriver, Object frame) {
        this.webDriver = webDriver;
        this.frame = frame;
        switchToMe();
    }

    @Override
    public void switchToMe() {
        Class<?> clazz = frame.getClass();
        if (Integer.class.isAssignableFrom(clazz)) {
            webDriver.switchTo().frame(Integer.class.cast(frame));
            return;
        }

        if (String.class.isAssignableFrom(clazz)) {
            webDriver.switchTo().frame(String.class.cast(frame));
            return;
        }

        if (WebElement.class.isAssignableFrom(clazz)) {
            webDriver.switchTo().frame(WebElement.class.cast(frame));
            return;
        }

        if (SearchSupplier.class.isAssignableFrom(clazz)) {
            SearchSupplier searchSupplier = SearchSupplier.class.cast(frame);
            Object element = searchSupplier.get().apply(webDriver);

            if (WebElement.class.isAssignableFrom(element.getClass())) {
                webDriver.switchTo().frame(WebElement.class.cast(element));
                return;
            }

            if (WrapsElement.class.isAssignableFrom(element.getClass())) {
                webDriver.switchTo().frame(WrapsElement.class
                        .cast(element).getWrappedElement());
            }
        }
    }

    @Override
    public WebDriver getWrappedDriver() {
        switchToMe();
        return webDriver;
    }

    public String toString() {
        return format("frame %s", frame);
    }
}
