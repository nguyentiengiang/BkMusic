package dev.ongteu.bkmusic.data.dao;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import dev.ongteu.bkmusic.data.entity.Categories;
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

public class CategoryDAO {
    public static List<CategoryItem> CATEGORY_ITEMS = new ArrayList<CategoryItem>();

    public CategoryDAO(final Context context, final int parentId, RecyclerView.Adapter adapterView){
        final MySPManager spManager = new MySPManager(context);
        if (spManager.isFirstRun() || Categories.count(Categories.class) == 0) {

            IServices iGetCategories = BaseRetrofit.instance().create(IServices.class);
            Call<List<CategoryItem>> callCate = iGetCategories.listCategory();
            callCate.enqueue(new Callback<List<CategoryItem>>() {
                @Override
                public void onResponse(Call<List<CategoryItem>> call, Response<List<CategoryItem>> response) {
                    List<CategoryItem> listCate = response.body();
                    for (CategoryItem cateItem : listCate) {
                        Categories cate = new Categories(cateItem.getCategoryCode(), cateItem.getName(), cateItem.getImg(), cateItem.getParentId());
                        cate.save();
                    }
                }

                @Override
                public void onFailure(Call<List<CategoryItem>> call, Throwable t) {

                }
            });
            spManager.setFirstRun(false);
        } else {
            List<Categories> categories = Categories.find(Categories.class, "PARENT_ID = ?", String.valueOf(parentId));
            for (Categories cate : categories) {
                CATEGORY_ITEMS.add(new CategoryItem(cate.getCategoryCode(), cate.getName(), cate.getImg(), cate.getParentId()));
            }
        }
    }

}
