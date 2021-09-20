package com.litil.catsandducks.presentation.adapters

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.litil.catsandducks.R
import com.litil.catsandducks.databinding.FragmentFavouritesBinding
import com.litil.catsandducks.databinding.ItemImageBinding
import com.litil.catsandducks.domain.models.db.ImageModel
import java.io.ByteArrayInputStream
import java.io.InputStream

class FavouritesAdapter : RecyclerView.Adapter<FavouritesAdapter.FavouritesViewHolder>() {
    var imagesList: List<ImageModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class FavouritesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.imageItem)

        fun bind(imageModel: ImageModel) {
            val bitmap = BitmapFactory.decodeByteArray(imageModel.image, 0, imageModel.image.size)
            val drawable = BitmapDrawable(Resources.getSystem(), bitmap)
            image.setImageDrawable(drawable)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritesViewHolder {
        return FavouritesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false),
        )
    }

    override fun onBindViewHolder(holder: FavouritesViewHolder, position: Int) {
        holder.bind(imagesList[position])
    }

    override fun getItemCount(): Int {
        return imagesList.size
    }
}