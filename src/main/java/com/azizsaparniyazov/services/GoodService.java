package com.azizsaparniyazov.services;

import com.azizsaparniyazov.models.Category;
import com.azizsaparniyazov.models.Good;
import com.azizsaparniyazov.services.utilServices.JsonReader;
import com.azizsaparniyazov.services.utilServices.JsonWriter;
import com.fasterxml.jackson.core.type.TypeReference;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//TODO Madina

public class GoodService {
    private final String PATH = "src\\Goods.json";

    public Good addGood(String goodName, double price, UUID categoryId, String userId) {
        if (this.hasGood(goodName, categoryId, userId) == null) {
            Good good = new Good();
            good.setName(goodName);
            good.setPrice(new BigDecimal(price));
            good.setCategoryId(categoryId);
            good.setUserId(userId);
            List<Good> goods = read();
            goods.add(good);
            write(goods);
            return good;
        }
        return null;
    }

    public void updateGood(String userId,UUID categoryId,String newGoodName){
        List<Good> goods = read();
        goods.stream()
                .forEach(good -> {
                    if (good.getUserId().equals(userId)
                            && good.getCategoryId().equals(categoryId)) {
                        good.setName(newGoodName);
                    }
                });
        write(goods);
    }

    public void updateGood(String userId,UUID categoryId,double price){
        List<Good> goods = read();
        goods.stream()
                .forEach(good -> {
                    if (good.getUserId().equals(userId)
                            && good.getCategoryId().equals(categoryId)) {
                        good.setPrice(new BigDecimal(price));
                    }
                });
        write(goods);
    }

    public String deleteGood(String goodName, UUID categoryId, String userId) {
        Good good = this.hasGood(goodName, categoryId, userId);
        if (good != null) {
            List<Good> goods = read();
            goods.remove(good);
            write(goods);
            return "Good successfully deleted";
        }
        return "Category with this name not founded";
    }

    public List<Good> showGood(UUID categoryId, String userId) {
        List<Good> goods = new ArrayList<>();
        read().stream()
                .forEach(good -> {
                    if (good.getCategoryId().equals(categoryId)
                            && good.getUserId().equals(userId)) {
                        goods.add(good);
                    }
                });
        return goods;
    }

    private Good hasGood(String goodName, UUID categoryId, String userId) {
        return read().stream()
                .filter(good -> good.getName().equals(goodName)
                        && good.getCategoryId().equals(categoryId)
                        && good.getUserId().equals(userId))
                .findFirst()
                .orElse(null);
    }


    public void deleteGoodByCategory(UUID categoryId) {
        List<Good> goods = read();
        for (Good good : goods) {
            if (good.getCategoryId().equals(categoryId)) {
                goods.remove(good);
            }
        }
        write(goods);
    }

    public List<Good> read() {
        return JsonReader.readGson(PATH, new TypeReference<List<Good>>() {
        });
    }

    public void write(List<Good> goods) {
        JsonWriter.writeGson(goods, PATH);
    }
}
