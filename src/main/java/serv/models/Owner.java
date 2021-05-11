package serv.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "owners")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "fio")
    private String FIO;

    @Column(name = "passport", unique = true)
    private String passport;

    @Column(name = "phone")
    private String phone;

    @Override
    public String toString() {
        return "ФИО=" + FIO +
                ", паспорт=" + passport +
                ", номер телефона=" + phone;
    }
}
