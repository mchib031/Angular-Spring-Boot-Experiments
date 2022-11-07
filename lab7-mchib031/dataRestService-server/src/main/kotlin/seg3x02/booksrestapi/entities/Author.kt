package seg3x02.booksrestapi.entities

import javax.persistence.*

@Entity
class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0
    var firstName: String = ""
    var lastName: String = ""

    @ManyToMany
    var books: MutableList<Book> = ArrayList()

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var bio: Bio = Bio()
}
