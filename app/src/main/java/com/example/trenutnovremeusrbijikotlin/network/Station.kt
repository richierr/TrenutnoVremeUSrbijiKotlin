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
    var humidity:String?=null
    var descriptionOfConditions:String?=null
    var descriptionOfConditionsCode:Int?=null
    var windSpeed:String?=null
    var windDirection:String?=null
    var temp:String?=null
    var pressure: String? = null
    var favorite: Boolean = false
}
