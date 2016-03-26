package com.aoe.astalift.web.controller;

import com.aoe.astalift.cart.dto.Cart;
import com.aoe.astalift.cart.dto.CartItem;
import com.aoe.astalift.cart.service.CartService;
import com.aoe.astalift.common.dto.BaseResponse;
import com.aoe.astalift.common.dto.CommonErrors;
import com.aoe.astalift.product.dto.ProductDetail;
import com.aoe.astalift.product.service.ProductService;
import com.aoe.astalift.web.dto.request.CartDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by joey on 16-3-18.
 */
@RestController
public class CartController {
    private static Logger logger = LoggerFactory.getLogger(CartController.class);

    @Resource(name="cartService")
    CartService cartService;

    @Resource(name="productService")
    ProductService productService;

    @RequestMapping("/buy/cart/list_all")
    public BaseResponse<List<CartItem>> listCart(HttpSession session){
        Integer userId = (Integer) session.getAttribute("user_id");
        Cart cart = cartService.getCart(userId);
        return new BaseResponse<List<CartItem>>(cart.toItemList());
    }

    @RequestMapping(value = "/buy/cart/{item_id}",method = RequestMethod.POST)
    public  BaseResponse<List<CartItem>> addProductToCart(
            @PathVariable("item_id") Integer itemId,
            @RequestBody CartDto cartDto, HttpSession session){

        BaseResponse<ProductDetail> response = productService.getProductDetail(cartDto.getProductId());
        if(response.getCode() == 0 && response.getData() != null){
            Integer userId = (Integer) session.getAttribute("user_id");
            Cart cart = cartService.getCart(userId);
            cartService.addProductToCart(cart,response.getData(),cartDto.getNum());
            return new BaseResponse<List<CartItem>>(cart.toItemList());
        }
        return new BaseResponse(new CommonErrors.UnknownError());
    }

    @RequestMapping(value = "/buy/cart/{item_id}/{product_id}", method = RequestMethod.DELETE)
    public BaseResponse<List<CartItem>> removeProductFromCart(
            @PathVariable("item_id") Integer itemId,
            @PathVariable("product_id") Integer productId, @RequestParam(required = true) Integer num,
                                              HttpSession session){
        BaseResponse<ProductDetail> response = productService.getProductDetail(productId);
        if(response.getCode() == 0 && response.getData() != null){
            Integer userId = (Integer) session.getAttribute("user_id");
            Cart cart = cartService.getCart(userId);
            cartService.removeProductFromCart(cart, response.getData(), num);
            return new BaseResponse<List<CartItem>>(cart.toItemList());
        }
        return new BaseResponse(new CommonErrors.UnknownError());
    }

    @RequestMapping(value = "/buy/cart/{item_id}",method = RequestMethod.DELETE)
    public BaseResponse<List<CartItem>> deleteItemFromCart(@PathVariable("item_id") Integer itemId, HttpSession session){
        BaseResponse<ProductDetail> response = productService.getProductDetail(itemId);
        if(response.getCode() == 0 && response.getData() != null){
            Integer userId = (Integer) session.getAttribute("user_id");
            Cart cart = cartService.getCart(userId);
            cartService.deleteCartItemFromCart(cart, response.getData());
            return new BaseResponse<List<CartItem>>(cart.toItemList());
        }
        return new BaseResponse(new CommonErrors.UnknownError());
    }

    @RequestMapping(value = "/buy/cart/{item_id}", method = RequestMethod.PUT)
    public BaseResponse<List<CartItem>> setProductNum(@PathVariable("item_id") Integer itemId, HttpSession session,
        @RequestParam Integer num
    ){
        BaseResponse<ProductDetail> response = productService.getProductDetail(itemId);
        if(response.getCode() == 0 && response.getData() != null){
            Integer userId = (Integer) session.getAttribute("user_id");
            Cart cart = cartService.getCart(userId);
            cartService.setProductNum(cart, response.getData(), num);
            return new BaseResponse<List<CartItem>>(cart.toItemList());
        }
        return new BaseResponse(new CommonErrors.UnknownError());
    }

    @RequestMapping(value = "/buy/cart", method = RequestMethod.PUT)
    public BaseResponse<List<CartItem>> setCartItemList(
            @RequestBody List<CartItem> cartItemList, HttpSession session
    ){
        Integer userId = (Integer) session.getAttribute("user_id");
        Cart cart = cartService.getCart(userId);
        cart = cartService.setCartItemList(cart, cartItemList);
        return new BaseResponse<List<CartItem>>(cart.toItemList());
    }
}
