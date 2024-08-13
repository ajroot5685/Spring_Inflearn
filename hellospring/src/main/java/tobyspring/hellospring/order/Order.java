package tobyspring.hellospring.order;

import lombok.ToString;

import java.math.BigDecimal;

@ToString
public class Order {

    private Long id;

    private String no;

    private BigDecimal total;

    public Order() {
    }

    public Order(String no, BigDecimal total) {
        this.no = no;
        this.total = total;
    }
}
