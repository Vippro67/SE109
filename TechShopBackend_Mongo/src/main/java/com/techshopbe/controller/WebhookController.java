package com.techshopbe.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;
import com.techshopbe.controller.webhookRequest.WebhookRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.techshopbe.dto.ProductDTO;
import com.techshopbe.service.ProductService;

@RestController
public class WebhookController {
    @Autowired
    private ProductService productService;

    @PostMapping(value = "/webhook", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> handleWebhookRequest(@RequestBody WebhookRequest request) {
        // Xử lý yêu cầu từ Dialogflow và trả về phản hồi
        String intent = request.getQueryResult().getIntent().getDisplayName();
        System.out.println(intent);
        Map<String, Object> customPayload = new HashMap<>();
        switch (intent) {
            case "Promotion_shop": {
                String responseText = "Intent: " + intent;
                customPayload.put("fulfillmentText", responseText);
                break;
            }
            case "Advise_Shop": {
                customPayload = createCustomPayloadIntentAdvise();
                break;
            }
            case "AdviseProduct_Shop": {
                // String responseText = "Intent: " + intent;
                // customPayload.put("fulfillmentText", responseText);

                Map<String, Object> pras = request.getQueryResult().getParameters();
                System.out.println(pras);

                customPayload = createCustomPayloadIntentAdvise02(pras);
                break;
            }
            default: {
                String responseText = "Không có phản hồi với Intent: " + intent;
                customPayload.put("fulfillmentText", responseText);
                break;
            }
        }

        return customPayload;
    }

    public Map<String, Object> createCustomPayloadIntentAdvise() {
        Map<String, Object> customPayload = new HashMap<>();
        // try {
        List<ProductDTO> trendingProducts = productService.getTrendingProducts();

        List<Map<String, Object>> payload = new ArrayList<>();

        int count = 0;
        for (ProductDTO productDTO : trendingProducts) {
            if (count < 3) {
                Map<String, Object> payloadButton = new HashMap<>();
                payloadButton.put("url", "http://localhost:3000/products/" + productDTO.getBrandName() + "/"
                        + productDTO.getProductID());

                Map<String, Object> action = new HashMap<>();
                action.put("type", "link");
                action.put("payload", payloadButton);

                Map<String, Object> button = new HashMap<>();
                button.put("name", "Xem sản phẩm");
                button.put("action", action);

                List<Map<String, Object>> buttons = new ArrayList<>();
                buttons.add(button);

                Map<String, Object> header = new HashMap<>();
                header.put("imgSrc", "http://localhost:8080/" + linkImages(productDTO.getImages())[0]);
                header.put("overlayText", String.valueOf(productDTO.getProductPrice()));

                Map<String, Object> payloadItem = new HashMap<>();

                payloadItem.put("titleExt", productDTO.getProductRate() + "/5");
                payloadItem.put("title", productDTO.getProductName());
                payloadItem.put("description", "Stock: " + productDTO.getStock());
                payloadItem.put("header", header);
                payloadItem.put("buttons", buttons);
                payloadItem.put("subtitle", "Brand: " + productDTO.getBrandName());

                payload.add(payloadItem);

                count++;
            } else
                break;
        }

        Map<String, Object> metadata = new HashMap<>();
        metadata.put("templateId", "10");
        metadata.put("contentType", "300");
        metadata.put("payload", payload);

        List<Map<String, Object>> fulfillmentMessages = new ArrayList<>();
        Map<String, Object> fulfillmentMessage01 = new HashMap<>();
        Map<String, Object> payloadfulfillmentMessage01 = new HashMap<>();
        payloadfulfillmentMessage01.put("message", "TechShop hiện tại đang có những sản phẩm hot là:");
        payloadfulfillmentMessage01.put("metadata", metadata);
        payloadfulfillmentMessage01.put("platform", "kommunicate");

        Map<String, Object> fulfillmentMessage02 = new HashMap<>();
        Map<String, Object> payloadfulfillmentMessage02 = new HashMap<>();
        payloadfulfillmentMessage02.put("message", "Bạn có muốn tư vấn các sản phẩm nào khác không?");
        payloadfulfillmentMessage02.put("platform", "kommunicate");

        fulfillmentMessage01.put("payload", payloadfulfillmentMessage01);
        fulfillmentMessage02.put("payload", payloadfulfillmentMessage02);
        fulfillmentMessages.add(fulfillmentMessage01);
        fulfillmentMessages.add(fulfillmentMessage02);

        customPayload.put("fulfillmentMessages", fulfillmentMessages);

        // } catch (Exception e) {
        // String responseText = "Không có phản hồi!";
        // customPayload.put("fulfillmentText", responseText);
        // }

        return customPayload;
    }

    public String[] linkImages(String imagesString) {
        String str = imagesString.replace("['", "");
        str = str.replace("'']", "");
        String[] linkimages = str.split("','");
        return linkimages;
    }

    public String createDecrip(String[] strings) {
        String Decrip = "";
        for (String str : strings) {
            Decrip += str;
            Decrip += "\n";
        }
        return Decrip;
    }

    public Map<String, Object> createCustomPayloadIntentAdvise02(Map<String, Object> parameters) {
        Map<String, Object> customPayload = new HashMap<>();

        if (parameters.get("e_product_categories").equals("") || parameters.get("e_product_brands").equals("")
                || parameters.get("number_priceMax").equals("") || parameters.get("number_priceMin").equals("")) {
            return customPayload;
        }

        List<ProductDTO> products = productService.getProductToAdvise((String) parameters.get("e_product_categories"),
                (String) parameters.get("e_product_brands"),
                (double) parameters.get("number_priceMax"), (double) parameters.get("number_priceMin"));

        ProductDTO product = null;

        if (products.size() == 0) {
            customPayload.put("fulfillmentText", "Tôi đã tìm kiếm nhưng không có sản phẩm nào như yêu cầu của bạn!");
        } else {
            product = products.get(0);

            Map<String, Object> payloadButton = new HashMap<>();
            payloadButton.put("url", "http://localhost:3000/products/" + product.getBrandName() + "/"
                    + product.getProductID());
            Map<String, Object> actionButton = new HashMap<>();
            actionButton.put("type", "link");
            actionButton.put("payload", payloadButton);
            Map<String, Object> button = new HashMap<>();
            button.put("name", "Xem sản phẩm");
            button.put("action", actionButton);

            List<Map<String, Object>> buttons = new ArrayList<>();
            buttons.add(button);

            Map<String, Object> header = new HashMap<>();
            header.put("overlayText", product.getProductPrice() + "đ");
            header.put("imgSrc", "http://localhost:8080/" + linkImages(product.getImages())[0]);

            Map<String, Object> payloadCard = new HashMap<>();
            payloadCard.put("title", product.getProductName());
            payloadCard.put("subtitle", "Stock: " + product.getStock());
            payloadCard.put("titleExt", product.getProductRate() + "/5");
            payloadCard.put("description", createDecrip(linkImages(product.getShortTech())));
            payloadCard.put("header", header);
            payloadCard.put("buttons", buttons);

            List<Map<String, Object>> payloadCards = new ArrayList<>();
            payloadCards.add(payloadCard);

            Map<String, Object> metadata = new HashMap<>();
            metadata.put("contentType", "300");
            metadata.put("templateId", "10");
            metadata.put("payload", payloadCards);

            Map<String, Object> payloadFulfillmessage = new HashMap<>();
            payloadFulfillmessage.put("message", "Đây là sản phẩm phù hợp với nhu cầu của bạn");
            payloadFulfillmessage.put("platform", "kommunicate");
            payloadFulfillmessage.put("metadata", metadata);

            Map<String, Object> fulfillmentMessage = new HashMap<>();
            fulfillmentMessage.put("payload", payloadFulfillmessage);

            List<Map<String, Object>> fulfillmentMessages = new ArrayList<>();
            fulfillmentMessages.add(fulfillmentMessage);

            customPayload.put("fulfillmentMessages", fulfillmentMessages);
        }

        return customPayload;
    }
}
