package PDF;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;

public class ConvertPDFToXML {
    static StreamResult streamResult;
    static TransformerHandler handler;
    static AttributesImpl atts;

    public void Convert(String src, String dest){               // file extensions should be included

        try {
            Document document = new Document();
            document.open();
            PdfReader reader = new PdfReader(src);
            PdfDictionary page = reader.getPageN(1);
            PRIndirectReference objectReference = (PRIndirectReference) page
                    .get(PdfName.CONTENTS);
            PRStream stream = (PRStream) PdfReader
                    .getPdfObject(objectReference);
            byte[] streamBytes = PdfReader.getStreamBytes(stream);
            PRTokeniser tokenizer = new PRTokeniser(streamBytes);

            StringBuffer strbufe = new StringBuffer();
            while (tokenizer.nextToken()) {
                if (tokenizer.getTokenType() == PRTokeniser.TK_STRING) {
                    strbufe.append(tokenizer.getStringValue());
                }
            }
            String test = strbufe.toString();
            streamResult = new StreamResult(dest);
            initXML();
            process(test);
            closeXML();
            document.add(new Paragraph(".."));
            document.close();
        } catch (Exception e) {
        }
    }

    public static void initXML() throws ParserConfigurationException,
            TransformerConfigurationException, SAXException {
        SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory
                .newInstance();

        handler = tf.newTransformerHandler();
        Transformer serializer = handler.getTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
        serializer.setOutputProperty(
                "{http://xml.apache.org/xslt}indent-amount", "4");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        handler.setResult(streamResult);
        handler.startDocument();
        atts = new AttributesImpl();
        handler.startElement("", "", "data", atts);
    }

    public void process(String s) throws SAXException {
        String[] elements = s.split("\\|");
        atts.clear();
        handler.startElement("", "", "Message", atts);
        handler.characters(elements[0].toCharArray(), 0, elements[0].length());
        handler.endElement("", "", "Message");
    }

    public void closeXML() throws SAXException {
        handler.endElement("", "", "data");
        handler.endDocument();
    }
    public static void main(String[] args) throws IOException {
        new ConvertPDFToXML().Convert("Res/Dilshan CV.pdf","Res/CV.xml");
    }
}