import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.File
import kotlin.system.exitProcess

fun main() {
    val adapterFactory = PolymorphicJsonAdapterFactory
        .of(Contacts::class.java, "contacts")
        .withSubtype(Person::class.java, "person")
        .withSubtype(Organization::class.java, "org")
    val moshi = Moshi.Builder()
        .add(adapterFactory)
        .add(KotlinJsonAdapterFactory())
        .build()
    val type = Types.newParameterizedType(MutableList::class.java, Contacts::class.java)
    val moshiAdapter = moshi.adapter<MutableList<Contacts>>(type)
    val file = File("file.txt")
    val contacts = mutableListOf<Contacts>()

    if (!file.exists()) { file.writeText(moshiAdapter.toJson(contacts)) }

    while (true) {
        println("[menu] Enter action (add, list, search, count, exit):")
        when (readln()) {
            "add" -> {
                println("Enter the type (person, organization):")
                val read = readln()
                if (read == "person") {
                    val personContact = Person()
                    personContact.add()
                    contacts.add(personContact)
                    file.writeText(moshiAdapter.toJson(contacts))
                    println()
                }
                if (read == "organization") {
                    val orgContact = Organization()
                    orgContact.add()
                    contacts.add(orgContact)
                    file.writeText(moshiAdapter.toJson(contacts))
                    println()
                }
            }
            "list" -> {
                contacts.forEach { println("${contacts.indexOf(it) + 1} $it") }
                println("\n[list] Enter action ([number], back):")
                val action = readln()
                contacts[action.toInt() - 1].info()
                println()
                when {
                    action.toIntOrNull() != null -> record(contacts, action.toInt() - 1)
                    action == "back" -> {
                        println()
                        break
                    }
                }
            }
            "search" -> {
                while (true) {
                    println("Enter search query:")
                    val searchList = mutableListOf<Contacts>()
                    val read = readln()
                    contacts.forEach { if (it.contain(read)) searchList.add(it)}
                    println("Found ${searchList.size} results:")
                    searchList.forEach { println("${searchList.indexOf(it) + 1} $it") }
                    println()
                    println("[search] Enter action ([number], back, again): > again\n")
                    val action = readln()
                    when {
                        action.toIntOrNull() != null -> {
                            searchList[action.toInt() - 1].info()
                            println()
                            record(contacts, action.toInt() - 1)
                            break
                        }
                        action == "back" -> break
                        action == "again" -> continue
                    }
                }
            }
            "count" -> println("The Phone Book has ${contacts.size} records.\n")
            "exit" -> exitProcess(0)
        }
    }
}
fun record (contacts: MutableList<Contacts>, index: Int) {
    while (true){
        println("[record] Enter action (edit, delete, menu): > menu")
        when (readln()) {
            "edit" -> {
                contacts[index].edit()
                println()
            }
            "delete" -> {
                contacts.removeAt(index)
                println()
                break
            }
            "menu" -> {
                println()
                break
            }
        }
    }
}