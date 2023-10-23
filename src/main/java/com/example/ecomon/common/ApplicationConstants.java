package com.example.ecomon.common;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ApplicationConstants {
    public static class File {
        public static final String[] HEADERS = {
                "ID", "Object Name", "Object Description", "Pollutant Name",
                "Value Pollution", "Pollutant Mfr", "Pollutant Elv",
                "Pollutant TLV", "Pollution Concentration", "CR",
                "HQ", "Year"
        };
    }
}
