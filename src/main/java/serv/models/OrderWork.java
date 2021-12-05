package serv.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "work_id", referencedColumnName = "id")
    private KindOfWork kindOfWork;

    @Column(name = "price")
    private int price;

    @ManyToOne
    @JoinColumn(name="order_id", insertable = false, updatable = false)
    @JsonIgnore
    public Order order;

    @Override
    public String toString() {
        return "{"+kindOfWork +
                ", цена=" + price + " руб."
                +"}";
    }
}
