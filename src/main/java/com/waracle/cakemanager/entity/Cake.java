package com.waracle.cakemanager.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table
public class Cake {

    @Id
    private String title;
    private String description;
    private String image;

    public Cake(){}
    public Cake(String title, String description, String image) {
        this.title = title;
        this.description = description;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cake cake = (Cake) o;
        return Objects.equals(title, cake.title) && Objects.equals(description, cake.description) && Objects.equals(image, cake.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, image);
    }

    @Override
    public String toString() {
        return "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image ;
    }
}
