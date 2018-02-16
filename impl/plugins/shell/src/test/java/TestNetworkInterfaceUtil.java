import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestNetworkInterfaceUtil {
  private static final Logger LOGGER = LoggerFactory.getLogger(TestNetworkInterfaceUtil.class);

  public static void main(final String[] arguments) throws SocketException {
    final Enumeration<NetworkInterface> networkInterfaceEnumeration =
        NetworkInterface.getNetworkInterfaces();

    while (networkInterfaceEnumeration.hasMoreElements()) {
      final NetworkInterface networkInterface = networkInterfaceEnumeration.nextElement();

      if (!networkInterface.isLoopback()) {
        final Enumeration<InetAddress> inetAddressEnumeration = networkInterface.getInetAddresses();
        while (inetAddressEnumeration.hasMoreElements()) {
          final InetAddress inetAddress = inetAddressEnumeration.nextElement();

          if (!inetAddress.isLinkLocalAddress()) {
            LOGGER.debug(
                "inetAddress:("
                    + networkInterface.getDisplayName()
                    + "):"
                    + inetAddress.getHostAddress());
          }
        }
      }
    }
  }
}
