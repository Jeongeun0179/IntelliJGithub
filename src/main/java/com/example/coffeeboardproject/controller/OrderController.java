package com.example.coffeeboardproject.controller;

import com.example.coffeeboardproject.dto.Cart;
import com.example.coffeeboardproject.dto.Coffee;
import com.example.coffeeboardproject.dto.Order;
import com.example.coffeeboardproject.dto.User;
import com.example.coffeeboardproject.service.CartService;
import com.example.coffeeboardproject.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;

    // 결제하기를 누르면 실행
    @RequestMapping("/manager.do")
    public String manager(HttpServletRequest request, Model model) {
        User user = new User();
        user.setEmail(request.getParameter("email"));
        user.setAddress(request.getParameter("address"));
        user.setZipcode(request.getParameter("postcode"));
        model.addAttribute("userFlag", orderService.userInsert(user));

        Order order = new Order();
        order.setEmail(request.getParameter("email"));
        //System.out.println(request.getParameter("email"));
        order.setTotal_price(Integer.parseInt(request.getParameter("totalPrice")));
        System.out.println(Integer.parseInt(request.getParameter("totalPrice")));
        order.setOrder_date(request.getParameter("orderDate"));

        model.addAttribute("orderFlag", orderService.orderInsert(order));
        //System.out.println("order.getOrder_id()1: " + order.getOrder_id());

        Cart cart = new Cart();
//        cart.setOrder_id(order.getOrder_id());
//        System.out.println("order.getOrder_id(): " + order.getOrder_id());
//        cart.setQuantity(cart.getQuantity());
//        System.out.println("cart.getQuantity(): " + cart.getQuantity());
//        cart.setCoffee_id(Integer.parseInt(request.getParameter("coffeeId")));
//        model.addAttribute("cartFinalFlag", cartService.cartFinalInsert(cart));

        model.addAttribute("deleteFlag", cartService.cartDelete(cart));

        return "board_manager1";
    }
}
