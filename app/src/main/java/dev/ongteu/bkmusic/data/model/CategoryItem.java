package dev.ongteu.bkmusic.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TienGiang on 18/9/2016.
 */

public class CategoryItem {


    @SerializedName("categoryCode")
    @Expose
    private int categoryCode;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("img")
    @Expose
    private String img;
    @SerializedName("parentId")
    @Expose
    private int parentId;

    /**
     * No args constructor for use in serialization
     *
     */
    public CategoryItem() {
    }

    /**
     *
     * @param parentId
     * @param name
     * @param img
     * @param categoryCode
     */
    public CategoryItem(int categoryCode, String name, String img, int parentId) {
        super();
        this.categoryCode = categoryCode;
        this.name = name;
        this.img = img;
        this.parentId = parentId;
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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}