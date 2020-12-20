package genexus.extension.intelli.pdfreader;


public class Main {

    public static void main(String[] args){
        PdfSplitPerPage pdfSplitPerPage = new PdfSplitPerPage();
        String origin = "C:/temp/WebFactoring.pdf";
        String destiny = "C:/temp";
        pdfSplitPerPage.splitInPages(origin, destiny, "[\\ ][0-9]{10}[\\ ]", true);
    }

}
