package ru.itmo.wp.form;

import javax.validation.constraints.*;

public class PostCredentials {
    @NotNull
    @NotEmpty
    @NotBlank
    @Size(max = 100)
    private String title;

    @NotNull
    @NotBlank
    @NotEmpty
    private String text;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Expected right tags")
    private String tags;

    public PostCredentials() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
