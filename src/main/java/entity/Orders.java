package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Orders {
    @Id
    @Column(name = "ORDER_ID")
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems =
                new ArrayList<OrderItem>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderData;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    // == 연관관계 메소드 == //
    public void setMember(Member member) {
        // 기존 관계  제거
        if (this.member != null) {
            this.member.getOrders().remove(this);
        }
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
}
