package serv.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "cars")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "technical_passport", unique = true)
    private String technicalPassport;

    @Column(name = "state_number", unique = true)
    private String stateNumber;

    @Column(name = "year")
    private String year;

    @Column(name = "info")
    private String info;

    @Override
    public String toString() {
        return "марка=" + brand  +
                ", модель=" + model +
                ", тех. паспорт=" + technicalPassport  +
                ", номер машины=" + stateNumber  +
                ", год выпуска=" + year +
                ", информация=" + info ;
    }
}
