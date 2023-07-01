import java.util.*

abstract class Contacts {
    abstract var timeLastEdit: String
    abstract var timeCreated: String
    abstract var number: String
    abstract var gender: String
    abstract var birthDate: String
    abstract var surname: String
    abstract var address: String
    abstract val name: String
    abstract val contacts: String
    abstract fun add()
    abstract fun info()
    abstract fun edit()
    fun checkBirthDay(string: String): String {
        return when {
            !string.matches("\\d+([-.])\\d+([-.])\\d+".toRegex()) -> "[no data]"
            string.isEmpty() -> "[no data]"
            else -> string
        }
    }
    fun checkGender(string: String): String {
        return when {
            !string.matches("[MF]".toRegex()) -> "[no data]"
            string.isEmpty() -> "[no data]"
            else -> string
        }
    }
    fun checkNumber(string: String): String {
        return when {
            !string.matches("\\+?(((\\([\\da-zA-Z]+\\))([ \\-][\\da-zA-Z]{2,})?)|([\\da-zA-Z]+([ \\-]\\([\\da-zA-Z]{2,}\\))?)|(([\\da-zA-Z]+)([ \\-][\\da-zA-Z]{2,})?))([ \\-][\\da-zA-Z]{2,})*".toRegex()) -> "[no number]"
            string.isEmpty() -> "[no number]"
            else -> string
        }
    }
    fun contain (string: String): Boolean {
        return name.lowercase(Locale.getDefault()).contains(string) ||
                surname.lowercase(Locale.getDefault()).contains(string) ||
                address.lowercase(Locale.getDefault()).contains(string) ||
                number.lowercase(Locale.getDefault()).contains(string)
    }
}