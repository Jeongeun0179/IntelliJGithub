package com.example.coffeeboardproject.controller;

import com.example.coffeeboardproject.dto.Cart;
import com.example.coffeeboardproject.dto.Coffee;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet("/addToCart")
public class AjaxRestController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // JSON 요청 데이터를 파싱
        String body = request.getReader().lines().collect(Collectors.joining());
        JsonObject jsonObject = JsonParser.parseString(body).getAsJsonObject();
        int coffeeId = jsonObject.get("coffee_id").getAsInt();

        // 데이터 처리: 임시로 Cart와 Coffee 객체를 생성
        Cart cart = new Cart();
        cart.setCoffee_id(coffeeId);
        cart.setQuantity(1); // 예제: 기본 수량은 1개

        Coffee coffee = new Coffee();
        coffee.setCoffee_name("Americano");
        coffee.setCoffee_price(5000);

        // 응답 데이터 작성
        response.setContentType("application/json");
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("success", true);
        jsonResponse.addProperty("coffee_id", cart.getCoffee_id());
        jsonResponse.addProperty("quantity", cart.getQuantity());
        response.getWriter().write(jsonResponse.toString());
    }
}

