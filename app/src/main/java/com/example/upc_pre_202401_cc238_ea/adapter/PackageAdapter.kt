package com.example.upc_pre_202401_cc238_ea.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.upc_pre_202401_cc238_ea.R
import com.example.upc_pre_202401_cc238_ea.model.Package
import com.squareup.picasso.Picasso

class PackageAdapter(private val pckg: List<Package>, private val clickListener: OnItemClickListener) : Adapter<PackageAdapter.PackageViewHolder>() {
    inner class PackageViewHolder(itemView: View) : ViewHolder(itemView) {
        private val tvId: TextView = itemView.findViewById(R.id.tvId)
        private val tvName: TextView = itemView.findViewById(R.id.tvName)
        private val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        private val tvUbication: TextView = itemView.findViewById(R.id.tvUbication)
        private val ivPicture: ImageView = itemView.findViewById(R.id.ivPhoto)
        private val like: ImageButton = itemView.findViewById(R.id.ibAddFavorites)

        //para el picasso
        fun bind(pckg: Package, clickListener: OnItemClickListener) {
            tvId.text = pckg.idProducto
            tvName.text = pckg.nombre
            tvDescription.text = pckg.descripcion
            tvUbication.text = pckg.ubicacin

            Picasso.get()
                .load(pckg.imagen)
                .into(ivPicture)
            like.setOnClickListener {
                clickListener.onItemClick(pckg)
            }
        }
    }

    //prototipo
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PackageViewHolder {
        //attach to root: no te sale nada
        val view = LayoutInflater.from(parent.context).inflate(R.layout.prototype_package, parent, false)
        return PackageViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pckg.size
    }

    override fun onBindViewHolder(holder: PackageViewHolder, position: Int) {
        holder.bind(pckg[position], clickListener)
    }

    //para el click
    interface OnItemClickListener {
        fun onItemClick(pack: Package)
    }

}
