package com.example.trenutnovremeusrbijikotlin.network

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "item", strict = false)
data class Station(
    /**
     * @return the title
     */
    /**
     * @param title the title to set
     */
    @field:Element(name = "title")
    @param:Element(name = "title")
    var title: String? = null,

    /**
     * @return the description
     */
    /**
     * @param description the description to set
     */
    @field:Element(name = "description")
    @param:Element(name = "description")
    var description: String? = null
){
    init {
        parseData()
    }

    var humidity: String? = null
    var descriptionOfConditions: String? = null
    var descriptionOfConditionsCode: Int? = null
    var windSpeed: String? = null
    var windDirection: String? = null
    var temp: String? = null
    var pressure: String? = null
    var favorite: Boolean = false

    fun parseData() {
        val split: List<String>? = description?.split(";")
        if (split != null) {
            for (string in split) {
                if (string.contains("Pritisak")) pressure = string
                if (string.contains("Temperatura")) temp = string
                if (string.contains("Pravac")) windDirection = string
                if (string.contains("Brzina")) windSpeed = string
                if (string.contains("Vlažnost")) humidity = string
                if (string.contains("Opis")) descriptionOfConditions = string
                if (string.contains("ifra opisa vremena")) {
                    val newString = string.replace(" Šifra opisa vremena: ", "")
                    descriptionOfConditionsCode = Integer.valueOf(newString)
                }
            }
        }

    }
}
