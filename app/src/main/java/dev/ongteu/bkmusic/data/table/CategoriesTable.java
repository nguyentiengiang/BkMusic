package dev.ongteu.bkmusic.data.table;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.ongteu.bkmusic.data.model.CategoryItem;
import dev.ongteu.bkmusic.data.orm.CategoriesORM;
import dev.ongteu.bkmusic.data.parser.API;
import dev.ongteu.bkmusic.data.parser.ParserCategories;
import dev.ongteu.bkmusic.utils.Constant;
import dev.ongteu.bkmusic.utils.MySPManager;

/**
 * Created by TienGiang on 26/9/2016.
 */

public class CategoriesTable {

    public static final List<CategoryItem> ITEMS = new ArrayList<CategoryItem>();
    public static final Map<Integer, CategoryItem> ITEM_MAP = new HashMap<Integer, CategoryItem>();

    public CategoriesTable(final Context context, RecyclerView.Adapter adapterView, int parentId){
        final MySPManager spManager = new MySPManager(context);
        if (spManager.isFirstRun() || CategoriesORM.isEmpty(context)) {
            ParserCategories getParserCategories;
            API.GetData getDataCategories = new API.GetData(context);
            getParserCategories = new ParserCategories() {
                @Override
                public void success(String json) {
                    super.success(json);
                    for (CategoryItem categoryItem : listCategory) {
                        CategoriesORM.addCategory(context, categoryItem);
                    }
                }
            };
            String url = Constant.URL_GET_CATEGORY;
            getDataCategories.execute(API.getPackedParameters(url, null, getParserCategories, adapterView));
            spManager.setFirstRun(false);
        } else {
            ArrayList<CategoryItem> listCategory = CategoriesORM.getCategoriesByParentId(context, parentId);
            int index = 1;
            ITEMS.clear();
            ITEM_MAP.clear();
            for (CategoryItem item : listCategory) {
                ITEMS.add(item);
                ITEM_MAP.put(index, item);
                index++;
            }
        }
    }



}
