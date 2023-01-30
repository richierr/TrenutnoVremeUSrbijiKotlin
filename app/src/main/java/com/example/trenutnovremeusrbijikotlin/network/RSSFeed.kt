package com.example.trenutnovremeusrbijikotlin.network

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Path
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
data class RSSFeed(
    /**
     * @return the channelTitle
     */
    /**
     * @param channelTitle the channelTitle to set
     */
    @field:Element(name = "title")
    @param:Element(name = "title")
    @field:Path("channel")
    @param:Path("channel")
    var channelTitle: String? = null,
    /**
     * @return the articleList
     */
    /**
     * @param articleList the articleList to set
     */
    @field:ElementList(name = "item", inline = true, required = false)
    @param:ElementList(name = "item", inline = true, required = false)
    @field:Path("channel")
    @param:Path("channel")
    var articleList: List<Station> = ArrayList()
){
    init {
        parseData()
    }

    fun parseData(){
        articleList.let {
            for (article in it) {
                val split: List<String>? = article.description?.split(";")
                if (split != null) {
                    for (string in split) {
                        if (string.contains("Pritisak")) article.pressure = string
                        if (string.contains("Temperatura")) article.temp = string
                        if (string.contains("Pravac")) article.windDirection = string
                        if (string.contains("Brzina")) article.windSpeed = string
                        if (string.contains("Vlažnost")) article.humidity = string
                        if (string.contains("Opis")) article.descriptionOfConditions = string
                        if (string.contains("ifra opisa vremena")) {
                            val newString = string.replace(" Šifra opisa vremena: ", "")
                            article.descriptionOfConditionsCode=Integer.valueOf(newString)
                        }
                    }
                }
            }
            // feedEntry.setTimeStamp(rssModel.getRss().getChannel().getLastBuildDate())
        }

    }
}
