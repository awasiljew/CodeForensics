package com.awasiljew.code.forensics.cloc;

import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import java.io.StringReader;
import java.util.Collection;

public class CLocModelBuilder {

    private final String csvContent;

    public CLocModelBuilder(String csvContent) {
        this.csvContent = csvContent;
    }

    public Collection<CLocModel> model() {
        BeanListProcessor<CLocModel> listProcessor = new BeanListProcessor<>(CLocModel.class);
        CsvParserSettings parserSettings = new CsvParserSettings();
        parserSettings.setProcessor(listProcessor);
        parserSettings.setHeaderExtractionEnabled(true);
        CsvParser parser = new CsvParser(parserSettings);
        parser.parse(new StringReader(csvContent));
        return listProcessor.getBeans();
    }
}
