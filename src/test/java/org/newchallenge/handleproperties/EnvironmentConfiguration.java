package org.newchallenge.handleproperties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static org.newchallenge.handleproperties.LoggerMessage.MESSAGE_FILE_NOT_FOUND;
import static org.newchallenge.handleproperties.LoggerMessage.MESSAGE_IOEXCEPTION;

/**
 * This class is in charge to set the default  parameters to
 * establishes the connection with the end points are set.
 *
 * @author <a href="mailto:luis.marcelo.garay@gmaill.com">Marcelo Garay</a>
 * @version 1.0
 */
public class EnvironmentConfiguration {
    private Properties properties;
    private static EnvironmentConfiguration EnvironmentConfiguration;

    private final static Logger LOGGER = LoggerFactory.getLogger(EnvironmentConfiguration.class);
    //Name setting file
    private final static String CONFIG = "gradle.properties";

    /**
     * This method read the gradle.property file.
     */
    private EnvironmentConfiguration() {
        File file = new File(CONFIG);
        try (FileReader fileReader = new FileReader(file)) {
            properties = new Properties();
            properties.load(fileReader);
        } catch (FileNotFoundException e) {
            LOGGER.warn(MESSAGE_FILE_NOT_FOUND, e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            LOGGER.warn(MESSAGE_IOEXCEPTION, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * This method Instance the EnvironmentEndpoint if this does not exist.
     *
     * @return the instanced EnvironmentEndpoint.
     */
    public static EnvironmentConfiguration getInstance() {
        if (EnvironmentConfiguration == null) {
            EnvironmentConfiguration = new EnvironmentConfiguration();
        }
        return EnvironmentConfiguration;
    }

    /**
     * Get the properties of the file.
     *
     * @param name string name of property setting.
     * @return String that is a property
     */
    public String getPropertyByName(final String name) {
        String property = System.getProperty(name);
        if (property == null) {
            return properties.getProperty(name);
        }
        return property;
    }
}
