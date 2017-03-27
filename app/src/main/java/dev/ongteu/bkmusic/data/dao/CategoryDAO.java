package dev.ongteu.bkmusic.data.dao;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import dev.ongteu.bkmusic.data.entity.Categories;
import dev.ongteu.bkmusic.data.entity.Categories_Schema;
import dev.ongteu.bkmusic.data.entity.OrmaDatabase;
import dev.ongteu.bkmusic.data.model.CategoryItem;
import dev.ongteu.bkmusic.data.parser.api.BaseRetrofit;
import dev.ongteu.bkmusic.data.parser.api.IServices;
import dev.ongteu.bkmusic.utils.MySPManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by TienGiang on 17/3/2017.
 */

public class CategoryDAO extends BaseDAO {

    public CategoryDAO(final Context context){
        super(context, true);
        init();
    }

    public void init() {
        final MySPManager spManager = new MySPManager(this.context);
        if (spManager.isFirstRun() || this.bkOrm.selectFromCategories().count() == 0) {
            IServices iGetCategories = BaseRetrofit.instance().create(IServices.class);
            Call<List<CategoryItem>> callCate = iGetCategories.listCategory();
            callCate.enqueue(new Callback<List<CategoryItem>>() {
                @Override
                public void onResponse(Call<List<CategoryItem>> call, Response<List<CategoryItem>> response) {
                    List<CategoryItem> listCate = response.body();
                    for (CategoryItem cateItem : listCate) {
                        Categories cate = new Categories(cateItem.getCategoryCode(), cateItem.getName(), cateItem.getImg(), cateItem.getParentId());
                        bkOrm.insertIntoCategories(cate);
                    }
                }

                @Override
                public void onFailure(Call<List<CategoryItem>> call, Throwable t) {

                }
            });
            spManager.setFirstRun(false);
        }
    }

    public List<CategoryItem> getCategoryByParentId(final int parentId){
        List<CategoryItem> categoryItems = new ArrayList<CategoryItem>();
        List<Categories> categories = this.bkOrm.selectFromCategories().where(Categories_Schema.INSTANCE.parentId, "=", parentId).toList();
        for (Categories cate : categories) {
            categoryItems.add(new CategoryItem(cate.getCategoryCode(), cate.getName(), cate.getImg(), cate.getParentId()));
        }
        return categoryItems;
    }
}
