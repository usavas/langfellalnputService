package com.langfella.NLPUtil;

import opennlp.tools.lemmatizer.DictionaryLemmatizer;
import opennlp.tools.lemmatizer.LemmatizerME;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@RestController
public class WordRootController {

    //TODO add sentence detector

    private DictionaryLemmatizer lemmatizer;
    private POSTaggerME posTaggerME;

    public WordRootController(){
        try {
            lemmatizer = new DictionaryLemmatizer(new File("/usr/app/en_lemmatizer"));
            posTaggerME = new POSTaggerME(new POSModel(new File("/usr/app/en_pos_maxent.bin")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/word/{wordRaw}")
    public String getWordRoot(@PathVariable String wordRaw){
        return getLemmaOfWord(wordRaw);
    }

    private String getLemmaOfWord(String word) {
        String lemma = "";
            lemma = lemmatizer.lemmatize(new String[]{word}, getPosOfWords(new String[]{word}))[0];
            return (lemma.equals("O")) ? null : lemma;
    }

    private String[] getPosOfWords(String[] words) {
        return posTaggerME.tag(words);
    }
}
