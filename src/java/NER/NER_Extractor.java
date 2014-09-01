package NER;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Thilina on 8/11/2014.
 */
public class NER_Extractor {
    Scanner sc;
    String[] lines;

    public NER_Extractor() {
        try {
            sc=new Scanner(new File("Res/CV.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        System.out.println("reading");
        String str = null;
        while (sc.hasNext())
            str+=sc.nextLine()+"\n";
//        System.out.println(str);
        lines=str.split("\n");

//        for (int i = 0; i < lines.length; i++) {
//            System.out.println(lines[i]);
//        }
    }

    public void ExtractNames(){
        String serializedClassifier = "src/NER/classifiers/english.conll.4class.distsim.crf.ser.gz";
        List<String> matches = new ArrayList<String>();
        try {
            AbstractSequenceClassifier<CoreLabel> classifier = CRFClassifier.getClassifier(serializedClassifier);

            for (String str : lines) {
                if(str.length()>2) {
                    String res = classifier.classifyWithInlineXML(str);
//                        String input = "one Organizer: <PERSON>Dayata Kirula</PERSON> 2011 <ORGANIZATION>CSE</ORGANIZATION>’s <ORGANIZATION>Exhibition Stall</ORGANIZATION>.  two;";
                        Pattern p = Pattern.compile("(?<=\\bPERSON>\\b).*?(?=\\b</PERSON\\b)");
                        Matcher m = p.matcher(res);
                        while (m.find()) {
                            matches.add(m.group());
                        }
                }
            }

            System.out.println(matches.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


}
