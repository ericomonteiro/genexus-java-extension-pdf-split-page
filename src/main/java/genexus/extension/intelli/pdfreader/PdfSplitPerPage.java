package genexus.extension.intelli.pdfreader;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PdfSplitPerPage {

    public void splitInPages (String sourceFile, String folderDestiny, String strategyDestinyName, Boolean gerenateTextFile) {

        Pattern pattern = Pattern.compile(strategyDestinyName);

        try {
            PdfReader reader = new PdfReader(sourceFile);
            int n = reader.getNumberOfPages();
            reader.close();
            String path;
            PdfStamper stamper;
            for (int i = 1; i <= n; i++) {

                System.out.println("Page: " + i);

                reader = new PdfReader(sourceFile);

                PdfReaderContentParser parser = new PdfReaderContentParser(reader);
                TextExtractionStrategy strategy;
                strategy = parser.processContent(i, new SimpleTextExtractionStrategy());

                String fileContent = strategy.getResultantText();
                String fileName = getFileName(pattern, fileContent);

                System.out.println(fileContent);

                reader.selectPages(String.valueOf(i));
                path = folderDestiny + "/" + fileName + ".pdf";

                if (gerenateTextFile) {
                    generateTextFile(path.replace(".pdf", ".txt"), fileContent);
                }

                stamper = new PdfStamper(reader, new FileOutputStream(path));
                stamper.close();
                reader.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private String getFileName(Pattern regexFileName, String contentFile) {
        Matcher m = regexFileName.matcher(contentFile);
        if (m.find()) {
            return m.group().trim().replaceAll("[^0-9]", "");
        }

        return UUID.randomUUID().toString();
    }

    private void generateTextFile(String pathFile, String contentFile) {
        try {
            PrintWriter out = new PrintWriter(new FileOutputStream(pathFile));
            out.println(contentFile);
            out.flush();
            out.close();
        }  catch (Exception e) {
            System.out.println(e);
        }
    }

}
