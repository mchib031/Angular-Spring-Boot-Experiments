package seg3x02.booksrestapi.entities

import javax.persistence.*

@Entity
@Table(name = "BookOrder")
class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0
    var quantity: Int = 0

}
