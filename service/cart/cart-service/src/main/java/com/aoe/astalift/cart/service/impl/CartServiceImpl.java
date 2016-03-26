package com.aoe.astalift.cart.service.impl;

import com.aoe.astalift.cart.dto.Cart;
import com.aoe.astalift.cart.dto.CartItem;
import com.aoe.astalift.cart.service.CartService;
import com.aoe.astalift.product.dto.ProductInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by joey on 16-3-18.
 */
@Service("cartService")
public class CartServiceImpl implements CartService {

    private static Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

    @Resource
    RedisTemplate<Integer, Cart> cartCache;

    public Cart addCart(Integer userId) {
        Cart cart = new Cart();
        cart.setUserId(userId);
        cartCache.opsForValue().set(userId,cart);
        return cart;
    }

    public Cart getCart(Integer userId) {
        Cart cart = cartCache.opsForValue().get(userId);
        if(null == cart){
            return this.addCart(userId);
        }
        return cart;
    }


    public Cart addProductToCart(Cart cart, ProductInfo productInfo, Integer num) {
        for (int i = 0; i < num; i++) {
            cart.addProduct(productInfo);
        }
        cartCache.opsForValue().set(cart.getUserId(),cart);
        return cart;
    }

    public List<CartItem> listUserCart(Integer userId) {
        List<CartItem> cartItemList = new LinkedList<CartItem>();
        Cart cart = cartCache.opsForValue().get(userId);
        Map<Integer, CartItem> cartItems = cart.getCartItems();
        for (CartItem cartItem : cartItems.values()) {
            cartItemList.add(cartItem);
        }
        return cartItemList;
    }

    public Cart removeProductFromCart(Cart cart, ProductInfo productInfo, Integer num) {
        CartItem cartItem = cart.getCartItems().get(productInfo.getId());
        if(null == cartItem){
            logger.error("购物车减操作失败 该产品未加入购物车 productId: {} title: {}", productInfo.getId(), productInfo.getTitle());
            return null;
        }
        Integer quantity = cartItem.getQuantity();
        if(quantity == 0){
            return cart;
        }else{
            if(quantity - num < 0){
                logger.error("购物车减操作失败 没有足够数量 productId: {} title: {}", productInfo.getId(), productInfo.getTitle());
                return null;
            }
            cartItem.setQuantity(quantity - num);
            cart.getCartItems().put(productInfo.getId(), cartItem);
            cartCache.opsForValue().set(cart.getUserId(),cart);
            return cart;
        }
    }

    public Cart deleteCartItemFromCart(Cart cart, ProductInfo productInfo) {
        CartItem cartItem = cart.getCartItems().get(productInfo.getId());
        if(null == cartItem){
            return cart;
        }else{
            cart.getCartItems().remove(productInfo.getId());
            cartCache.opsForValue().set(cart.getUserId(),cart);
            return cart;
        }
    }

    public Cart setProductNum(Cart cart, ProductInfo productInfo, Integer num) {
        CartItem cartItem = cart.getCartItems().get(productInfo.getId());
        if(null == cartItem){
            logger.error("购物车减操作失败 该产品未加入购物车 productId: {} title: {}", productInfo.getId(), productInfo.getTitle());
            return cart;
        }else {
            cartItem.setQuantity(num);
            cartCache.opsForValue().set(cart.getUserId(),cart);
            return cart;
        }
    }


    public Cart setCartItemList(Cart cart, List<CartItem> items) {
        Map<Integer, CartItem> itemMap = new LinkedHashMap();
        for (CartItem item : items) {
            itemMap.put(item.getProductInfo().getId(), item);
        }
        cart.setCartItems(itemMap);
        cartCache.opsForValue().set(cart.getUserId(), cart);
        return cart;

    }

    public void deleteUserCart(Integer userId) {
        cartCache.delete(userId);
    }
}
