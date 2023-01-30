package com.example.trenutnovremeusrbijikotlin.ui

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.trenutnovremeusrbijikotlin.R
import com.example.trenutnovremeusrbijikotlin.SharedPlacesViewModel
import com.example.trenutnovremeusrbijikotlin.databinding.OneStationBinding
import com.example.trenutnovremeusrbijikotlin.network.Station

class StationAdapter(private var list: List<Station>) :
    RecyclerView.Adapter<StationAdapter.ViewHolder>() {
    var mContext: Context? = null
    lateinit var listener: SharedPlacesViewModel

    class ViewHolder(var binding: OneStationBinding) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {
        var text = binding.tvStationName
        var humidity = binding.tvHumidity
        var windSpeed = binding.tvWindSpeed
        var windDirection = binding.tvWindDirection
        var temperature = binding.tvTemp
        var imageView: ImageView = binding.holderImageView
        var description = binding.tvDescription
        var favIcon = binding.favIcon

        override fun onClick(p0: View?) {
            if(humidity.visibility==View.VISIBLE){
                humidity.visibility=View.GONE
                windSpeed.visibility=View.GONE
                windDirection.visibility=View.GONE
                description.visibility=View.GONE
            }else{
                humidity.visibility=View.VISIBLE
                windSpeed.visibility=View.VISIBLE
                windDirection.visibility=View.VISIBLE
                description.visibility=View.VISIBLE
            }
        }
    }

    private fun setFavIcon(imageView: ImageView, favored: Boolean) {
        if (favored) {
            imageView.setImageResource(R.drawable.fav_icon_station_foreground)
        } else {
            imageView.setImageResource(R.drawable.unfav_icon_station_foreground)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = OneStationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.listener = listener
        val view = ViewHolder(binding)
        mContext = parent.context
        binding.cardOneItem.setOnClickListener(view)
        return view
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.text.text = list.get(position).title?.replace("Stanica: ", "") ?: "rrr"
        holder.humidity.text = list.get(position).humidity
        holder.temperature.text = list.get(position).temp
        holder.windDirection.text = list.get(position).windDirection
        holder.windSpeed.text = list.get(position).windSpeed
        holder.description.text = list.get(position).descriptionOfConditions
        mContext?.let { Glide.with(it).load(iconResourceUri(position)).into(holder.imageView) }
        setFavIcon(holder.favIcon, list.get(position).favorite)
        holder.binding.item = list[position]

    }
    private fun iconResourceUri(id: Int): Uri? {
        val myStringBuilder = StringBuilder()
        myStringBuilder.append("android.resource://com.example.trenutnovremeusrbijikotlin/drawable/")
        myStringBuilder.append("a")
        val iconId: Int? = list[id].descriptionOfConditionsCode
        myStringBuilder.append(iconId)
        //myStringBuilder.append(".png");
        val uri = Uri.parse(myStringBuilder.toString())
        myStringBuilder.setLength(0)
//        Timber.d("iconResourceUri: $uri")
        return uri
    }

    override fun getItemCount(): Int {
        return list.size ?: 0
    }

    fun setData(it: List<Station>?) {
        it?.let {
            this.list = it
        }
        notifyDataSetChanged()
    }

}