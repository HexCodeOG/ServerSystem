package de.hexcode.configuration.serialization;

import java.util.Map;

public interface ConfigurationSerializable {

    public Map<String, Object> serialize();
}
