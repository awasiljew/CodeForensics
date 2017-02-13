package com.awasiljew.code.forensics.cloc;

import com.univocity.parsers.annotations.Parsed;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CLocModel {

    @Parsed
    private String language;
    @Parsed
    private String filename;
    @Parsed
    private int blank;
    @Parsed
    private int comment;
    @Parsed
    private int code;

}
