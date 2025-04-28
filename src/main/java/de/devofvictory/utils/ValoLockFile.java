package de.devofvictory.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
public class ValoLockFile {

    @Getter
    private String name, pid, port, password, protocol;
}
