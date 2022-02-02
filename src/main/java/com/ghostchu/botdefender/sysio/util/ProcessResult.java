package com.ghostchu.botdefender.sysio.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProcessResult {
    private int code;
    private String response;
}
