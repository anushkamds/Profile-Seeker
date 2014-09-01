package PDF;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by Thilina on 8/11/2014.
 */
public class PDF_Reader {
    public void SaveTxt(String src,String dest) {
        PDDocument pd;
        BufferedWriter wr;
        try {
            File input = new File(src);  // The PDF file from where you would like to extract
            File output = new File(dest); // The text file where you are going to store the extracted data
            pd = PDDocument.load(input);
            System.out.println(pd.getNumberOfPages());
            System.out.println(pd.isEncrypted());
//            pd.save("CopyOfInvoice.pdf"); // Creates a copy called "CopyOfInvoice.pdf"
            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setStartPage(3); //Start extracting from page 3
            stripper.setEndPage(5); //Extract till page 5
            wr = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output)));
            stripper.writeText(pd, wr);
            if (pd != null) {
                pd.close();
            }
            // I use close() to flush the stream.
            wr.close();
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
