package uppgift_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ashkan Amiri
 * Date:  2021-01-04
 * Time:  15:17
 * Project: inlämningsUppgift_1
 * Copyright: MIT
 */
public class Question {
    protected String question;
    protected Category category;
    protected List<String> answers = new ArrayList<>();
    protected int correctAnswer;

    public Question(String q, String a1, String a2, String a3, String a4, Category cat, int correct) {
        question = q;
        answers = Arrays.asList(a1, a2, a3, a4);
        category = cat;
        correctAnswer = correct;
    }

    public String getQuestionString() {
        return question;
    }

    public Category getCategory() {
        return category;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public List<String> getAllAnswers() {
        return answers;
    }
}