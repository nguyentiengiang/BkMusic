package dev.ongteu.bkmusic.data.entity;

import com.github.gfx.android.orma.annotation.Column;
import com.github.gfx.android.orma.annotation.PrimaryKey;
import com.github.gfx.android.orma.annotation.Table;

/**
 * Created by TienGiang on 16/3/2017.
 */

@Table
public class Categories {

    @PrimaryKey(autoincrement = true)
    private long id;

    @Column
    private int categoryCode;

    @Column
    private String name;

    @Column
    private String img;

    @Column
    private int parentId;

    /**
     * No args constructor for use in serialization
     */
    public Categories() {
    }

    /**
     * @param parentId
     * @param name
     * @param img
     */
    public Categories(int categoryCode, String name, String img, int parentId) {
        super();
        this.categoryCode = categoryCode;
        this.name = name;
        this.img = img;
        this.parentId = parentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
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

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }


}