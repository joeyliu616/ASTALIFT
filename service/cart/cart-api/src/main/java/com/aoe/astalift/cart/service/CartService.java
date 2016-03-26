package com.aoe.astalift.cart.service;

import com.aoe.astalift.cart.dto.Cart;
import com.aoe.astalift.cart.dto.CartItem;
import com.aoe.astalift.product.dto.ProductInfo;

import java.util.List;

/**
 * Created by joey on 16-3-18.
 */
public interface CartService {
    Cart addCart(Integer userId);
    Cart getCart(Integer userId);
    Cart addProductToCart(Cart cart, ProductInfo productInfo, Integer num);
    List<CartItem> listUserCart(Integer userId);
    Cart removeProductFromCart(Cart  cart, ProductInfo productInfo, Integer num);
    Cart deleteCartItemFromCart(Cart cart, ProductInfo productInfo);
    Cart setProductNum(Cart cart, ProductInfo productInfo, Integer num);
    Cart setCartItemList(Cart cart, List<CartItem> items);
    void deleteUserCart(Integer userId);
}
