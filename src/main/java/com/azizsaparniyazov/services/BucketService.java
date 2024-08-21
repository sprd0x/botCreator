package com.azizsaparniyazov.services;

import com.azizsaparniyazov.models.Bucket;
import com.azizsaparniyazov.models.Good;
import com.azizsaparniyazov.services.utilServices.JsonReader;
import com.azizsaparniyazov.services.utilServices.JsonWriter;
import com.fasterxml.jackson.core.type.TypeReference;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class BucketService {
    private final String PATH = "src\\Buckets.json";


    public void addToBucket(Good good, String customerId, String userId, Integer quantity) {
        List<Bucket> buckets = read();
        for (Bucket b : buckets) {
            if (b.getCustomerId().equals(customerId) && b.getUserId().equals(userId)) {
                updateBucket(good, customerId, userId, quantity);
            } else {
                Map<Good,Integer> goods = new HashMap<>();
                goods.put(good,quantity);
                Bucket bucket = new Bucket();
                bucket.setCustomerId(customerId);
                bucket.setUserId(userId);
                bucket.setGoods(goods);
                bucket.setTotalPrice(good.getPrice().multiply(new BigDecimal(quantity)));
                buckets.add(bucket);
                write(buckets);
            }
        }
    }

    public void updateBucket(Good good, String customerId, String userId, Integer quantity) {
        List<Bucket> buckets = read();
        Bucket myBucket = buckets.stream().filter(bucket -> bucket.getCustomerId().equals(customerId) && bucket.getUserId().equals(userId))
                .findFirst().orElse(null);
        myBucket.getGoods().put(good,quantity);
        BigDecimal price = good.getPrice().multiply(new BigDecimal(quantity));
        myBucket.setTotalPrice(myBucket.getTotalPrice().add(price));
        write(buckets);
    }

    public Optional<Bucket> showBucket (String customerId, String userId){
        return read().stream()
                .filter(bucket -> bucket.getCustomerId().equals(customerId)
                && bucket.getUserId().equals(userId))
                .findFirst();
    }

    public void reduceBucket(Good deleteGood,String customerId, String userId){
        List<Bucket> buckets = read();
        Bucket myBucket = buckets.stream().filter(bucket -> bucket.getCustomerId().equals(customerId) && bucket.getUserId().equals(userId))
                .findFirst().orElse(null);
        Integer value = myBucket.getGoods().get(deleteGood);
        BigDecimal newPrice = deleteGood.getPrice().multiply(new BigDecimal(value));
        myBucket.setTotalPrice(myBucket.getTotalPrice().subtract(newPrice));
        myBucket.getGoods().remove(deleteGood);
        write(buckets);
    }

    public void clearBucket(String customerId, String userId){
        List<Bucket> buckets = read();
        List<Bucket> newBuckets = buckets.stream().filter(bucket -> bucket.getCustomerId().equals(customerId)
        && bucket.getUserId().equals(userId)).collect(Collectors.toList());
        buckets = newBuckets;
        write(buckets);
    }


    public List<Bucket> read() {
        return JsonReader.readGson(PATH, new TypeReference<List<Bucket>>() {
        });
    }

    public void write(List<Bucket> buckets) {
        JsonWriter.writeGson(buckets, PATH);
    }
}
