package com.aoe.astalift.cart.entity;

import com.aoe.astalift.product.dto.ProductInfo;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by joey on 16-3-18.
 */
public class Cart implements Serializable{
    @JsonProperty
    Integer userId;
    @JsonProperty
    Map<Integer, CartItem> cartItems = new LinkedHashMap<Integer, CartItem>();

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Map<Integer, CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Map<Integer, CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    //直接替换
    public void addCartItem(CartItem item){
        this.cartItems.put(item.getProductInfo().getId(), item);
    }

    public void addProduct(ProductInfo productInfo){
        CartItem cartItem = this.cartItems.get(productInfo.getId());
        if(null == cartItem){
            cartItem = new CartItem();
            cartItem.setProductInfo(productInfo);
            cartItem.setQuantity(0);
        }

        int org = cartItem.getQuantity();
        cartItem.setQuantity(org + 1);
        this.cartItems.put(productInfo.getId(), cartItem);
    }

    public List<CartItem> toItemList(){
        List<CartItem> cartItemList = new LinkedList<CartItem>();
        for (CartItem cartItem : cartItems.values()) {
            cartItemList.add(cartItem);
        }
        return cartItemList;
    }
}
