import com.walterjwhite.shell.api.model.CommandOutput;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestUpower {
  private static final Logger LOGGER = LoggerFactory.getLogger(TestUpower.class);

  public static void main(final String[] arguments) throws IOException, InterruptedException {
    /*
    final CommandOutput commandOutputMessage = CommandExecutionUtil.run("upower -i /org/freedesktop/UPower/devices/battery_BAT0 | grep -P '(state|percentage)'");// | awk {'print$1,$2'} | sed -e \"s/ //\" | grep -Pv \"^$\"");
    LOGGER.debug("out:" + commandOutputMessage.getOutput());
    LOGGER.debug("err:" + commandOutputMessage.getError());
    LOGGER.debug("ext:" + commandOutputMessage.getReturnCode());
    */

    /*
    final CommandOutput chainedCommandOutput = CommandExecutionUtil.runChained(new String[]{"upower -i /org/freedesktop/UPower/devices/battery_BAT0",
            "grep -P (state|percentage)", "awk {print$1,$2}"/ *, "sed -e s/ //\"",
            "grep -Pv ^$"* /});
    LOGGER.debug("out:" + chainedCommandOutput.getOutput());
    LOGGER.debug("err:" + chainedCommandOutput.getError());
    LOGGER.debug("ext:" + chainedCommandOutput.getReturnCode());
    */

    final CommandOutput commandOutput = null;
    //        CommandExecutionUtil.run("upower -i /org/freedesktop/UPower/devices/battery_BAT0");
    LOGGER.debug("out:" + commandOutput.getOutput());
    // LOGGER.debug("err:" + commandOutputMessage.getError());
    // LOGGER.debug("ext:" + commandOutputMessage.getReturnCode());

    final Pattern statePattern =
        Pattern.compile(
            ".*(fully-charged|charging|discharging).*"); // Pattern.compile("state:[\\w]{1,}(fully-charged|charging|discharging)");
    final Pattern percentagePattern =
        Pattern.compile(
            ".*percentage:[\\W]{1,}([\\d]{1,3})%.*"); // Pattern.compile("state:[\\w]{1,}(fully-charged|charging|discharging)");

    String state = null;
    int percentage = -1;
    for (String line : commandOutput.getOutput().split("\n")) {
      if (state != null && percentage > 0) {
        break;
      }

      final Matcher stateMatcher = statePattern.matcher(line);

      if (stateMatcher.matches()) {
        LOGGER.debug("state matches:" + stateMatcher.matches());
        // LOGGER.debug("state matches:" + stateMatcher.group(0));
        LOGGER.debug("state matches:" + stateMatcher.group(1));

        state = stateMatcher.group(1);
        // LOGGER.debug("state matches:" + stateMatcher.group(2));
        continue;
      }

      final Matcher percentageMatcher = percentagePattern.matcher(line);
      if (percentageMatcher.matches()) {
        LOGGER.debug("percentage matches:" + percentageMatcher.matches());
        LOGGER.debug("percentage matches:" + percentageMatcher.group(1));
        percentage = Integer.valueOf(percentageMatcher.group(1));
        continue;
      }

      // UpowerUtil.getBatteryStatus();
    }

    LOGGER.debug("state:" + percentage + ":" + state);
  }
}
