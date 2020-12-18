package genexus.extension.intelli.pdfreader;


public class Main {

    public static void main(String[] args){
        PdfSplitPerPage pdfSplitPerPage = new PdfSplitPerPage();
        String origin = "C:/temp/genexus-extension/pdf-reader/origin/WebFactoring.pdf";
        String destiny = "C:/temp/genexus-extension/pdf-reader/result";
        pdfSplitPerPage.splitInPages(origin, destiny, "[\\ ][0-9]{10}[\\ ]", true);
    }

}
