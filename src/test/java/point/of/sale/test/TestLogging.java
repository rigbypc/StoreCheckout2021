package point.of.sale.test;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

class TestLogging {

	private static Logger logger = LogManager.getLogger();
	
	@Test
	void test() {
		logger.debug("Debug log message");
		logger.info("Info log message");
		logger.warn("Warn log message");
		logger.error("Error log message");
	}

}
