package uppgift_1;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Created by Ashkan Amiri
 * Date:  2021-01-04
 * Time:  15:20
 * Project: inlämningsUppgift_1
 * Copyright: MIT
 */

/*
Inlämningsuppgit i kursen Funktionell Programmering för JAVA-programmet
För samtliga funktioner i denna fil, byt ut "throw UnSupportedException"-raden
och skriv ett pipeline-uttryck som returnerar det som ska returneras.
Streams MÅSTE användas i samtliga funktioner som lämnas in
För att testa om era funktioner funkar, kör testerna som hör till denna fil
För att bli godkänd på denna uppgift måste minst 7 av funktionerna vara implementerade.
Sigruns bedömning av denna uppgift kommer att gå till så att hon klipper in er version av denna fil
i sitt projekt och kör testerna.
Hennes kontroll om ni har klarat uppgifterna eller inte görs genom att
hon kollar att tillräckeligt många av tester går gröna. Pga detta ska ni inte ändra i någon fil
medföljande detta projekt, och inte heller metodsignaturerna i denna fil eller era tester, eftersom
ni då riskerar att påverka rättningen i negativ riktning.
 */

public class RewriteMe {

    public Database database = new Database();
    public List<Question> questions = database.getQuestions();

    //1-Skriv en funktioner som returnerar hur många frågor det finns i databasen?
    public int getAmountOfQuestionsInDatabase() {
        return (int) questions.stream().count();
    }

    //2-Hur många frågor finns i databasen för en viss, given kategori (som ges som inparameter)
    public int getAmountOfQuestionsForACertainCategory(Category category) {
        return (int) questions.stream().filter(c -> c.getCategory().equals(category)).count();
    }

    //3-Skapa en lista innehållandes samtliga frågesträngar i databasen
    public List<String> getListOfAllQuestions() {
        return questions.stream().map(Question::getQuestionString).collect(Collectors.toList());
    }

    //4-Skapa en lista innehållandes samtliga frågesträngar där frågan tillhör en viss kategori
    //Kategorin ges som inparameter
    public List<String> getAllQuestionStringsBelongingACategory(Category category) {
        return questions.stream()
                .filter(c -> c.getCategory().equals(category)).map(Question::getQuestionString)
                .collect(Collectors.toList());
    }

    //5-Skapa en lista av alla svarsalternativ, där varje svarsalternativ får förekomma
    // en och endast en gång i den lista som du ska returnera
    public List<String> getAllAnswerOptionsDistinct() {
        return questions.stream()
                .map(Question::getAllAnswers).flatMap(Collection::stream).distinct().collect(Collectors.toList());
    }


    //6-Finns en viss sträng, given som inparameter, som svarsalternativ till någon fråga i vår databas?
    public boolean isThisAnAnswerOption(String answerCandidate) {
        return questions.stream()
                .map(Question::getAllAnswers).flatMap(Collection::stream).distinct()
                .anyMatch(s -> s.contains(answerCandidate));
    }

    //7-Hur ofta förekommer ett visst svarsalternativ, givet som inparameter, i databasen
    public int getAnswerCandidateFrequncy(String answerCandidate) {
        return (int) questions.stream()
                .map(Question::getAllAnswers)
                .flatMap(Collection::stream)
                .filter(s -> s.contains(answerCandidate)).count();
    }

    //8-Skapa en Map där kategorierna är nycklar och värdena är en lista
    //av de frågesträngar som tillhör varje kategori
    public Map<Category, List<String>> getQuestionGroupedByCategory() {
        return questions.stream()
                .collect(Collectors.groupingBy(Question::getCategory,
                        TreeMap::new, Collectors.mapping(Question::getQuestionString, Collectors.toList())));
    }

    //9-Skapa en funktion som hittar det svarsalternativ som har flest bokstäver, i en kategori, given som inparameter
    // OBS: Du måste använda Reduce!
    public String getLongestLettercountAnwerInAGivenCategory(Category c) {
        return questions.stream()
                .filter(e -> e.getCategory().equals(c)).map(Question::getAllAnswers)
                .flatMap(Collection::stream)
                .reduce((answer1, answer2) -> answer1.length() >= answer2.length() ? answer1 : answer2).get();
    }


    public static void main(String[] args) {
        RewriteMe uppg4 = new RewriteMe();
    }
}