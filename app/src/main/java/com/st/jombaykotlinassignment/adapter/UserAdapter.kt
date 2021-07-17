package com.st.jombaykotlinassignment.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.st.jombaykotlinassignment.databinding.TileListBinding
import com.st.jombaykotlinassignment.layoutInflater
import com.st.jombaykotlinassignment.model.UserModel
import java.util.stream.Collectors

internal class UserAdapter : RecyclerView.Adapter<UserAdapter.UserVH>() {

    internal var listUser: MutableList<UserModel> = mutableListOf<UserModel>()

    class UserVH(private val binding: TileListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(userModel: UserModel) {
            binding.apply {
                tvName.text = userModel.name
                tvDob.text = userModel.dob
                Glide.with(root).load(userModel.imagUrl).into(ivImag)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserVH(
        TileListBinding.inflate(parent.layoutInflater, parent, false)
    )

    override fun onBindViewHolder(holder: UserVH, position: Int) = holder.bind(listUser[position])

    fun setListUser(userModels: MutableList<UserModel>) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            listUser.addAll(
                userModels.stream().filter { t: UserModel ->
                    listUser.stream().noneMatch { u: UserModel -> (t.id == u.id) }
                }.collect(Collectors.toList())
            )
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = listUser.size
}
