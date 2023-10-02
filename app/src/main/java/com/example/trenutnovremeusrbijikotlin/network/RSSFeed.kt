package com.example.trenutnovremeusrbijikotlin.network

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Path
import org.simpleframework.xml.Root

@Entity(tableName = "rss")
@Root(name = "rss", strict = false)
data class RSSFeed(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
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
    var articleList: List<Station> = ArrayList(),
)
