package contacts

import java.time.LocalDateTime
import kotlin.system.exitProcess

fun main() {
    val contact = Contact()
    while (true) {
        println("Enter action (add, remove, edit, count, info, exit):")
        when (readln()) {
            "add" -> contact.add()
            "remove" -> contact.remove()
            "edit" -> contact.edit()
            "count" -> contact.count()
            "info" -> contact.info()
            "exit" -> exitProcess(0)
        }
    }
}

class Contact {
    private val name = mutableListOf<String>()
    private val address = mutableListOf<String>()
    private val surname = mutableListOf<String>()
    private val number = mutableListOf<String>()
    private val birthDate = mutableListOf<String>()
    private val gender = mutableListOf<String>()
    private val timeCreated = mutableListOf<String>()
    private val timeLastEdit = mutableListOf<String>()
    private val type = mutableListOf<String>()

    fun add() {
        println("Enter the type (person, organization):")
        val readType = readln()
        if (readType == "person") {
            println("Enter the name of the person:")
            name.add(readln())
            address.add("")
            println("Enter the surname of the person:")
            surname.add(readln())
            println("Enter the birth date:")
            birthDate.add(validBirthDay(readln()))
            if (birthDate.last() == "[no data]") println("Bad birth date!")
            println("Enter the gender (M, F):")
            gender.add(validGender(readln()))
            if (gender.last() == "[no data]") println("Bad gender!")
            println("Enter the number:")
            number.add(validNumber(readln()))
            if (number.last() == "[no number]") println("Wrong number format!")
            timeCreated.add(LocalDateTime.now().toString())
            timeLastEdit.add(LocalDateTime.now().toString())
            type.add("person")
        }
        if (readType == "organization") {
            println("Enter the organization name:")
            name.add(readln())
            println("Enter the address:")
            address.add(readln())
            surname.add("")
            println("Enter the number:")
            number.add(validNumber(readln()))
            if (number.last() == "[no number]") println("Wrong number format!")
            birthDate.add("")
            gender.add("")
            timeCreated.add(LocalDateTime.now().toString())
            timeLastEdit.add(LocalDateTime.now().toString())
            type.add("organization")
        }
        println("The record added.")
        println()
    }

    fun remove() {
        if (name.size == 0) println("No records to remove!")
        else {
            list()
            println("Select a record:")
            val index = readln().toInt() - 1
            name.removeAt(index)
            address.removeAt(index)
            surname.removeAt(index)
            number.removeAt(index)
            birthDate.removeAt(index)
            gender.removeAt(index)
            timeCreated.removeAt(index)
            timeLastEdit.removeAt(index)
            type.removeAt(index)
            println("The record removed!")
        }
        println()
    }

    fun edit() {
        if (name.size == 0) println("No records to edit!")
        else {
            list()
            println("Select a record:")
            val numberRecord = readln().toInt() - 1
            if (type[numberRecord] == "person") {
                println("Select a field (name, surname, birth, gender, number):")
                when (readln()) {
                    "name" -> {
                        println("Enter name:"); name[numberRecord] = readln()
                    }
                    "surname" -> {
                        println("Enter surname:"); surname[numberRecord] = readln()
                    }
                    "birth" -> {
                        println("Enter birthday:")
                        birthDate[numberRecord] = validBirthDay(readln())
                        if (birthDate[numberRecord] == "[no data]") println("Bad birth date!")
                    }
                    "gender" -> {
                        println("Enter gender:")
                        gender[numberRecord] = validGender(readln())
                        if (gender[numberRecord] == "[no data]") println("Bad gender!")
                    }
                    "number" -> {
                        println("Enter number:")
                        number[numberRecord] = validNumber(readln())
                        if (number[numberRecord] == "[no number]") println("Wrong number format!")
                    }
                }
                timeLastEdit[numberRecord] = LocalDateTime.now().toString()
            }
            if (type[numberRecord] == "organization") {
                println("Select a field (address, number):")
                when(readln()) {
                    "address" -> {
                        println("Enter address:")
                        address[numberRecord] = readln()
                    }
                    "number" -> {
                        println("Enter number:")
                        number[numberRecord] = validNumber(readln())
                        if (number[numberRecord] == "[no number]") println("Wrong number format!")
                    }
                }
                timeLastEdit[numberRecord] = LocalDateTime.now().toString()
            }
        }
        println ("The record updated!")
        println ()
    }

    fun count() {
        println("The Phone Book has ${name.size} records.")
        println()
    }


    private fun list() {
        for (index in name.indices) {
            println("${index + 1}. ${name[index]} ${surname[index]}")
        }
    }

    fun info() {
        list()
        println("Enter index to show info:")
        val index = readln().toInt() - 1
        if (type[index] == "person") {
            println("Name: ${name[index]}\nSurname: ${surname[index]}\n" +
                    "Birth date: ${birthDate[index]}\nGender: ${gender[index]}\n" +
                    "Number: ${number[index]}\nTime created: ${timeCreated[index]}\n" +
                    "Time last edit: ${timeLastEdit[index]}")
        }
        if (type[index] == "organization") {
            println("Organization name: ${name[index]}\nAddress: ${address[index]}\n" +
                    "Number: ${number[index]}\nTime created: ${timeCreated[index]}\n" +
                    "Time last edit: ${timeLastEdit[index]}")
        }
        println()
    }

    private fun validBirthDay(string: String): String {
        return when {
            !string.matches("\\d+([-.])\\d+([-.])\\d+".toRegex()) -> "[no data]"
            string.isEmpty() -> "[no data]"
            else -> string
        }
    }

    private fun validGender(string: String): String {
        return when {
            !string.matches("[MF]".toRegex()) -> "[no data]"
            string.isEmpty() -> "[no data]"
            else -> string
        }
    }

    private fun validNumber(string: String): String {
        return when {
            !string.matches("\\+?(((\\([\\da-zA-Z]+\\))([ \\-][\\da-zA-Z]{2,})?)|([\\da-zA-Z]+([ \\-]\\([\\da-zA-Z]{2,}\\))?)|(([\\da-zA-Z]+)([ \\-][\\da-zA-Z]{2,})?))([ \\-][\\da-zA-Z]{2,})*".toRegex()) -> "[no number]"
            string.isEmpty() -> "[no number]"
            else -> string
        }
    }
}
