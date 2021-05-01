package serv.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "order_work")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderWork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "order_id")
    private int orderId;

    @Column(name = "work_id")
    private int workId;

    @Column(name = "price")
    private int price;

    @ManyToOne
    @JoinColumn(name="order_id", insertable = false, updatable = false)
    @JsonIgnore
    public Order order;
}
