package vo;

public class StockInfo {
    private static StringBuilder builder;
    static {
        builder = new StringBuilder();
    }
    private String ID;
    private String title;
    private String author;
    private String date;
    private String content;
    private String lastUpdate;
    private String answerAuthor;
    private String answer;

    public StockInfo (String ID, String title, String author, String date, String content, String lastUpdate, String answerAuthor, String answer) {
        this.ID = ID;
        this.title = title;
        this.author = author;
        this.date = date;
        this.content = content;
        this.lastUpdate = lastUpdate;
        this.answerAuthor = answerAuthor;
        this.answer = answer;
    }

    public StockInfo () {
    }


    public String getTitle () {
        return title;
    }

    public void setTitle (String title) {
        this.title = title;
    }

    public String getAuthor () {
        return author;
    }

    public void setAuthor (String author) {
        this.author = author;
    }


    public String getContent () {
        return content;
    }

    public void setContent (String content) {
        this.content = content;
    }


    public String getAnswerAuthor () {
        return answerAuthor;
    }

    public void setAnswerAuthor (String answerAuthor) {
        this.answerAuthor = answerAuthor;
    }

    public String getAnswer () {
        return answer;
    }

    public void setAnswer (String answer) {
        this.answer = answer;
    }

    public String getID () {
        return ID;
    }

    public void setID (String ID) {
        this.ID = ID;
    }

    public String getDate () {
        return date;
    }

    public void setDate (String date) {
        this.date = date;
    }

    public String getLastUpdate () {
        return lastUpdate;
    }

    public void setLastUpdate (String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        builder.delete(0,builder.length());
        builder.append(ID+"\t");
        builder.append(title+"\t");
        builder.append(author+"\t");
        builder.append(date+"\t");
        builder.append(content+"\t");
        builder.append(lastUpdate+"\t");
        builder.append(answerAuthor+"\t");
        builder.append(answer+"\n");

        return builder.toString();
    }
}
