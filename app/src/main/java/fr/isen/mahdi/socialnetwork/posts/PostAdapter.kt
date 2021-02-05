package fr.isen.mahdi.socialnetwork.posts

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.mahdi.socialnetwork.databinding.PostCellBinding
import fr.isen.mahdi.socialnetwork.network.Post
import java.util.*


class PostAdapter(private val posts: List<Post>): RecyclerView.Adapter<PostAdapter.PostsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        return PostsViewHolder(PostCellBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.postTitle.text = posts[position].title
        holder.postDescription.text = posts[position].body
        holder.postUsername.text = posts[position].userId.toString() // to replace with real username
        holder.postCreatedAt.text = Date().toString()
        /**holder.dishName.text = posts[position].name.take(40)
        holder.dishPrice.text = "${posts[position].prices[0].price} $"
        if (dishes[position].images[0].isNotEmpty()) {
            Picasso.get().load(dishes[position].images[0]).into(holder.dishImage);
        }
        holder.dishCardView.setOnClickListener {
            cellClickListener.onCellClickListener(dishes[position])
        }*/
    }

    override fun getItemCount(): Int {
        return posts.count()
    }

    class PostsViewHolder(postCellBinding: PostCellBinding): RecyclerView.ViewHolder(postCellBinding.root) {
        val postTitle: TextView = postCellBinding.postTitle
        val postUsername: TextView = postCellBinding.postUsername
        val postDescription: TextView = postCellBinding.postDescription
        val postCreatedAt: TextView = postCellBinding.postCreatedAt
        val postImage: ImageView = postCellBinding.postImage
        val btnLike: ImageButton = postCellBinding.btnLike
        val btnComment: ImageButton = postCellBinding.btnComment
    }
}