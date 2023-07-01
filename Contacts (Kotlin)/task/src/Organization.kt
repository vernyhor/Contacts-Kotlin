import java.time.LocalDateTime

class Organization() : Contacts() {
    override var name: String = ""
    override var surname: String = ""
    override var address: String = ""
    override var timeLastEdit: String = ""
    override var timeCreated: String = ""
    override var number: String = ""
    override var gender: String = ""
    override var birthDate: String = ""
    override val contacts: String = "org"

    override fun toString(): String {
        return name
    }
    override fun add() {
        println("Enter the organization name:")
        name = readln()
        println("Enter the address:")
        address = readln()
        println("Enter the number:")
        number = checkNumber(readln())
        if (number == "[no number]") println("Wrong number format!")
        timeCreated = LocalDateTime.now().toString()
        timeLastEdit = LocalDateTime.now().toString()
        info()
    }

    override fun info() {
        println("Organization name: $name\n" +
                "Address: $address\n" +
                "Number: $number\n" +
                "Time created: $timeCreated\n" +
                "Time last edit: $timeLastEdit")
    }

    override fun edit() {
        println("Select a field (name, address, number):")
        when(readln()) {
            "name" -> {
                println("Enter name:")
                name = readln()
            }
            "address" -> {
                println("Enter address:")
                address = readln()
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