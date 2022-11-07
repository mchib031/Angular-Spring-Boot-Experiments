package seg3x02.booksrestapi.entities

import javax.persistence.*

@Entity
class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0
    var title: String = ""
    var category: String = ""
    var isbn: String = ""
    var cost: Double = 0.0
    var year: Int = 0
    var description: String = ""

    @ManyToMany(mappedBy = "books", cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    var authors: MutableList<Author> = ArrayList()

    @OneToMany(cascade = [CascadeType.ALL])
    var orders: MutableList<Order> = ArrayList()
}
