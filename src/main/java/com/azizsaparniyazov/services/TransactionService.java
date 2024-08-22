package com.azizsaparniyazov.services;


import com.azizsaparniyazov.models.Transaction;
import com.azizsaparniyazov.models.User;
import com.azizsaparniyazov.services.utilServices.JsonReader;
import com.azizsaparniyazov.services.utilServices.JsonWriter;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

//TODO Khurshid
public class TransactionService {

    private String PATH = "src/main/java/com/azizsaparniyazov/services/TransactionService.Transactions";








    private List<Transaction> read() {
        return JsonReader.readGson(PATH, new TypeReference<List<Transaction>>() {});
    }

    private void write(List<Transaction> transactions) {
        JsonWriter.writeGson(transactions, PATH);
    }
}
