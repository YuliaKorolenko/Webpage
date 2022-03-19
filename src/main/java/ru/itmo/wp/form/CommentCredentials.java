package ru.itmo.wp.form;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CommentCredentials {

    @Lob
    @NotBlank
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 60)
    private String text;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
