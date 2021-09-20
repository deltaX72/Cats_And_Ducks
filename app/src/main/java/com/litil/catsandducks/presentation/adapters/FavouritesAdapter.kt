package com.litil.catsandducks.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.litil.catsandducks.R
import com.litil.catsandducks.domain.models.db.ImageModel
import com.litil.catsandducks.presentation.utils.convertByteArrayToDrawable

class FavouritesAdapter(
    private val onDoubleClicked: (ImageModel) -> Unit
) : RecyclerView.Adapter<FavouritesAdapter.FavouritesViewHolder>() {
    var imagesList: List<ImageModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class FavouritesViewHolder(itemView: View, private val onDoubleClicked: (ImageModel) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.imageItem)

        fun bind(imageModel: ImageModel) {
            image.setImageDrawable(convertByteArrayToDrawable(imageModel.image))

            image.setOnClickListener {
                onDoubleClicked(imageModel)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritesViewHolder {
        return FavouritesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false),
            onDoubleClicked
        )
    }

    override fun onBindViewHolder(holder: FavouritesViewHolder, position: Int) {
        holder.bind(imagesList[position])
    }

    override fun getItemCount(): Int {
        return imagesList.size
    }
}