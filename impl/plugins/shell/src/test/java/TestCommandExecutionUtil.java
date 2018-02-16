import com.walterjwhite.shell.api.model.CommandOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestCommandExecutionUtil {
  private static final Logger LOGGER = LoggerFactory.getLogger(TestCommandExecutionUtil.class);

  public static void main(final String[] arguments) {
    final CommandOutput commandOutput = null; // CommandExecutionUtil.run("ps aux");
    LOGGER.debug("output:" + commandOutput.getOutput());
    // LOGGER.debug("error:" + commandOutputMessage.getError());
    // LOGGER.debug("returnCode:" + commandOutputMessage.getReturnCode());
  }
}
