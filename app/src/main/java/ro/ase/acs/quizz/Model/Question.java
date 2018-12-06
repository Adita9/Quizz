package ro.ase.acs.quizz.Model;

import java.io.Serializable;
import java.util.List;

public class Question  implements Serializable{
    String title;
    List<Answer> answers;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
