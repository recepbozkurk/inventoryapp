package com.example.inventoryapp.services;

import com.example.inventoryapp.dto.ProductDtoConverter;
import com.example.inventoryapp.entities.History;
import com.example.inventoryapp.entities.Product;
import com.example.inventoryapp.entities.Threshold;
import com.example.inventoryapp.repositories.ProductRepository;
import com.example.inventoryapp.repositories.ThresholdRepository;
import com.example.inventoryapp.utils.ResultEnums;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {
    final JavaMailSender javaMailSender;
    final ProductRepository productRepository;
    final ProductDtoConverter productDtoConverter;
    final ThresholdRepository thresholdRepository;
    final HistoryService historyService;


    public ProductService(JavaMailSender javaMailSender, ProductRepository productRepository, ProductDtoConverter productDtoConverter,
                          ThresholdRepository thresholdRepository, HistoryService historyService) {
        this.javaMailSender = javaMailSender;
        this.productRepository = productRepository;
        this.productDtoConverter = productDtoConverter;
        this.thresholdRepository = thresholdRepository;
        this.historyService = historyService;
    }

    public Map getProductList(){
        Map<ResultEnums, Object> result = new LinkedHashMap<>();
        result.put(ResultEnums.status, true);
        result.put(ResultEnums.result, productRepository.findAll().stream().map(productDtoConverter::convert).collect(Collectors.toList()));
        result.put(ResultEnums.message, "");

        return result;
    }

    public Map getProductListByFilter(Product filter){
        Map<ResultEnums, Object> result = new LinkedHashMap<>();
        result.put(ResultEnums.status, true);
        result.put(ResultEnums.result, productRepository.findByFilter(filter).stream().map(productDtoConverter::convert).collect(Collectors.toList()));
        result.put(ResultEnums.message, "");

        return result;
    }

    public Map saveProduct(Product product){
        Map<ResultEnums, Object> result = new LinkedHashMap<>();
        result.put(ResultEnums.status, true);
        result.put(ResultEnums.result, productDtoConverter.convert(productRepository.save(product)));
        result.put(ResultEnums.message, "");

        History history = new History();
        history.setProductId(product.getId());
        history.setProcess("ADD");
        history.setDate(new Date());
        historyService.saveHistory(history);

        return result;
    }

    public Map increaseProduct(int id, int amount){
        Map<ResultEnums, Object> result = new LinkedHashMap<>();

        if(amount < 0){
            result.put(ResultEnums.status, false);
            result.put(ResultEnums.message, "Lütfen pozitif bir değer giriniz.");

            return result;
        }

        List<Product> productList = productRepository
                .findAll()
                .stream()
                .filter(p -> p.getId() == id)
                .collect(Collectors.toList());

        Product product = productList.get(0);
        product.setAmount(product.getAmount() + amount);

        result.put(ResultEnums.status, true);
        result.put(ResultEnums.result, productDtoConverter.convert(productRepository.save(product)));
        result.put(ResultEnums.message, "");

        History history = new History();
        history.setProductId(product.getId());
        history.setProcess("INCREASE");
        history.setDate(new Date());
        historyService.saveHistory(history);

        return result;
    }

    public Map decreaseProduct(int id, int amount){
        Map<ResultEnums, Object> result = new LinkedHashMap<>();

        List<Product> productList = productRepository
                .findAll()
                .stream()
                .filter(p -> p.getId() == id)
                .collect(Collectors.toList());

        Product product = productList.get(0);
        if(product.getAmount() >= amount){
            product.setAmount(product.getAmount() - amount);

            result.put(ResultEnums.status, true);
            result.put(ResultEnums.result, productDtoConverter.convert(productRepository.save(product)));
            result.put(ResultEnums.message, "");

            checkThresholdValue(product.getProductId(), product.getAmount(), product.getName());

            History history = new History();
            history.setProductId(product.getId());
            history.setProcess("DECREASE");
            history.setDate(new Date());
            historyService.saveHistory(history);
        }
        else{
            result.put(ResultEnums.status, false);
            result.put(ResultEnums.message, "Envanterde belirtilen miktarda ürün bulunmamaktadır.");
        }

        return result;
    }

    public Map deleteProduct(int productId){
        productRepository.deleteById(productId);

        Map<ResultEnums, Object> result = new LinkedHashMap<>();
        result.put(ResultEnums.status, true);
        result.put(ResultEnums.message, "");

        History history = new History();
        history.setProductId((long) productId);
        history.setProcess("DELETE");
        history.setDate(new Date());
        historyService.saveHistory(history);

        return result;
    }

    public Map searchProduct(String name){
        List<Product> productList = productRepository
                .findAll()
                .stream()
                .filter(p -> p.getName().equals(name))
                .collect(Collectors.toList());

        List<Integer> storeList = new ArrayList<>();
        for(Product p: productList){
            storeList.add(p.getStoreId());
        }

        Map<ResultEnums, Object> result = new LinkedHashMap<>();
        result.put(ResultEnums.status, true);
        result.put(ResultEnums.result, storeList);
        result.put(ResultEnums.message, "");

        return result;
    }

    public void checkThresholdAndCreate(int productId){
        List<Threshold> thresholdList = thresholdRepository
                .findAll()
                .stream()
                .filter(t -> t.getProductId() == productId)
                .collect(Collectors.toList());

        if(thresholdList.isEmpty()){
            Threshold threshold = new Threshold();
            threshold.setProductId(productId);
            threshold.setThresholdValue(1); //default

            thresholdRepository.save(threshold);
        }
    }

    public void checkThresholdValue(int productId, int amount, String productName){
        List<Threshold> thresholdList = thresholdRepository
                .findAll()
                .stream()
                .filter(t -> t.getProductId() == productId)
                .collect(Collectors.toList());

        Threshold threshold = thresholdList.get(0);
        if(threshold.getThresholdValue() > amount) {
            sendEmail(productName, threshold.getThresholdValue());
        }
    }

    public void sendEmail(String productName, int threshold){
        String subject = "Kritik Eşik Uyarısı (" + productName + ")";
        String body = "Merhaba. " + productName + " ürünü belirlediğiniz (" + threshold + ") eşiğinin altına düştü.";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("gonderici@gmail.com");
        message.setTo("alici@gmail.com");
        message.setText(body);
        message.setSubject(subject);

        javaMailSender.send(message);
    }
}
