package com.github.toy.constructor.selenium.functions.target.locator.frame;

import com.github.toy.constructor.core.api.SequentialGetSupplier;
import com.github.toy.constructor.selenium.SeleniumSteps;
import com.github.toy.constructor.selenium.functions.target.locator.TargetLocatorSupplier;
import org.openqa.selenium.WebDriver;

import java.util.function.Function;

import static com.github.toy.constructor.core.api.StoryWriter.toGet;

public final class GetFrameSupplier extends SequentialGetSupplier<SeleniumSteps, Frame, WebDriver, GetFrameSupplier>
        implements TargetLocatorSupplier<Frame> {

    private final Function<WebDriver, Frame> getFrame;

    private GetFrameSupplier(Function<WebDriver, Frame> getFrame) {
        this.getFrame = getFrame;
    }

    /**
     * Creates instance of {@link GetFrameSupplier} which wraps a function. This function switches to some frame by
     * defined parameters and returns an instance of {@link Frame}
     *
     * @param howToGetFrame how to get the frame to switch to.
     * @return instance of {@link GetFrameSupplier}
     */
    public static GetFrameSupplier frame(Function<WebDriver, Frame> howToGetFrame) {
        return new GetFrameSupplier(howToGetFrame).from(toGet("Attempt to switch to a frame",
                SeleniumSteps::getWrappedDriver));
    }

    @Override
    protected Function<WebDriver, Frame> getEndFunction() {
        return getFrame;
    }
}
