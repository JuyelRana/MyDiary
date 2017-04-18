package org.careerop.mydiary;

/**
 * Created by Juyel_Rana on 12/9/2016.
 */
public class NotePad {

    private int id;
    private String title, body, date;

    public NotePad() {
    }

    public NotePad(String title, String body, String date) {
        this.title = title;
        this.body = body;
        this.date = date;
    }

    public NotePad(int id, String title, String body, String date) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "NotePad{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
