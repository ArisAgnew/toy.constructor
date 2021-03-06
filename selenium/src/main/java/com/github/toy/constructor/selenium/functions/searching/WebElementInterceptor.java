package com.github.toy.constructor.selenium.functions.searching;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Method;

import static java.lang.String.format;

public class WebElementInterceptor implements MethodInterceptor {

    private final WebElement element;
    private final By by;
    private final String description;

    WebElementInterceptor(WebElement element, By by, String description) {
        this.element = element;
        this.by = by;
        this.description = description;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        if ("toString".equals(method.getName()) &&
                method.getParameterTypes().length == 0
                && String.class.equals(method.getReturnType())) {
            return format("Web element found by %s on condition '%s'", by, description);
        }
        return method.invoke(element, args);
    }
}
