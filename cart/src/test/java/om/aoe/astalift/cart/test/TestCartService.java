package om.aoe.astalift.cart.test;

import com.aoe.astalift.cart.CartServiceConfigHook;
import com.aoe.astalift.cart.entity.Cart;
import com.aoe.astalift.cart.entity.CartItem;
import com.aoe.astalift.cart.service.CartService;
import com.aoe.astalift.common.dto.BaseResponse;
import com.aoe.astalift.product.dto.ProductInfo;
import com.aoe.astalift.product.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by joey on 16-3-18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(CartServiceConfigHook.class)
public class TestCartService {

    @Resource
    CartService cartService;

    @Resource
    ProductService productService;

    @Test
    public void foo(){
        Assert.notNull(cartService);
        Integer userId = 99;

        //keep it empty
        cartService.deleteUserCart(userId);
        Cart cart = cartService.getCart(userId);
        Assert.isTrue(cart == null);

        //add cart test
        Assert.notNull(cartService.addCart(userId));
        cart = cartService.getCart(userId);

        //empty test
        Assert.isTrue(cart != null);
        Assert.isTrue(cartService.listUserCart(userId).size() == 0);

        //load ProductInfo test
        BaseResponse<List<ProductInfo>> baseResponse = productService.listProduct();
        Assert.isTrue(baseResponse.getCode() == 0);
        Assert.isTrue(baseResponse.getData().size() != 0);
        Assert.isTrue(baseResponse.getData().size() > 1);
        List<ProductInfo> productInfoList = baseResponse.getData();
        ProductInfo p1 = productInfoList.get(0);
        ProductInfo p2 = productInfoList.get(1);

        //testAdd
        cartService.addProductToCart(cart, p1, 1);
        Assert.isTrue(cartService.getCart(userId).getCartItems().size() == 1);
        cartService.addProductToCart(cart, p1, 2);

        List<CartItem> items = cartService.listUserCart(userId);
        Assert.isTrue(items.size() == 1);
        Assert.isTrue(items.get(0).getProductInfo().getId() == p1.getId());
        Assert.isTrue(items.get(0).getQuantity() == 3);

        cartService.addProductToCart(cart, p2, 5);
        Assert.isTrue(cartService.listUserCart(userId).size() == 2);
        Assert.isTrue(cartService.getCart(userId).getCartItems().get(p2.getId()).getQuantity() == 5);
        Assert.isTrue(cartService.getCart(userId).getCartItems().get(p1.getId()).getQuantity() == 3);
        cartService.addProductToCart(cart, p2, 2);
        Assert.isTrue(cartService.getCart(userId).getCartItems().get(p2.getId()).getQuantity() == 7);

        //test remove product
        cartService.removeProductFromCart(cart, p1, 1);
        Assert.isTrue(cartService.getCart(userId).getCartItems().get(p1.getId()).getQuantity() == 2);
        Assert.isTrue(cartService.getCart(userId).getCartItems().get(p2.getId()).getQuantity() == 7);

        //test delete cartItem
        cartService.deleteCartItemFromCart(cart,p2);
        Assert.isTrue(cartService.getCart(userId).getCartItems().size() == 1);
        Assert.isTrue(cartService.getCart(userId).getCartItems().get(p1.getId()).getQuantity() == 2);

    }
}
