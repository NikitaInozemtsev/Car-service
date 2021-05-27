package serv.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "kind_of_work")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class KindOfWork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "start_price")
    private Integer startPrice;

    //in hours
    @Column(name = "lead_time")
    private Integer leadTime;

    //in days
    @Column(name = "guarantee")
    private Integer guarantee;

    @Override
    public String toString() {
        return "имя=" + name +
                ", время выполнения=" + leadTime + " ч." +
                ", гарантия=" + guarantee + " д.";
    }
}
