package com.aoe.astalift.order.constants;

import com.aoe.astalift.common.dto.ErrorTemplate;

/**
 * Created by joey on 16-3-23.
 */
public interface OrderError {
    class ItemAmountError extends ErrorTemplate{
        public ItemAmountError() {
            super(30001, "错误的商品数量, 采购量不能为0");
        }
    }
    class OrderNotExist extends ErrorTemplate{
        public OrderNotExist(){
            super(30002, "错误的商品数量, 采购量不能为0");
        }
    }

    class CannotCancel extends ErrorTemplate {
        public CannotCancel(){
            super(30003, "无法取消当前状态的订单， 请联系客服");
        }
    }

    class CannotReceive extends ErrorTemplate {
        public CannotReceive(){
            super(30004, "无法取消当前状态的订单， 请联系客服");
        }
    }

    class CannotConfirm extends  ErrorTemplate {
        public CannotConfirm(){
            super(30005, "无法接受当前状态的订单, 请联系客服");
        }
    }

}
