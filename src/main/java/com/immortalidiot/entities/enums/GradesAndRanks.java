package com.immortalidiot.entities.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum GradesAndRanks {
    RECRUIT("Recruit"),
    PRIVATE_II("Private II"),
    PRIVATE_I("Private I"),
    APPRENTICE_III("Apprentice III"),
    APPRENTICE_II("Apprentice II"),
    APPRENTICE_I("Apprentice I"),
    MASTER_III("Master III"),
    MASTER_II("Master II"),
    MASTER_I("Master I"),
    MAGISTER_III("Magister III"),
    MAGISTER_II("Magister II"),
    MAGISTER_I("Magister I");

    private final String displayName;

    GradesAndRanks(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static List<String> getAllGradesAndRanks() {
        return Arrays.stream(GradesAndRanks.values())
                .map(GradesAndRanks::getDisplayName)
                .collect(Collectors.toList());
    }
}
