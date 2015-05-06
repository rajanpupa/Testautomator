package selenium.automation.treeelements.events;

import org.openqa.selenium.WebDriver;

/**
 * @author Rajan Prasad Upadhyay
 */
public interface IPageEvent{
    /**
     * Execute the main task this event is supposed to execute
     */
    public boolean run(WebDriver driver);
}
