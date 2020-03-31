package it.polimi.ingsw.server.answers;

import java.io.Serializable;

public class SerializedMessage implements Serializable {
    private Answer serverAnswer;

    public void setServerAnswer(Answer answer) {
        serverAnswer = answer;
    }

    public Answer getServerAnswer() {
        return serverAnswer;
    }
}
