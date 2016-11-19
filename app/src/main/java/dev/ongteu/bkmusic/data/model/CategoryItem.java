package dev.ongteu.bkmusic.data.model;

/**
 * Created by TienGiang on 18/9/2016.
 */
public class CategoryItem {

    private int id;
    private String name;
    private String img;
    private int parent_id;

    public CategoryItem() {
    }

    public CategoryItem(int id, String name, String img, int parent_id) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.parent_id = parent_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }
}
