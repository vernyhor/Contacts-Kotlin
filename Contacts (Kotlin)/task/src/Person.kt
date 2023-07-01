import java.time.LocalDateTime
class Person(): Contacts() {
    override var name: String = ""
    override var surname: String = ""
    override var address: String = ""
    override var timeLastEdit: String = ""
    override var timeCreated: String = ""
    override var number: String = ""
    override var gender: String = ""
    override var birthDate: String = ""
    override val contacts: String = "person"

    override fun toString(): String {
        return "$name $surname"
    }
    override fun add() {
            println("Enter the name of the person:")
            name = readln()
            println("Enter the surname of the person:")
            surname = readln()
            println("Enter the birth date:")
            birthDate = checkBirthDay(readln())
            if (birthDate == "[no data]") println("Bad birth date!")
            println("Enter the gender (M, F):")
            gender = checkGender(readln())
            if (gender == "[no data]") println("Bad gender!")
            println("Enter the number:")
            number = checkNumber(readln())
            if (number == "[no number]") println("Wrong number format!")
            timeCreated = LocalDateTime.now().toString()
            timeLastEdit = LocalDateTime.now().toString()
            info()
    }
    override fun info() {
        println("Name: $name\n" +
                "Surname: $surname\n" +
                "Birth date: $birthDate\n" +
                "Gender: $gender\n" +
                "Number: $number\n" +
                "Time created: $timeCreated\n" +
                "Time last edit: $timeLastEdit")
    }

    override fun edit() {
        println("Select a field (name, surname, birth, gender, number):")
        when (readln()) {
            "name" -> {
                println("Enter name:"); name = readln()
            }
            "surname" -> {
                println("Enter surname:"); surname = readln()
            }
            "birth" -> {
                println("Enter birthday:")
                birthDate = checkBirthDay(readln())
                if (birthDate == "[no data]") println("Bad birth date!")
            }
            "gender" -> {
                println("Enter gender:")
                gender = checkGender(readln())
                if (gender == "[no data]") println("Bad gender!")
            }
            "number" -> {
                println("Enter number:")
                number = checkNumber(readln())
                if (number == "[no number]") println("Wrong number format!")
            }
        }
        println("Saved")
        timeLastEdit = LocalDateTime.now().toString()
        info()
    }
}