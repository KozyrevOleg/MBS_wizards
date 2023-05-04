package com.msp360.at.wizards.steps;

public enum StorageClasses {

    S3_STANDARD("Standard"),
    S3_INTELLIGENT_TIERING("Intelligent-Tiering"),
    S3_STANDARD_IA("Standard-IA"),
    S3_ONE_ZONE_IA("One Zone-IA"),
    S3_GLACIER_FLEXIBLE_RETRIEVAL("Glacier Flexible Retrieval"),
    S3_GLACIER_DEEP_ARCHIVE("Glacier Deep Archive"),
    S3_GLACIER_INSTANT_RETRIEVAL("Glacier Instant Retrieval"),
    AZURE_HOT("Hot"),
    AZURE_COOL("Cool"),
    AZURE_ARCHIVE("Archive"),

    NO_CLASSES(null);

    private final String classType;

    StorageClasses(String classType) {
        this.classType = classType;
    }

    public String toString() {
        return classType;

    }
}
