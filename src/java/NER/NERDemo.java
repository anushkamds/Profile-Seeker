package NER;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.io.IOUtils;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;

import java.util.List;


/** This is a demo of calling CRFClassifier programmatically.
 *  <p>
 *  Usage: {@code java -mx400m -cp "stanford-ner.jar:." NER.NERDemo [serializedClassifier [fileName]] }
 *  <p>
 *  If arguments aren't specified, they default to
 *  NER.classifiers/english.all.3class.distsim.crf.ser.gz and some hardcoded sample text.
 *  <p>
 *  To use CRFClassifier from the command line:
 *  </p><blockquote>
 *  {@code java -mx400m edu.stanford.nlp.ie.crf.CRFClassifier -loadClassifier [classifier] -textFile [file] }
 *  </blockquote><p>
 *  Or if the file is already tokenized and one word per line, perhaps in
 *  a tab-separated value format with extra columns for part-of-speech tag,
 *  etc., use the version below (note the 's' instead of the 'x'):
 *  </p><blockquote>
 *  {@code java -mx400m edu.stanford.nlp.ie.crf.CRFClassifier -loadClassifier [classifier] -testFile [file] }
 *  </blockquote>
 *
 *  @author Jenny Finkel
 *  @author Christopher Manning
 */

public class NERDemo {

  public static void main(String[] args) throws Exception {

//    String serializedClassifier = "NER.classifiers/english.all.3class.distsim.crf.ser.gz";
      String serializedClassifier = "src/NER/classifiers/english.conll.4class.distsim.crf.ser.gz";

    if (args.length > 0) {
      serializedClassifier = args[0];
    }

    AbstractSequenceClassifier<CoreLabel> classifier = CRFClassifier.getClassifier(serializedClassifier);

    /* For either a file to annotate or for the hardcoded text example,
       this demo file shows two ways to process the output, for teaching
       purposes.  For the file, it shows both how to run NER on a String
       and how to run it on a whole file.  For the hard-coded String,
       it shows how to run it on a single sentence, and how to do this
       and produce an inline XML output format.
    */
    if (args.length > 1) {
      String fileContents = IOUtils.slurpFile(args[1]);
      List<List<CoreLabel>> out = classifier.classify(fileContents);
      for (List<CoreLabel> sentence : out) {
        for (CoreLabel word : sentence) {
          System.out.print(word.word() + '/' + word.get(CoreAnnotations.AnswerAnnotation.class) + ' ');
        }
        System.out.println();
      }
      System.out.println("---||||| ");
      out = classifier.classifyFile(args[1]);
      for (List<CoreLabel> sentence : out) {
        for (CoreLabel word : sentence) {
          System.out.print(word.word() + '/' + word.get(CoreAnnotations.AnswerAnnotation.class) + ' ');
        }
        System.out.println();
      }

    } else {
      String[] example = {"Good afternoon Mr. Thilina Premasiri, how are you today?", "I am Running Train. I born in 1990/10/24",
                          "Malaka J. Walpola\n" +
                                  "Senior Lecturer at University of Moratuwa\n" +
                                  "Sri Lanka Higher Education\n" +
                                  "Join LinkedIn and access Malaka J. Walpola’s full profile. It's free!\n" +
                                  "\n" +
                                  "As a LinkedIn member, you'll join 300 million other professionals who are sharing connections, ideas, and opportunities.\n" +
                                  "\n" +
                                  "See who you and Malaka J. Walpola know in common\n" +
                                  "Get introduced to Malaka J. Walpola\n" +
                                  "Contact Malaka J. Walpola directly" };
//      for (String str : example) {
//        System.out.println(classifier.classifyToString(str));
//      }
//      System.out.println("---");
//
//      for (String str : example) {
//        // This one puts in spaces and newlines between tokens, so just print not println.
//        System.out.print(classifier.classifyToString(str, "slashTags", false));
//      }
//      System.out.println("---");
//
      for (String str : example) {
        System.out.println(classifier.classifyWithInlineXML(str));
      }
//      System.out.println("---");
//
//      for (String str : example) {
//        System.out.println(classifier.classifyToString(str, "xml", true));
//      }
//      System.out.println("---");

//      int i=0;
//      for (String str : example) {
//        for (List<CoreLabel> lcl : classifier.classify(str)) {
//          for (CoreLabel cl : lcl) {
//            System.out.print(i++ + ": ");
//            System.out.println(cl.toShorterString());
//          }
//        }
//      }
    }
  }

}
