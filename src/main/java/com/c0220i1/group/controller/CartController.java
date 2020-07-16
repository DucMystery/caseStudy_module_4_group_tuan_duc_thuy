package com.c0220i1.group.controller;

import com.c0220i1.group.model.*;
import com.c0220i1.group.service.products.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.*;

@Controller
@SessionAttributes("mycart")
  public class CartController {
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private HttpSession session;
    @Autowired
    private CustomerInfoService customerInfoService;
    @ModelAttribute("mycart")
        public HashMap<Long, CartLine> showMyCart(){
            return new HashMap<>();
    }


    @GetMapping("/mycart")
    public ModelAndView cartPage(@ModelAttribute("mycart")HashMap<Long,CartLine> mycart,Principal principal){
      ModelAndView modelAndView = new ModelAndView("cart");
      double amount = 0;
      for(CartLine cartLine:mycart.values()){
          amount+=cartLine.getAmount();
      }
      modelAndView.addObject("account",accountService.findByName(principal.getName()));
      modelAndView.addObject("amount",amount);
      modelAndView.addObject("mycart",mycart);
       return modelAndView;

    }
    @GetMapping("/addcart/{id}")
    public ModelAndView addProductToCart(@PathVariable Long id,
                                         @ModelAttribute("mycart")HashMap<Long,CartLine> mycart){
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        if(mycart==null){
            mycart= new HashMap<Long,CartLine>();
        }
        Product product =productService.findById(id).orElse(null);
        if(!mycart.containsKey(id)){
            CartLine cartLine = new CartLine(product,1);
            mycart.put(id,cartLine);
        } else {
            CartLine cartLine= mycart.get(id);
            cartLine.setQuantity(cartLine.getQuantity()+1);
            mycart.put(id,cartLine);
        }
        modelAndView.addObject("mycart",mycart);
        return modelAndView;
    }
    @DeleteMapping("/removefromcart/{id}")
    @ResponseBody
    public CartLine deleteProductFromToCart(@PathVariable Long id,
                                            @ModelAttribute("mycart")HashMap<Long,CartLine> mycart){

       CartLine cartLine= mycart.get(id);
       mycart.remove(id);
       return cartLine;

    }
    @PostMapping("/updatecart")
    public ModelAndView  updateToCart(@RequestParam("quantity_pro")int[] quantity,
                               @ModelAttribute("mycart")HashMap<Long,CartLine> mycart){
      Set<Long> keys= mycart.keySet();
      int index=0;
        for(Long key:keys){
            CartLine cartLine=mycart.get(key);
            cartLine.setQuantity(quantity[index]);;
            mycart.put(key,cartLine);
            index++;
        }
        double amount=0;

        for(CartLine cartLine:mycart.values()){
            amount+=cartLine.getAmount();
        }
        ModelAndView modelAndView = new ModelAndView("cart");
        modelAndView.addObject("amount",amount);
        modelAndView.addObject("mycart",mycart);
        return modelAndView;
    }
    @GetMapping("/clearallcart")
    public ModelAndView showCartPageWhenclearCart(@ModelAttribute("mycart")HashMap<Long,CartLine> mycart){
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        mycart.clear();
        modelAndView.addObject("mycart",mycart);
        return modelAndView;
    }
    @GetMapping("/payment")
    public ModelAndView showPaymentPage(Principal principal){

        String username=principal.getName();
        List<OrderDetail> orderDetails= new ArrayList<>();
        HashMap<Long,CartLine> mycart= (HashMap<Long, CartLine>) session.getAttribute("mycart");
        Orders order = getOrders(username, mycart);
        CustomerInfo customerInfo= customerInfoService.findByAccount_Username(username);
        ModelAndView modelAndView = new ModelAndView("payment");
        modelAndView.addObject("customerinfo",customerInfo);
       modelAndView.addObject("order",order);
       return modelAndView;
    }
    @PostMapping("/paymentconfirm")
    public ModelAndView showPage(Principal principal,@ModelAttribute ("mycart")HashMap<Long,CartLine> mycart ){
        String username=principal.getName();
        CustomerInfo customerInfo= customerInfoService.findByAccount_Username(username);
        List<OrderDetail> orderDetails= new ArrayList<>();
        Product product= new Product();
        mycart= (HashMap<Long, CartLine>) session.getAttribute("mycart");
        Orders order = getOrders(username, mycart);
        for(OrderDetail orderDetail: order.getDetails()){
            //Luu oder detail lai
            orderDetailService.save(orderDetail);
            //Thay doi so luong cua product trong stock
            product=productService.findById(orderDetail.getProduct().getId()).orElse(null);
            product.setQuantity(product.getQuantity()-orderDetail.getQuantity());
        }
        orderService.save(order);
        mycart.clear();
        ModelAndView modelAndView = new ModelAndView("alertSuccess");
        modelAndView.addObject("account",accountService.findByName(principal.getName()));
//        modelAndView.addObject("customerinfo",customerInfo);
//        modelAndView.addObject("order",order);
        modelAndView.addObject("message",
                "You are buy our product success. Please wait 1-2 day for shipment. Thank you so much");
        return modelAndView;
    }

    private Orders getOrders(String username, HashMap<Long, CartLine> mycart) {
        List<OrderDetail> orderDetails =new ArrayList<>();;
        Set<Long> keys= mycart.keySet();
        for(Long key:keys){
            OrderDetail orderDetail= new OrderDetail(mycart.get(key).getProduct(),
                    mycart.get(key).getQuantity(),mycart.get(key).getAmount());
            orderDetails.add(orderDetail);
        }
        double amount=0;
        Account account = accountService.findByName(username);
        Orders order = new Orders();
        order.setDetails(orderDetails);
        order.setAccount(account);
        order.setDateOrder(new Date());
        //Lay amount tu list orderdetail sang

        for(OrderDetail orderDetail:order.getDetails()){
            amount+=orderDetail.getAmount();
        }
        order.setAmount(amount);
        if (order.getAmount()>=300){
            order.setShipFee(0);
        } else {
            order.setShipFee(10);
        }
        order.setPaymentsuccess(false);
        return order;
    }


}


