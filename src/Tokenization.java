import java.io.*;

/**
 * Created by minhle on 9/5/2015.
 *
 */

public class Tokenization {
    public static final String inputDir = "data\\comp.txt";
    public static final String sentencesFileDir = "output\\sentences.txt";
    public static final String wordFileDir = "output\\words.txt";

    public static final String splitSentenceRegex = "(?<=[.?!])\\s+(?=[\"0-9\\p{Lu}])";
        public static final String splitWordRegex = "\\P{L}+";

    public static void main(String[] args) throws IOException {
        SplitSentence();
        SplitWord();
    }

    public static void SplitSentence() throws IOException {
        PrintWriter writer = new PrintWriter(sentencesFileDir, "UTF-8");
        File inputFile = new File(inputDir);
        BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                        new FileInputStream(inputFile), "UTF-8"));
        String article;
        while ((article = in.readLine()) != null){
            article = article.replace('\u00A0',' ');
            for (String sentence : article.split(splitSentenceRegex)){
                sentence = sentence.replace('\u00A0',' ').trim();
                if (sentence.length() > 0){
                    writer.println(sentence);
                }
            }
        }
        writer.close();
        in.close();
    }

    public static void SplitWord() throws IOException {
        PrintWriter writer = new PrintWriter(wordFileDir, "UTF-8");
        File inputFileDir = new File(sentencesFileDir);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(inputFileDir), "UTF-8"));
        String sentence;
        while ((sentence = in.readLine()) != null){
            for (String word : sentence.split(splitWordRegex)){
                if (word.length() > 0){
                    writer.println(word);
                }
            }
        }
        writer.close();
        in.close();
    }
}
